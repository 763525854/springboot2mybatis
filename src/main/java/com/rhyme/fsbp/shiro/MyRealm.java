package com.rhyme.fsbp.shiro;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rhyme.fsbp.entity.Role;
import com.rhyme.fsbp.entity.User;
import com.rhyme.fsbp.mapper.UserRoleMapper;
import com.rhyme.fsbp.service.shiro.UserService;
import com.rhyme.fsbp.util.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/5 13:13
 */
public class MyRealm extends AuthorizingRealm {
    Logger logger = LoggerFactory.getLogger(MyRealm.class);
    @Resource
    private UserService userService;
    @Resource
    private UserRoleMapper userRoleMapper;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String principal = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
       /* Wrapper<Role> roleWrapper = new QueryWrapper<Role>().eq("username", principal);
        List<Role> roles = userRoleMapper.selectList(roleWrapper);
        Set<String> set = new HashSet<>();
        for (Role role : roles) {
            set.add(role.getRoleName());
        }*/
        authorizationInfo.setRoles(userService.findRoles(principal));
        //authorizationInfo.setRoles(set);
        authorizationInfo.setStringPermissions(userService.findPermissions(principal));
        return authorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
        logger.info("shiro认证请求", authenticationToken);
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        logger.info("页面输入username is===={}", username);
        logger.info("页面输入password is===={}", password);
        User user = userService.findByUsername(username);
        logger.info("根据用户名{}查询数据库返回信息{}",username,user);
        if (user == null) {
            throw new UnknownAccountException("账户不存在!");
        }
        if (true == user.getLocked()) {
            throw new LockedAccountException();
        }
        if (!MD5Utils.encryptToString(password).equals(user.getPassword())) {
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(username, user.getPassword(), getName());
    }
}
