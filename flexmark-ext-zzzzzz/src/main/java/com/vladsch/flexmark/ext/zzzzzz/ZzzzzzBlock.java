package com.vladsch.flexmark.ext.zzzzzz;

import com.vladsch.flexmark.ast.CustomBlock;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.SubSequence;

/**
 * A Zzzzzz block node
 */
public class ZzzzzzBlock extends CustomBlock {
    protected BasedSequence openingMarker = SubSequence.NULL;
    protected BasedSequence text = SubSequence.NULL;
    protected BasedSequence closingMarker = SubSequence.NULL;
    protected BasedSequence zzzzzz = SubSequence.NULL;
    private int zzzzzzOrdinal = 0;
    private int firstReferenceOffset = Integer.MAX_VALUE;

    public int getFirstReferenceOffset() {
        return firstReferenceOffset;
    }

    public void setFirstReferenceOffset(int firstReferenceOffset) {
        this.firstReferenceOffset = firstReferenceOffset;
    }

    public void addFirstReferenceOffset(int firstReferenceOffset) {
        if (this.firstReferenceOffset < firstReferenceOffset) this.firstReferenceOffset = firstReferenceOffset;
    }

    public boolean isReferenced() {
        return this.firstReferenceOffset < Integer.MAX_VALUE;
    }

    public int getZzzzzzOrdinal() {
        return zzzzzzOrdinal;
    }

    public void setZzzzzzOrdinal(int zzzzzzOrdinal) {
        this.zzzzzzOrdinal = zzzzzzOrdinal;
    }

    @Override
    public void getAstExtra(StringBuilder out) {
        out.append(" ordinal: " + zzzzzzOrdinal + " ");
        segmentSpan(out, openingMarker, "open");
        segmentSpan(out, text, "text");
        segmentSpan(out, closingMarker, "close");
        segmentSpan(out, zzzzzz, "zzzzzz");
    }

    @Override
    public BasedSequence[] getSegments() {
        return new BasedSequence[] { openingMarker, text, closingMarker, zzzzzz };
    }

    public ZzzzzzBlock() {
    }

    public ZzzzzzBlock(BasedSequence chars) {
        super(chars);
    }

    public ZzzzzzBlock(Node node) {
        super();
        appendChild(node);
        this.setCharsFromContent();
    }

    public BasedSequence getOpeningMarker() {
        return openingMarker;
    }

    public void setOpeningMarker(BasedSequence openingMarker) {
        this.openingMarker = openingMarker;
    }

    public BasedSequence getText() {
        return text;
    }

    public void setText(BasedSequence text) {
        this.text = text;
    }

    public BasedSequence getClosingMarker() {
        return closingMarker;
    }

    public void setClosingMarker(BasedSequence closingMarker) {
        this.closingMarker = closingMarker;
    }

    public BasedSequence getZzzzzz() {
        return zzzzzz;
    }

    public void setZzzzzz(BasedSequence zzzzzz) {
        this.zzzzzz = zzzzzz;
    }
}
