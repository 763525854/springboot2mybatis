package com.rhyme.fsbp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rhyme.fsbp.mapper.UserMapper;
import com.rhyme.fsbp.model.User;
import com.rhyme.fsbp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public PageInfo findAllPage(Map<String, Object> queryParams) {
        int index = Integer.parseInt(queryParams.get("page").toString());
        int size = Integer.parseInt(queryParams.get("rows").toString());
        PageHelper.startPage(index, size);
        StringBuilder sql = new StringBuilder();
        List<User> commList = userMapper.findAll();
        PageInfo<User> page = new PageInfo(commList) ;
        return page;
    }
}
