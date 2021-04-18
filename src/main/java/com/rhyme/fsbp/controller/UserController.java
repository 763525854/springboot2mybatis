package com.rhyme.fsbp.controller;

import com.rhyme.fsbp.model.User;
import com.rhyme.fsbp.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "User控制类")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public List<User> findAll(){
        return userService.selectAll();
    }

    @GetMapping("/findOne")
    public User findOne(Long id){
        return userService.findOne(id);
    }

}