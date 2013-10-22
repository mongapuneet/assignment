package com.walmartlabs.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StreamWordCounter {
    String streamName;
    Map<CharacterNode, Integer> wordCounts = new HashMap<CharacterNode, Integer>();

    private void StreamCount(String streamName) {
        this.streamName = streamName;
    }

    public void incrementCount(CharacterNode wordHandle) {
        if (wordCounts.containsKey(wordHandle)) {
            int wordCount = wordCounts.get(wordHandle).intValue();
            wordCounts.put(wordHandle, new Integer(wordCount + 1));
        } else {
            wordCounts.put(wordHandle, new Integer(1));
        }
    }

    public Set<CharacterNode> getWordHandles() {
        return wordCounts.keySet();
    }

    public int getCount(CharacterNode wordHandle) {
        return wordCounts.get(wordHandle);
    }
}
