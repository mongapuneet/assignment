package com.walmartlabs.service;

import com.walmartlabs.repository.WordCountRepository;
import com.walmartlabs.spring.SpringConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

import static com.walmartlabs.TestUtil.createWordStream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DuplicateWordCountServiceImplTest {

    private DuplicateWordCountService service;

    @Before
    public void setUp() {
        System.setProperty("corePoolSize", String.valueOf(10));
        System.setProperty("maxPoolSize", String.valueOf(50));
        System.setProperty("queueCapacity", String.valueOf(30));

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        service = context.getBean(DuplicateWordCountService.class);
    }

    @Test
    public void shouldGetDuplicateWordsWithCounts() {
        service.receiveStreamOfWords("Stream 1", createWordStream("This is This is test This is"));
        waitFor(seconds(2));
        Map<String, Integer> wordCountMap = service.getDuplicateWordCount("Stream 1");

        assertEquals(3, wordCountMap.get("this").intValue());
        assertEquals(3, wordCountMap.get("is").intValue());
        assertFalse(wordCountMap.containsKey("test"));
    }

    private void waitFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private long seconds(int seconds) {
        return seconds * 1000L;
    }


}
