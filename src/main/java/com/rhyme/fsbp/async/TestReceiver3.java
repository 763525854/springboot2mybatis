package com.rhyme.fsbp.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
//@Scope("prototype")
public class TestReceiver3 {
    @Async
    public void hello() throws InterruptedException {
        while (true){
           Thread.sleep(3000);
            System.out.println("睡得好舒服");
            break;
        }
    }

    @Async
    public Future<String> helloFuture() throws InterruptedException {
        while (true){
            Thread.sleep(3000);
            System.out.println("睡得好舒服3");
            break;
        }
        return new AsyncResult<String>("i am testreceiver3 i have done");
    }

    @Async("threadPoolTaskExecutor2")
    public Future<String> helloFutureWithPool() throws InterruptedException {
        while (true){
            Thread.sleep(3000);
            System.out.println("睡得好舒服3");
            break;
        }
        return new AsyncResult<String>("i am testreceiver3 i use threadpool i have done");
    }
}
