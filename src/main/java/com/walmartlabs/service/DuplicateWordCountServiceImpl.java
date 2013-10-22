package com.walmartlabs.service;

import com.walmartlabs.repository.WordCountRepository;
import com.walmartlabs.stream.WordStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DuplicateWordCountServiceImpl implements DuplicateWordCountService {

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private WordCountRepository wordCountRepository;

    private static Logger log = LoggerFactory.getLogger(DuplicateWordCountServiceImpl.class);

    public boolean receiveStreamOfWords(String streamName, WordStream stream) {
        try {
            threadPoolTaskExecutor.execute(createWordStreamHandler(streamName, stream), 1000L);
        } catch (TaskRejectedException taskRejectedException) {
            log.error("Task Rejected by Thread Pool for stream " + streamName);
            return false;
        }
        return true;
    }

    @Override
    public Map<String, Integer> getDuplicateWordCount(String streamName) {
        return wordCountRepository.getDuplicateWordCount(streamName);
    }

    private WordStreamHandler createWordStreamHandler(String streamName, WordStream wordStream) {
        return (WordStreamHandler) applicationContext.getBean("wordStreamHandler", streamName, wordStream);
    }
}
