package com.rhyme.fsbp.mapper;

import com.rhyme.fsbp.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    List<User> findAll();
}
