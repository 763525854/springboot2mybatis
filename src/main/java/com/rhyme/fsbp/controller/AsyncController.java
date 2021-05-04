package com.rhyme.fsbp.controller;

import com.rhyme.fsbp.async.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.Future;

/**
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/4 9:53
 */
@RestController
@RequestMapping("/async")
public class AsyncController {
    private static final Logger logger = LoggerFactory.getLogger(AsyncController.class);
    @Resource
    private TestReceiver testReceiver;
    @Resource
    private TestReceiver2 testReceiver2;
    @Resource
    private TestReceiver3 testReceiver3;

    /**
     * 不带返回值的异步任务执行
     *
     * @throws InterruptedException
     */
    @RequestMapping(value = "/dotask1", method = RequestMethod.GET)
    public void doTask1() throws InterruptedException {
        long start = System.currentTimeMillis();
        testReceiver.hello();
        testReceiver2.hello();
        testReceiver3.hello();
        long end = System.currentTimeMillis();
        logger.info("task is use {}", end - start);
    }

    /**
     * 带返回值的异步任务执行。没有线程池。存在创建线程，销毁线程的开销，没有服用线程。
     *
     * @throws InterruptedException
     */
    @RequestMapping(value = "/dotask2", method = RequestMethod.GET)
    public void doTask2WithFuture() throws InterruptedException {
        long start = System.currentTimeMillis();
        Future<String> taskResult1 = testReceiver.helloFuture();
        Future<String> taskResult2 = testReceiver2.helloFuture();
        Future<String> taskResult3 = testReceiver3.helloFuture();
        while (true) {
            if (taskResult1.isDone() && taskResult2.isDone() && taskResult3.isDone()) {
                break;
            }
        }
        long end = System.currentTimeMillis();
        logger.info("task is use {}", end - start);
    }

    /**
     * 带返回值的异步任务执行，且用线程池创建线程。复用了线程
     *
     * @throws InterruptedException
     */
    @RequestMapping(value = "/dotask3", method = RequestMethod.GET)
    public void doTask2WithFutureAndThreadPool() throws InterruptedException {
        long start = System.currentTimeMillis();
        Future<String> taskResult1 = testReceiver.helloFutureWithPool();
        Future<String> taskResult2 = testReceiver2.helloFutureWithPool();
        Future<String> taskResult3 = testReceiver3.helloFutureWithPool();
        //只有当三个任务都执行完毕，才进行后续的主线程方法。适用于需要分支任务完成才能继续后续逻辑的场景。
        //假如task1执行需要3秒，task2要4秒，task3要5秒，该异步调用，能将串行执行需要12秒的时间减少到并行需要5秒。以最长的执行时间为结果。
        //如果不需要根据子task的执行完成结果来执行主线程后续任务，可以删除下面的while代码块
        while (true) {
            if (taskResult1.isDone() && taskResult2.isDone() && taskResult3.isDone()) {
                break;
            }
        }
        long end = System.currentTimeMillis();
        logger.info("task is use {}", end - start);
    }

    /**
     * 带返回值的异步任务执行，且用线程池创建线程,使用get方法，该异步调用会与主线程同步,执行时间，与doTask2WithFutureAndThreadPool,doTask2WithFuture一样
     *
     * @throws InterruptedException
     */
    @RequestMapping(value = "/dotask4", method = RequestMethod.GET)
    public void doTask2WithFutureAndThreadPool2() throws Exception {
        long start = System.currentTimeMillis();
        Future<String> taskResult1 = testReceiver.helloFutureWithPool2();
        Future<String> taskResult2 = testReceiver2.helloFutureWithPool2();
        Future<String> taskResult3 = testReceiver3.helloFutureWithPool2();
        String s = taskResult1.get().toString();
        System.out.println(s);
        s = taskResult2.get().toString();
        System.out.println(s);
        s = taskResult3.get().toString();
        System.out.println(s);
        long end = System.currentTimeMillis();
        logger.info("task is use {}", end - start);
    }

    /**
     * 增加对异步调用成功失败的处理，仅用于处理有返回值的异步调用
     * @throws Exception
     */
    @RequestMapping(value = "/dotask5", method = RequestMethod.GET)
    public void dotaskcallback() throws Exception {
        long start = System.currentTimeMillis();
        ListenableFuture<String> callback = testReceiver.helloCallBack();
        callback.addCallback(new MySuccessCallback(), new MyFailCallback());
        //System.out.println(callback.get().toString());
        long end = System.currentTimeMillis();
        logger.info("task is use {}", end - start);
    }
}
