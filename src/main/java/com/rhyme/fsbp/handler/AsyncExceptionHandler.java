package com.rhyme.fsbp.handler;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/4 16:33
 */
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    //处理相关异常。
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        if (ex instanceof Exception) {
            System.out.println("完犊子了" + ex.getMessage());
        }
    }
}
