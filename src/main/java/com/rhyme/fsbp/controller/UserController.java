package com.rhyme.fsbp.controller;

import com.rhyme.fsbp.entity.User;
import com.rhyme.fsbp.service.shiro.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/22 18:31
 */
@RestController
@RequestMapping("/shiroUser")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;

    @PostMapping("/crateShiroUser")
    public User createShiroUser(@RequestBody User user) {
        logger.info("创建用户请求参数request{}", user);
        return userService.createUser(user);
    }
}
