package com.rhyme.fsbp.controller;

import com.rhyme.fsbp.model.User;
import com.rhyme.fsbp.service.UserService;
import com.rhyme.fsbp.vo.UserReqVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Api(tags = "User控制类")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public List<User> findAll() {
        return userService.selectAll();
    }

    @GetMapping("/findOne")
    public User findOne(Long id) {
        return userService.findOne(id);
    }

    @GetMapping("/validation1")
    public String validation1(@RequestParam @Valid @NotNull String name) {
        return "validation1";
    }

    @GetMapping("/validation2")
    public String validation2(@RequestParam @Valid UserReqVo userReqVo) {
        return "validation2";
    }
}