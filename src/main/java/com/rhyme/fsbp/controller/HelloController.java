package com.rhyme.fsbp.controller;

import com.rhyme.fsbp.myinterface.Apidempotent;
import com.rhyme.fsbp.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    private TokenService tokenService;

    @RequestMapping("/hello")
    public String hello() {
        redisTemplate.opsForValue().set("key", "value");
        return "hello world";
    }

    @RequestMapping("/rhyme")
    public String rhyme() {
        if (redisTemplate.opsForValue().get("rhyme") != null)
            return "hello world";
        redisTemplate.opsForValue().set("rhyme", "rhyme");
        return "hello world";
    }

    @RequestMapping("/mytoken")
    @Apidempotent
    public String mytoken(HttpServletRequest request) {
        return "ok token";
    }

}
