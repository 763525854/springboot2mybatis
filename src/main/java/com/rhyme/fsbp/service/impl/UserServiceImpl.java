package com.rhyme.fsbp.service.impl;

import com.rhyme.fsbp.dao.UserRepository;
import com.rhyme.fsbp.model.User;
import com.rhyme.fsbp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CacheManager cacheManager;

    @Override
    public User findByName() {
        User u1 = userRepository.findByName("rhyme");
        System.out.println("第一次查询：" + u1.getAge());
        User u2 = userRepository.findByName("rhyme");
        System.out.println("第二次查询：" + u2.getAge());
        return u2;
    }
}
