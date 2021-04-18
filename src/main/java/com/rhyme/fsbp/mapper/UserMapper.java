package com.rhyme.fsbp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rhyme.fsbp.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    @Select("select * from tb_user")
    List<User> findAll();
}
