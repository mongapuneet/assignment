package com.walmartlabs;

import com.walmartlabs.stream.WordStream;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class TestUtil {
    public static WordStream createWordStream(String sentence) {
        InputStream inputStream = new ByteArrayInputStream(bytes(sentence));
        return new WordStream(inputStream);
    }

    private static byte[] bytes(String string) {
        return string.getBytes();
    }
}
