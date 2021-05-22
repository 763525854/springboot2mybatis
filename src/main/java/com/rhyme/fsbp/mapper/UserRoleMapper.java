package com.rhyme.fsbp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rhyme.fsbp.entity.Role;

import java.util.List;

/**
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/23 1:19
 */
public interface UserRoleMapper extends BaseMapper<Role> {
    List<Role> findAll();
}
