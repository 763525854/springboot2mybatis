package com.rhyme.fsbp.controller;

import com.rhyme.fsbp.model.MyVue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/2 22:10
 */
@RestController
@CrossOrigin
public class VueController {
    private static final Logger logger = LoggerFactory.getLogger(VueController.class);
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @PostMapping("/myvue")
    public String vue(@RequestBody MyVue myVue, HttpServletRequest request) {
        logger.info("request paroms is {}", myVue);
        long timestamp = Long.valueOf(request.getHeader("timestamp")).longValue();
        String nonceReq=request.getHeader("nonce");
        logger.info("timestamp value is {}",timestamp);
        logger.info("nonceReq value is {}",nonceReq);
        boolean nonceBoolean=redisTemplate.hasKey(nonceReq);
        if (nonceBoolean==true){
            throw new RuntimeException("nonce已存在，此次请求为重复请求");
        }else {
            redisTemplate.opsForValue().set(nonceReq,"0", 60L,TimeUnit.SECONDS);
        }
        if ((System.currentTimeMillis() - timestamp) > 30 * 1000) {
            throw new RuntimeException("请勿重复访问该接口");
        }
        if (null != myVue) {
            System.out.println();
            return "你真棒";
        } else {
            return "hello";
        }
    }
}
