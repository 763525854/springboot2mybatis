package com.rhyme.fsbp.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
//@Scope("prototype")
public class TestReceiver3 {
    @Async
    public void hello() throws InterruptedException {
        while (true){
           Thread.sleep(3000);
            System.out.println("睡得好舒服");
        }
    }
}
