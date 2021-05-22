package com.rhyme.fsbp.controller;

import com.rhyme.fsbp.entity.User;
import com.rhyme.fsbp.reqvo.UserAddRoleReqVo;
import com.rhyme.fsbp.service.shiro.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/22 18:31
 */
@RestController
@RequiresAuthentication
//@RequiresRoles("rhyme")
@RequestMapping("/shiroUser")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;

    //@RequiresRoles("admin")
    @PostMapping("/crateShiroUser")
    public User createShiroUser(@RequestBody User user) {
        logger.info("创建用户请求参数request{}", user);
        return userService.createUser(user);
    }

    @PostMapping("/userAddRole")
    public int userAddRole(@Validated @Valid @NotBlank @RequestBody UserAddRoleReqVo reqVo){
        return userService.userAddRole(reqVo);
    }
}
