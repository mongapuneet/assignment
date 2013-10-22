package com.walmartlabs.service;

import com.walmartlabs.repository.WordCountRepository;
import com.walmartlabs.stream.WordStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Scope("prototype")
public class WordStreamHandler implements Runnable {


    private String streamName;
    private WordStream wordStream;

    @Autowired
    private WordCountRepository wordRepository;
    private static Logger log = LoggerFactory.getLogger(WordStreamHandler.class);

    public WordStreamHandler(String streamName, WordStream wordStream) {
        this.streamName = streamName;
        this.wordStream = wordStream;
    }

    @Override
    public void run() {
        try {
            saveWordOccurrences();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void saveWordOccurrences() throws IOException, InterruptedException {
        while (wordStream.hasMoreWords()) {
            wordRepository.saveWordOccurrence(streamName, wordStream.nextWord());
        }
        log.info(streamName + " processed");
    }

}
