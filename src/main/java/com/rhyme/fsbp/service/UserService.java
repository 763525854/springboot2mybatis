package com.rhyme.fsbp.service;

import com.rhyme.fsbp.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    List<User> selectAll();
    User findOne(Long id);
}
