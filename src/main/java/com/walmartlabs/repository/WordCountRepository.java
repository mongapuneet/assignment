package com.walmartlabs.repository;

import com.walmartlabs.domain.CharacterNode;
import com.walmartlabs.domain.StreamWordCounter;
import com.walmartlabs.domain.WordTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WordCountRepository {

    private WordTree tree = new WordTree();

    private Map<String, StreamWordCounter> countMap = new HashMap<String, StreamWordCounter>();
    private static Logger log = LoggerFactory.getLogger(WordCountRepository.class);

    public Map<String, Integer> getDuplicateWordCount(String streamName) {
        return getWordCountInternal(streamName, true);
    }

    public void saveWordOccurrence(String streamName, String word) {
        CharacterNode characterNode = tree.insertWord(word);
        log.info("Updating count for word " + word + " in stream " + streamName);
        keepTrackOfWordCount(streamName, characterNode);
    }

    private void keepTrackOfWordCount(String streamName, CharacterNode wordHandle) {
        if (!countMap.containsKey(streamName)) {
            log.info("First word received for stream " + streamName);
            countMap.put(streamName, new StreamWordCounter());
        }
        StreamWordCounter streamCount = this.countMap.get(streamName);
        streamCount.incrementCount(wordHandle);
        log.info("word count incremented");
    }

    public Map<String, Integer> getWordCount(String streamName) {
        return getWordCountInternal(streamName, false);
    }

    private Map<String, Integer> getWordCountInternal(String streamName, boolean keepOnlyDuplicateWords) {
        Map<String, Integer> result = new HashMap<String, Integer>();
        StreamWordCounter streamWordCounter = this.countMap.get(streamName);
        if (streamWordCounter != null) {
            for (CharacterNode wordHandle : streamWordCounter.getWordHandles()) {
                String word = tree.getWord(wordHandle);
                int count = streamWordCounter.getCount(wordHandle);
                if (keepOnlyDuplicateWords) {
                    if (count > 1) {
                        result.put(word, count);
                    }
                } else {
                    result.put(word, count);
                }
            }
        }
        return result;
    }
}
