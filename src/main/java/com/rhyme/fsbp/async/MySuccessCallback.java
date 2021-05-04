package com.rhyme.fsbp.async;

import org.springframework.util.concurrent.SuccessCallback;

/**
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/4 15:14
 */
public class MySuccessCallback implements SuccessCallback<String> {

    @Override
    public void onSuccess(String result) {
        System.out.println("MySuccessCallback is running");
    }
}
