package com.rhyme.fsbp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rhyme.fsbp.model.TbUser;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbUserMapper extends BaseMapper<TbUser> {
    @Select("select * from tb_user")
    List<TbUser> findAll();
}
