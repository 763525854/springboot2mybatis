package com.rhyme.fsbp.mapper;

import com.rhyme.fsbp.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("select * from tb_user")
    //Results注解去掉也可以自动映射
    @Results({@Result(property = "userName", column = "username"),
            @Result(property = "password", column = "password")})
    List<User> findAll();
}
