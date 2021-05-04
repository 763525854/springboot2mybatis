package com.rhyme.fsbp.async;

import org.springframework.util.concurrent.FailureCallback;

/**
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/4 15:18
 */
public class MyFailCallback implements FailureCallback {
    @Override
    public void onFailure(Throwable ex) {
        if (ex instanceof RuntimeException) {
            System.out.println("MyFailCallback is running");
        }
    }
}
