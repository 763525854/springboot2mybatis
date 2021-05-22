package com.rhyme.fsbp.service;

import com.rhyme.fsbp.model.TbUser;

import java.util.List;

public interface UserService {
    List<TbUser> findAll();
    List<TbUser> selectAll();
    TbUser findOne(Long id);
    int insert(String enable);
}
