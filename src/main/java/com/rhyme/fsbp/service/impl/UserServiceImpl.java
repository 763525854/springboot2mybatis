package com.rhyme.fsbp.service.impl;

import com.rhyme.fsbp.mapper.UserMapper;
import com.rhyme.fsbp.model.User;
import com.rhyme.fsbp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
}
