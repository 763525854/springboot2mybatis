package com.rhyme.fsbp.controller;

import com.github.pagehelper.PageInfo;
import com.rhyme.fsbp.model.User;
import com.rhyme.fsbp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

    @RequestMapping(value = "/findAllPage",method = RequestMethod.POST)
    public PageInfo findAll(@RequestBody Map<String,Object> map){
        return userService.findAllPage(map);
    }
}
