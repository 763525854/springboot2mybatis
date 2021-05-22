package com.rhyme.fsbp.controller;

import com.rhyme.fsbp.model.TbUser;
import com.rhyme.fsbp.service.UserService;
import com.rhyme.fsbp.vo.UserReqVo;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Api(tags = "User控制类")
@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;

    @GetMapping("insertuser")
    public void insertUser(@RequestParam String enable) {
        logger.info("insertuser的请求参数request:{}", enable);
        userService.insert(enable);
    }

    @GetMapping("/findAll")
    public List<TbUser> findAll() {
        return userService.selectAll();
    }

    @GetMapping("/findOne")
    public TbUser findOne(Long id) {
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