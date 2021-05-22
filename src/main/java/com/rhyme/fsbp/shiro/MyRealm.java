package com.rhyme.fsbp.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/5 13:13
 */
public class MyRealm extends AuthorizingRealm {
    Logger logger = LoggerFactory.getLogger(MyRealm.class);

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Object principal = principalCollection.getPrimaryPrincipal();
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken){
        authenticationToken.getPrincipal();
        String username = (String) authenticationToken.getPrincipal();
        if (!"javaboy".equals(username)) {
            throw new UnknownAccountException("账户不存在!");
        }
        Object password = new String((char[]) authenticationToken.getCredentials());
        logger.info("password is {}", password);
        if (!password.equals("123")) {
            throw new IncorrectCredentialsException();
        }
        System.out.println(password);
        /*UsernamePasswordToken token = new UsernamePasswordToken("", "");
        try {
            SecurityUtils.getSubject().login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }*/
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
