package com.walmartlabs.repository;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class WordCountRepositoryTest {

    @Test
    public void shouldCountWords() {
        WordCountRepository repository = new WordCountRepository();
        repository.saveWordOccurrence("Stream 1", "This");
        repository.saveWordOccurrence("Stream 1", "There");
        repository.saveWordOccurrence("Stream 1", "There");
        Map<String, Integer> wordCounts = repository.getWordCount("Stream 1");

        assertEquals(2, wordCounts.get("there").intValue());
    }

}
