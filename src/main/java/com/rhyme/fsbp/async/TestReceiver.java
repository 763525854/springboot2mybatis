package com.rhyme.fsbp.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Random;
import java.util.concurrent.Future;

@Component
public class TestReceiver {

    private static final Logger log = LoggerFactory.getLogger(TestReceiver.class);

    @Async
    public void hello() {
        for (int i = 0; i < 100; i++) {
            System.out.println("i am hello1 ###" + i);
        }
    }

    @Async
    public Future<String> helloFuture() {
        for (int i = 0; i < 100; i++) {
            System.out.println("i am hello1 ###" + i);
        }
        return new AsyncResult<String>("i am testreceiver i have done");
    }

    @Async("threadPoolTaskExecutor1")
    public Future<String> helloFutureWithPool() {
        for (int i = 0; i < 100; i++) {
            System.out.println("i am hello1 ###" + i);
        }
        return new AsyncResult<String>("i am testreceiver i use threadpool i have done");
    }

    @Async("threadPoolTaskExecutor1")
    public Future<String> helloFutureWithPool2() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
        }
        Thread.sleep(2);
        return new AsyncResult<String>("i am testreceiver i use threadpool i have done");
    }

    @Async("threadPoolTaskExecutor1")
    public ListenableFuture<String> helloCallBack() throws InterruptedException {
        System.out.println("running hellocallback");
        Thread.sleep(10000);
        Random random = new Random();
        int r = random.nextInt(10);
        if (r < 5) {
            throw new RuntimeException("我太小了，我出bug了");
        } else {
            System.out.println("我刚刚好，成功执行");
            //do nothing
        }
        return new AsyncResult<String>("真棒");
    }
}
