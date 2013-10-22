package com.walmartlabs.service;

import com.walmartlabs.stream.WordStream;

import java.util.Map;

public interface DuplicateWordCountService {
    public boolean receiveStreamOfWords(String streamName, WordStream wordStream);

    public Map<String,Integer> getDuplicateWordCount(String streamName);
}
