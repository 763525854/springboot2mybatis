package com.rhyme.fsbp.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class TestReceiver {

    private static final Logger log = LoggerFactory.getLogger(TestReceiver.class);

    @Async
    public void hello() {
        for (int i=0;i<100;i++){
            System.out.println("i am hello1 ###"+i);
        }
    }

    @Async
    public Future<String> helloFuture() {
        for (int i=0;i<100;i++){
            System.out.println("i am hello1 ###"+i);
        }
        return new AsyncResult<String>("i am testreceiver i have done");
    }

    @Async("threadPoolTaskExecutor1")
    public Future<String> helloFutureWithPool() {
        for (int i=0;i<100;i++){
            System.out.println("i am hello1 ###"+i);
        }
        return new AsyncResult<String>("i am testreceiver i use threadpool i have done");
    }

    @Async("threadPoolTaskExecutor1")
    public Future<String> helloFutureWithPool2() throws InterruptedException {
        for (int i=0;i<100;i++){
        }
        Thread.sleep(2);
        return new AsyncResult<String>("i am testreceiver i use threadpool i have done");
    }
}
