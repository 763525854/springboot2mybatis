package com.rhyme.fsbp.controller;

import com.rhyme.fsbp.model.TbUser;
import com.rhyme.fsbp.myinterface.Apidempotent;
import com.rhyme.fsbp.service.MyService;
import com.rhyme.fsbp.service.TokenService;
import com.rhyme.fsbp.service.TbUserService;
import io.swagger.annotations.*;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "Hello控制类")
@RestController
//@CrossOrigin
public class HelloController {
    private static Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    RedisTemplate<Object, Object> redisTemplate2;
    @Autowired
    private TokenService tokenService;
    @Autowired
    TbUserService userService;
    @Qualifier("myService1")
    @Autowired
    MyService myService;

    @ApiOperation("多种实现的注入选择")
    @GetMapping("/myservice")
    @ApiImplicitParams ({@ApiImplicitParam(name="params",required = true)})
    public void myservice(@ApiParam("入参参数")  String params) {
        logger.info("入参params：{}", params);
        myService.sayHello();
    }


    @GetMapping("/hello")
    public String hello() {
        TbUser tbUser = new TbUser();
        tbUser.setId(8L);
        tbUser.setUsername("haha");
        tbUser.setPassword("nihaoa");
        ArrayList arrayList = new ArrayList();
        arrayList.add(tbUser);
        arrayList.add(tbUser);
        arrayList.add(tbUser);
        arrayList.add(tbUser);
        String ok = "user";
        redisTemplate2.opsForValue().set(ok.toString(), arrayList);
        ArrayList arrayList2 = (ArrayList) redisTemplate2.opsForValue().get("user");
        System.out.println(arrayList2.size());
        redisTemplate.opsForValue().set("key", "value");
        return "hello world";
    }

    @GetMapping("/lock")
    public String lock() {
        Long counter = redisTemplate.opsForValue().increment("COUNTER", 1);
        System.out.println("haha " + counter);
//        RLock lock = redissonClient.getLock("TEST");
        try {
//            lock.lock();
            Thread.sleep(10); // 5 sec
        } catch (Exception ex) {
            logger.error("Error occurred");
        } finally {
//            lock.unlock();
        }
        return "lock-" + counter;
    }

    @GetMapping("/rhyme")
    public String rhyme() {
        if (redisTemplate.opsForValue().get("rhyme") != null)
            return "hello world";
        redisTemplate.opsForValue().set("rhyme", "rhyme");
        return "hello world";
    }

    @GetMapping("/bit")
    public void bit() {
        redisTemplate.opsForValue().getBit("mybit", 0);
    }

    @GetMapping("/hyperLogLog")
    public void hyperLogLog() {
        redisTemplate.opsForHyperLogLog().add("myHyperLogLog", "a");
    }

    @GetMapping("/mytoken")
    @Apidempotent
    public List<TbUser> mytoken(HttpServletRequest request) {
        return userService.findAll();
    }

}
