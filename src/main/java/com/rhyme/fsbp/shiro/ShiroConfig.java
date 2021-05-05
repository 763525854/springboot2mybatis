package com.rhyme.fsbp.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
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
        MyRealm myRealm=new MyRealm();
        return myRealm;
    }

    @Bean
    SecurityManager securityManager(){
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setRealm(myRealm());
        return manager;
    }

    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/index");
        bean.setUnauthorizedUrl("/unauthorizedurl");
        Map<String,String> map=new LinkedHashMap<>();
        map.put("/doLogin","anon");
        map.put("/**","authc");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }
}
