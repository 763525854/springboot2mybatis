package com.rhyme.fsbp.config;

import com.rhyme.fsbp.handler.AsyncExceptionHandler;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 配置线程池，处理无返回值得异常情况，
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/4 16:29
 */
@Configuration
@EnableAsync
public class SpringAsyncConfig implements AsyncConfigurer {
    //此处可以用@Bean代替 executor.initialize()改行代码，进而初始化
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("MyAsyncExecutorThread-");
        executor.initialize();
        return executor;
    }

    //配置异常处理代理
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncExceptionHandler();
    }
}
