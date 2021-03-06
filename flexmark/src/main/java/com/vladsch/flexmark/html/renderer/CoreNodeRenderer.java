package com.vladsch.flexmark.html.renderer;

import com.vladsch.flexmark.ast.*;
import com.vladsch.flexmark.ast.util.ReferenceRepository;
import com.vladsch.flexmark.ast.util.TextCollectingVisitor;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.internal.ListOptions;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.Escaping;
import com.vladsch.flexmark.util.options.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.vladsch.flexmark.util.sequence.SubSequence.NULL;

/**
 * The node renderer that renders all the core nodes (comes last in the order of node renderers).
 */
public class CoreNodeRenderer implements NodeRenderer {
    final static public AttributablePart LOOSE_LIST_ITEM = new AttributablePart("LOOSE_LIST_ITEM");
    final static public AttributablePart TIGHT_LIST_ITEM = new AttributablePart("TIGHT_LIST_ITEM");
    final static public AttributablePart PARAGRAPH_LINE = new AttributablePart("PARAGRAPH_LINE");
    final static public AttributablePart CODE_CONTENT = new AttributablePart("FENCED_CODE_CONTENT");

    private final ReferenceRepository referenceRepository;
    private final ListOptions listOptions;

    public CoreNodeRenderer(DataHolder options) {
        this.referenceRepository = options.get(Parser.REFERENCES);
        this.listOptions = new ListOptions(options);
    }

    @Override
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        return new HashSet<>(Arrays.asList(
                new NodeRenderingHandler<>(AutoLink.class, this::render),
                new NodeRenderingHandler<>(BlockQuote.class, this::render),
                new NodeRenderingHandler<>(BulletList.class, this::render),
                new NodeRenderingHandler<>(Code.class, this::render),
                new NodeRenderingHandler<>(Document.class, this::render),
                new NodeRenderingHandler<>(Emphasis.class, this::render),
                new NodeRenderingHandler<>(FencedCodeBlock.class, this::render),
                new NodeRenderingHandler<>(HardLineBreak.class, this::render),
                new NodeRenderingHandler<>(Heading.class, this::render),
                new NodeRenderingHandler<>(HtmlBlock.class, this::render),
                new NodeRenderingHandler<>(HtmlCommentBlock.class, this::render),
                new NodeRenderingHandler<>(HtmlInnerBlock.class, this::render),
                new NodeRenderingHandler<>(HtmlInnerBlockComment.class, this::render),
                new NodeRenderingHandler<>(HtmlEntity.class, this::render),
                new NodeRenderingHandler<>(HtmlInline.class, this::render),
                new NodeRenderingHandler<>(HtmlInlineComment.class, this::render),
                new NodeRenderingHandler<>(Image.class, this::render),
                new NodeRenderingHandler<>(ImageRef.class, this::render),
                new NodeRenderingHandler<>(IndentedCodeBlock.class, this::render),
                new NodeRenderingHandler<>(Link.class, this::render),
                new NodeRenderingHandler<>(LinkRef.class, this::render),
                new NodeRenderingHandler<>(BulletListItem.class, this::render),
                new NodeRenderingHandler<>(OrderedListItem.class, this::render),
                new NodeRenderingHandler<>(MailLink.class, this::render),
                new NodeRenderingHandler<>(OrderedList.class, this::render),
                new NodeRenderingHandler<>(Paragraph.class, this::render),
                new NodeRenderingHandler<>(Reference.class, this::render),
                new NodeRenderingHandler<>(SoftLineBreak.class, this::render),
                new NodeRenderingHandler<>(StrongEmphasis.class, this::render),
                new NodeRenderingHandler<>(Text.class, this::render),
                new NodeRenderingHandler<>(TextBase.class, this::render),
                new NodeRenderingHandler<>(ThematicBreak.class, this::render)
        ));
    }

    private void render(Document node, NodeRendererContext context, HtmlWriter html) {
        // No rendering itself
        context.renderChildren(node);
    }

    private void render(Heading node, NodeRendererContext context, HtmlWriter html) {
        if (context.getHtmlOptions().renderHeaderId) {
            String id = context.getNodeId(node);
            if (id != null) {
                html.attr("id", id);
            }
        }

        if (context.getHtmlOptions().sourcePositionParagraphLines) {
            html.srcPos(node.getChars()).withAttr().tagLine("h" + node.getLevel(), () -> {
                html.srcPos(node.getText()).withAttr().tag("span");
                context.renderChildren(node);
                html.tag("/span");
            });
        } else {
            html.srcPos(node.getText()).withAttr().tagLine("h" + node.getLevel(), () -> {
                context.renderChildren(node);
            });
        }
    }

    private void render(BlockQuote node, NodeRendererContext context, HtmlWriter html) {
        html.withAttr().tagIndent("blockquote", () -> {
            context.renderChildren(node);
        });
    }

    private void render(FencedCodeBlock node, NodeRendererContext context, HtmlWriter html) {
        html.line();
        html.srcPosWithTrailingEOL(node.getChars()).withAttr().tag("pre").openPre();

        BasedSequence info = node.getInfo();
        if (info.isNotNull() && !info.isBlank()) {
            int space = info.indexOf(' ');
            BasedSequence language;
            if (space == -1) {
                language = info;
            } else {
                language = info.subSequence(0, space);
            }
            html.attr("class", context.getHtmlOptions().languageClassPrefix + language.unescape());
        }

        html.srcPosWithEOL(node.getContentChars()).withAttr(CODE_CONTENT).tag("code");
        html.text(node.getContentChars().normalizeEOL());
        html.tag("/code");
        html.tag("/pre").closePre();
        html.line();
    }

    private void render(ThematicBreak node, NodeRendererContext context, HtmlWriter html) {
        html.srcPos(node.getChars()).withAttr().tagVoidLine("hr");
    }

    private void render(IndentedCodeBlock node, NodeRendererContext context, HtmlWriter html) {
        html.line();
        html.srcPosWithEOL(node.getChars()).withAttr().tag("pre").openPre();
        html.srcPosWithEOL(node.getContentChars()).withAttr(CODE_CONTENT).tag("code");
        html.text(node.getContentChars().trimTailBlankLines().normalizeEndWithEOL());
        html.tag("/code");
        html.tag("/pre").closePre();
        html.line();
    }

    private void render(BulletList node, NodeRendererContext context, HtmlWriter html) {
        html.withAttr().tagIndent("ul", () -> {
            context.renderChildren(node);
        });
    }

    private void render(OrderedList node, NodeRendererContext context, HtmlWriter html) {
        int start = node.getStartNumber();
        if (listOptions.orderedStart && start != 1) html.attr("start", String.valueOf(start));
        html.withAttr().tagIndent("ol", () -> {
            context.renderChildren(node);
        });
    }

    private void render(BulletListItem node, NodeRendererContext context, HtmlWriter html) {
        render((ListItem) node, context, html);
    }

    private void render(OrderedListItem node, NodeRendererContext context, HtmlWriter html) {
        render((ListItem) node, context, html);
    }

    private void render(ListItem node, NodeRendererContext context, HtmlWriter html) {
        if (listOptions.isTightListItem(node)) {
            html.srcPosWithEOL(node.getChars()).withAttr(TIGHT_LIST_ITEM).withCondIndent().tagLine("li", () -> {
                context.renderChildren(node);
            });
        } else {
            html.srcPosWithEOL(node.getChars()).withAttr(LOOSE_LIST_ITEM).tagIndent("li", () -> {
                context.renderChildren(node);
            });
        }
    }

    public static void renderTextBlockParagraphLines(Node node, NodeRendererContext context, HtmlWriter html) {
        if (context.getHtmlOptions().sourcePositionParagraphLines) {
            BasedSequence nextLine = getSoftLineBreakSpan(node.getFirstChild());
            if (nextLine.isNotNull()) {
                html.srcPos(nextLine).withAttr(PARAGRAPH_LINE).tag("span");
                context.renderChildren(node);
                html.tag("/span");
                return;
            }
        }
        context.renderChildren(node);
    }

    private void render(Paragraph node, NodeRendererContext context, HtmlWriter html) {
        boolean inTightList = listOptions.isInTightListItem(node);
        if (!inTightList && (!(node.getParent() instanceof ListItem) || !((ListItem) node.getParent()).isParagraphWrappingDisabled())) {
            html.srcPosWithEOL(node.getChars()).withAttr().tagLine("p", () -> {
                renderTextBlockParagraphLines(node, context, html);
            });
        } else {
            renderTextBlockParagraphLines(node, context, html);
        }
    }

    public static BasedSequence getSoftLineBreakSpan(Node node) {
        if (node == null) return NULL;

        Node lastNode = node;
        Node nextNode = node.getNext();

        while (nextNode != null && !(nextNode instanceof SoftLineBreak)) {
            lastNode = nextNode;
            nextNode = nextNode.getNext();
        }

        return Node.spanningChars(node.getChars(), lastNode.getChars());
    }

    private void render(SoftLineBreak node, NodeRendererContext context, HtmlWriter html) {
        if (context.getHtmlOptions().sourcePositionParagraphLines) {
            BasedSequence nextLine = getSoftLineBreakSpan(node.getNext());
            if (nextLine.isNotNull()) {
                html.tag("/span");
                html.raw(context.getHtmlOptions().softBreak);
                html.srcPos(nextLine).withAttr(PARAGRAPH_LINE).tag("span");
                return;
            }
        }
        html.raw(context.getHtmlOptions().softBreak);
    }

    private void render(HardLineBreak node, NodeRendererContext context, HtmlWriter html) {
        html.raw(context.getHtmlOptions().hardBreak);
    }

    private void render(Emphasis node, NodeRendererContext context, HtmlWriter html) {
        html.srcPos(node.getText()).withAttr().tag("em");
        context.renderChildren(node);
        html.tag("/em");
    }

    private void render(StrongEmphasis node, NodeRendererContext context, HtmlWriter html) {
        html.srcPos(node.getText()).withAttr().tag("strong");
        context.renderChildren(node);
        html.tag("/strong");
    }

    private void render(Text node, NodeRendererContext context, HtmlWriter html) {
        html.text(Escaping.normalizeEOL(node.getChars().unescape()));
    }

    private void render(TextBase node, NodeRendererContext context, HtmlWriter html) {
        context.renderChildren(node);
    }

    private void render(Code node, NodeRendererContext context, HtmlWriter html) {
        html.srcPos(node.getText()).withAttr().tag("code");
        html.text(Escaping.collapseWhitespace(node.getText(), true));
        html.tag("/code");
    }

    private void render(HtmlBlock node, NodeRendererContext context, HtmlWriter html) {
        if (node.hasChildren()) {
            // inner blocks handle rendering
            context.renderChildren(node);
        } else {
            renderHtmlBlock(node, context, html, context.getHtmlOptions().suppressHtmlBlocks, context.getHtmlOptions().escapeHtmlBlocks);
        }
    }

    private void render(HtmlCommentBlock node, NodeRendererContext context, HtmlWriter html) {
        renderHtmlBlock(node, context, html, context.getHtmlOptions().suppressHtmlCommentBlocks, context.getHtmlOptions().escapeHtmlCommentBlocks);
    }

    private void render(HtmlInnerBlock node, NodeRendererContext context, HtmlWriter html) {
        renderHtmlBlock(node, context, html, context.getHtmlOptions().suppressHtmlBlocks, context.getHtmlOptions().escapeHtmlBlocks);
    }

    private void render(HtmlInnerBlockComment node, NodeRendererContext context, HtmlWriter html) {
        renderHtmlBlock(node, context, html, context.getHtmlOptions().suppressHtmlCommentBlocks, context.getHtmlOptions().escapeHtmlCommentBlocks);
    }

    public void renderHtmlBlock(HtmlBlockBase node, NodeRendererContext context, HtmlWriter html, boolean suppress, boolean escape) {
        if (suppress) return;

        html.line();
        if (escape) {
            html.text(node.getContentChars().normalizeEOL());
        } else {
            html.raw(node.getContentChars().normalizeEOL());
        }
        html.line();
    }

    private void render(HtmlInline node, NodeRendererContext context, HtmlWriter html) {
        renderInlineHtml(node, context, html, context.getHtmlOptions().suppressInlineHtml, context.getHtmlOptions().escapeInlineHtml);
    }

    private void render(HtmlInlineComment node, NodeRendererContext context, HtmlWriter html) {
        renderInlineHtml(node, context, html, context.getHtmlOptions().suppressInlineHtmlComments, context.getHtmlOptions().escapeInlineHtmlComments);
    }

    public void renderInlineHtml(HtmlInlineBase node, NodeRendererContext context, HtmlWriter html, boolean suppress, boolean escape) {
        if (suppress) return;

        if (escape) {
            html.text(node.getChars().normalizeEOL());
        } else {
            html.raw(node.getChars().normalizeEOL());
        }
    }

    private void render(Reference node, NodeRendererContext context, HtmlWriter html) {

    }

    private void render(HtmlEntity node, NodeRendererContext context, HtmlWriter html) {
        html.text(node.getChars().unescape());
    }

    private void render(AutoLink node, NodeRendererContext context, HtmlWriter html) {
        String text = node.getText().toString();
        if (context.isDoNotRenderLinks()) {
            html.text(text);
        } else {
            ResolvedLink resolvedLink = context.resolveLink(LinkType.LINK, text, null);
            html.srcPos(node.getText()).attr("href", resolvedLink.getUrl())
                    .withAttr(resolvedLink)
                    .tag("a", () -> html.text(text));
        }
    }

    private void render(MailLink node, NodeRendererContext context, HtmlWriter html) {
        String text = node.getText().unescape();
        if (context.isDoNotRenderLinks()) {
            html.text(text);
        } else {
            ResolvedLink resolvedLink = context.resolveLink(LinkType.LINK, text, null);
            html.srcPos(node.getText()).attr("href", "mailto:" + resolvedLink.getUrl())
                    .withAttr(resolvedLink)
                    .tag("a")
                    .text(text)
                    .tag("/a");
        }
    }

    private void render(Image node, NodeRendererContext context, HtmlWriter html) {
        if (!context.isDoNotRenderLinks()) {
            String altText = new TextCollectingVisitor().collectAndGetText(node);

            ResolvedLink resolvedLink = context.resolveLink(LinkType.IMAGE, node.getUrl().unescape(), null);

            html.attr("src", resolvedLink.getUrl());
            html.attr("alt", altText);
            if (node.getTitle().isNotNull()) {
                html.attr("title", node.getTitle().unescape());
            }
            html.srcPos(node.getChars()).withAttr(resolvedLink).tagVoid("img");
        }
    }

    private void render(Link node, NodeRendererContext context, HtmlWriter html) {
        if (context.isDoNotRenderLinks()) {
            context.renderChildren(node);
        } else {
            ResolvedLink resolvedLink = context.resolveLink(LinkType.LINK, node.getUrl().unescape(), null);

            html.attr("href", resolvedLink.getUrl());
            if (node.getTitle().isNotNull()) {
                html.attr("title", node.getTitle().unescape());
            }
            html.srcPos(node.getChars()).withAttr(resolvedLink).tag("a");
            context.renderChildren(node);
            html.tag("/a");
        }
    }

    private void render(ImageRef node, NodeRendererContext context, HtmlWriter html) {
        if (!node.isDefined()) {
            // empty ref, we treat it as text
            assert !node.isDefined();
            html.text(node.getChars().unescape());
        } else {
            if (!context.isDoNotRenderLinks()) {
                Reference reference = node.getReferenceNode(referenceRepository);
                assert reference != null;
                String altText = new TextCollectingVisitor().collectAndGetText(node);

                ResolvedLink resolvedLink = context.resolveLink(LinkType.IMAGE, reference.getUrl().unescape(), null);

                html.attr("src", resolvedLink.getUrl());
                html.attr("alt", altText);
                if (reference.getTitle().isNotNull()) {
                    html.attr("title", reference.getTitle().unescape());
                }
                html.srcPos(node.getChars()).withAttr(resolvedLink).tagVoid("img");
            }
        }
    }

    private void render(LinkRef node, NodeRendererContext context, HtmlWriter html) {
        if (!node.isDefined()) {
            // empty ref, we treat it as text
            assert !node.isDefined();
            html.raw("[");
            context.renderChildren(node);
            html.raw("]");

            if (!node.isReferenceTextCombined()) {
                html.raw("[");
                html.raw(node.getReference().unescape());
                html.raw("]");
            }
        } else {
            if (context.isDoNotRenderLinks()) {
                context.renderChildren(node);
            } else {
                Reference reference = node.getReferenceNode(referenceRepository);
                assert reference != null;

                ResolvedLink resolvedLink = context.resolveLink(LinkType.LINK, reference.getUrl().unescape(), null);

                html.attr("href", resolvedLink.getUrl());
                if (reference.getTitle().isNotNull()) {
                    html.attr("title", reference.getTitle().unescape());
                }
                html.srcPos(node.getChars()).withAttr(resolvedLink).tag("a");
                context.renderChildren(node);
                html.tag("/a");
            }
        }
    }
}
