package com.rhyme.fsbp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rhyme.fsbp.mapper.UserMapper;
import com.rhyme.fsbp.model.User;
import com.rhyme.fsbp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectList(null);
    }

    @Override
    @Cacheable(value = "user", key = "#id")
    public User findOne(Long id) {
        return userMapper.selectById(id);
    }
}
