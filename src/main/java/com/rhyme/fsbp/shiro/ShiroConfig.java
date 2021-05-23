package com.rhyme.fsbp.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.Md5CredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/5 13:21
 */
@Configuration
public class ShiroConfig {
    @Bean
    MyRealm myRealm() {
        MyRealm myRealm = new MyRealm();
        HashedCredentialsMatcher matcher=new HashedCredentialsMatcher("MD5");
        myRealm.setCredentialsMatcher(matcher);
        return myRealm;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm());
        return manager;
    }

    @Bean
    ShiroFilterChainDefinition ShiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition bean = new DefaultShiroFilterChainDefinition();
        bean.addPathDefinition("/doLogin", "anon");
        bean.addPathDefinition("/**", "authc");
        return bean;
    }
}
