package com.rhyme.fsbp.controller;

import com.rhyme.fsbp.async.TestReceiver;
import com.rhyme.fsbp.async.TestReceiver2;
import com.rhyme.fsbp.async.TestReceiver3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/4 9:53
 */
@RestController
@RequestMapping("/async")
public class AsyncController {
    private static Logger logger = LoggerFactory.getLogger(AsyncController.class);
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
}
