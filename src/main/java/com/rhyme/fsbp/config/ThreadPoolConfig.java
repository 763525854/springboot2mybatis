package com.rhyme.fsbp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/4 10:32
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {
    @Bean("threadPoolTaskExecutor1")
    public AsyncTaskExecutor taskExecutor1() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(10);
        return executor;
    }

    @Bean("threadPoolTaskExecutor2")
    public AsyncTaskExecutor taskExecutor2() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(10);
        return executor;
    }

}
