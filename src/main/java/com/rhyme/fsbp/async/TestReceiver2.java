package com.rhyme.fsbp.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TestReceiver2 {

    private static final Logger log = LoggerFactory.getLogger(TestReceiver2.class);

    @Async
    public void hello() {
       for (int i=0;i<100;i++){
           System.out.println("i am hello2 ****"+i);
       }
    }
}
