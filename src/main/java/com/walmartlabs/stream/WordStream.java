package com.walmartlabs.stream;

import java.io.IOException;
import java.io.InputStream;

public class WordStream {
    private static final int SPACE = ' ';
    private static final int END_OF_STREAM = -1;
    private static final int TAB = '\t';
    private int lastReadCharacter;
    private InputStream inputStream;

    public WordStream(InputStream inputStream) {
        this.inputStream = inputStream;
        lastReadCharacter = nextCharacter();
    }

    public boolean hasMoreWords() {
        return !isEndOfStream();
    }

    public String nextWord() {
        StringBuilder builder = new StringBuilder();
        while (!isEndOfStream() && !isWordBreak()) {
            builder.append((char) lastReadCharacter);
            lastReadCharacter = nextCharacter();
        }

        while(isWordBreak()) {
            lastReadCharacter = nextCharacter();
        }

        return builder.toString();
    }

    private int nextCharacter() {
        try {
            return inputStream.read();
        } catch (IOException e) {
            return END_OF_STREAM;
        }
    }

    private boolean isWordBreak() {
        return lastReadCharacter == SPACE || lastReadCharacter == TAB;
    }

    private boolean isEndOfStream() {
        return lastReadCharacter == END_OF_STREAM;
    }
}
