package com.vladsch.flexmark.test;

import com.vladsch.flexmark.spec.SpecExample;
import com.vladsch.flexmark.spec.SpecReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public abstract class ComboSpecTestCase extends FullSpecTestCase {
    public static final String SPEC_RESOURCE = "/ast_spec.md";

    private DumpSpecReader dumpSpecReader;
    protected final SpecExample example;

    /**
     * @return return resource name for the spec to use for the examples of the test
     */
    protected abstract String getSpecResourceName();

    public ComboSpecTestCase(SpecExample example) {
        this.example = example;
    }

    protected SpecExample example() {
        return example;
    }

    @Parameterized.Parameters(name = "{0}")
    public static List<Object[]> data() {
        List<SpecExample> examples = SpecReader.readExamples();
        List<Object[]> data = new ArrayList<>();

        // NULL example runs full spec test
        data.add(new Object[] { SpecExample.NULL });

        for (SpecExample example : examples) {
            data.add(new Object[] { example });
        }
        return data;
    }

    @Override
    public SpecReader create(InputStream inputStream) {
        dumpSpecReader = new DumpSpecReader(inputStream, this);
        return dumpSpecReader;
    }

    /**
     * @return return true if actual result spec used in comparison should be output to stdout
     */
    protected boolean outputActualFullSpec() {
        return false;
    }

    protected boolean includeExampleCoords() {
        return true;
    }

    protected String readResource(String resourcePath) {
        try (InputStream stream = ComboSpecTestCase.class.getResourceAsStream(resourcePath)) {
            return readStream(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected String readStream(InputStream stream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, Charset.forName("UTF-8")))) {
            String line;
            StringBuilder out = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                out.append(line).append('\n');
            }

            return out.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testFullSpec() throws Exception {
        if (!example.isFullSpecExample()) return;

        String specResourcePath = getSpecResourceName();
        SpecReader.readExamples(specResourcePath, this);
        String fullSpec = SpecReader.readSpec(specResourcePath);
        String actual = dumpSpecReader.getFullSpec();

        if (outputActualFullSpec()) {
            System.out.println(actual);
        }

        assertEquals(fullSpec, actual);
    }

    @Test
    public void testHtmlRendering() {
        if (!example.isSpecExample()) return;

        if (example.getAst() != null) {
            assertRenderingAst(example.getSource(), example.getHtml(), example.getAst(), example.getOptionsSet());
        } else {
            assertRendering(example.getSource(), example.getHtml(), example.getOptionsSet());
        }
    }
}
