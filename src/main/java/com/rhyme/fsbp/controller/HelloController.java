package com.rhyme.fsbp.controller;

import com.rhyme.fsbp.model.User;
import com.rhyme.fsbp.myinterface.Apidempotent;
import com.rhyme.fsbp.service.MyService;
import com.rhyme.fsbp.service.TokenService;
import com.rhyme.fsbp.service.UserService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
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
    UserService userService;
    @Qualifier("myService1")
    @Autowired
    MyService myService;

    @RequestMapping("/myservice")
    public void myservice() {
        myService.sayHello();
    }

    @RequestMapping("/hello")
    public String hello() {
        User user = new User();
        user.setId(8L);
        user.setUsername("haha");
        user.setPassword("nihaoa");
        ArrayList arrayList = new ArrayList();
        arrayList.add(user);
        arrayList.add(user);
        arrayList.add(user);
        arrayList.add(user);
        String ok = "user";
        redisTemplate2.opsForValue().set(ok.toString(), arrayList);
        ArrayList arrayList2 = (ArrayList) redisTemplate2.opsForValue().get("user");
        System.out.println(arrayList2.size());
        redisTemplate.opsForValue().set("key", "value");
        return "hello world";
    }

    @RequestMapping("/lock")
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

    @RequestMapping("/rhyme")
    public String rhyme() {
        if (redisTemplate.opsForValue().get("rhyme") != null)
            return "hello world";
        redisTemplate.opsForValue().set("rhyme", "rhyme");
        return "hello world";
    }

    @RequestMapping("/bit")
    public void bit() {
        redisTemplate.opsForValue().getBit("mybit", 0);
    }

    @RequestMapping("/hyperLogLog")
    public void hyperLogLog() {
        redisTemplate.opsForHyperLogLog().add("myHyperLogLog", "a");
    }

    @RequestMapping("/mytoken")
    @Apidempotent
    public List<User> mytoken(HttpServletRequest request) {
        return userService.findAll();
    }

}
