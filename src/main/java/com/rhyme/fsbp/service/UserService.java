package com.rhyme.fsbp.service;

import com.github.pagehelper.PageInfo;
import com.rhyme.fsbp.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> findAll();
    PageInfo findAllPage(Map<String, Object> queryParams);
}
