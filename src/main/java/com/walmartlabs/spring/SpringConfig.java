package com.walmartlabs.spring;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

@Configuration
@ComponentScan(basePackages = "com.walmartlabs")
public class SpringConfig {

    public static final int DEFAULT_CORE_POOL_SIZE = 5;
    private static final int DEFAULT_MAX_POOL_SIZE = 10;
    private static final int DEFAULT_QUEUE_CAPACITY = 20;

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(getSystemProperty("corePoolSize", DEFAULT_CORE_POOL_SIZE));
        threadPoolTaskExecutor.setMaxPoolSize(getSystemProperty("maxPoolSize", DEFAULT_MAX_POOL_SIZE));
        threadPoolTaskExecutor.setQueueCapacity(getSystemProperty("queueCapacity", DEFAULT_QUEUE_CAPACITY));
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        return threadPoolTaskExecutor;
    }

    private int getSystemProperty(String propertyName, int defaultValue) {
        return parseInt(System.getProperty(propertyName, valueOf(defaultValue)));
    }
}
