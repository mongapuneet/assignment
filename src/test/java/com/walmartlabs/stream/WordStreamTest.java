package com.walmartlabs.stream;


import com.walmartlabs.stream.WordStream;
import org.junit.Test;

import static com.walmartlabs.TestUtil.createWordStream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WordStreamTest {

    public static final String EMPTY_STRING = "";
    public static final String TEST_SENTENCE = "This is a test stream";

    @Test
    public void shouldIndicateIfStreamHasMoreWords() {
        assertTrue(createWordStream(TEST_SENTENCE).hasMoreWords());
    }

    @Test
    public void shouldIndicateIfStreamHasNoWords() {
        assertFalse(createWordStream(EMPTY_STRING).hasMoreWords());
    }

    @Test
    public void shouldReadOneWordFromStream() {
        assertEquals("Hello", createWordStream("Hello").nextWord());
    }

    @Test
    public void shouldReadMultipleWordsFromStream() {
        WordStream wordStream = createWordStream(TEST_SENTENCE);
        String[] expectedWords = {"This", "is", "a", "test", "stream"};
        int i = 0;
        while (wordStream.hasMoreWords()) {
            assertEquals(expectedWords[i], wordStream.nextWord());
            i++;
        }

        assertEquals("Number of words did not match", 5, i);
    }


    @Test
    public void shouldIgnoreContinuousSpaces() {
        WordStream wordStream = createWordStream("This is    a     test    stream      ");
        String[] expectedWords = {"This", "is", "a", "test", "stream"};
        int i = 0;
        while (wordStream.hasMoreWords()) {
            assertEquals(expectedWords[i], wordStream.nextWord());
            i++;
        }

        assertEquals("Number of words did not match", 5, i);
    }
}
