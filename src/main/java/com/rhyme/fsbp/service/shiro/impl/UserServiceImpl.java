package com.rhyme.fsbp.service.shiro.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rhyme.fsbp.entity.Role;
import com.rhyme.fsbp.entity.User;
import com.rhyme.fsbp.exception.GolabException;
import com.rhyme.fsbp.mapper.UserMapper;
import com.rhyme.fsbp.mapper.UserRoleMapper;
import com.rhyme.fsbp.reqvo.UserAddRoleReqVo;
import com.rhyme.fsbp.service.shiro.UserService;
import com.rhyme.fsbp.util.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/22 17:50
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    /**
     * 创建用户
     *
     * @param user
     * @return
     */
    @Override
    public User createUser(User user) {
        user.setPassword(MD5Utils.encryptToString(user.getPassword()));
        int i = userMapper.insert(user);
        logger.info("创建用户:{}，并返回:{}", user, i);
        Wrapper<User> wrapper = new QueryWrapper<User>().eq("username", user.getUsername());
        return userMapper.selectOne(wrapper);
    }

    /**
     * 改密码
     *
     * @param userId
     * @param newPassword
     */
    @Override
    public void changePassword(Long userId, String newPassword) {
        User user = userMapper.selectById(userId);
        user.setPassword(MD5Utils.encryptToString(newPassword));
        int i = userMapper.updateById(user);
        logger.info("更新:{}，并返回:{}", user, i);
    }

    /**
     * 添加用户角色
     *
     * @param userId
     * @param roleIds
     */
    @Override
    public void correlationRoles(Long userId, Long... roleIds) {

    }

    /**
     * 移除-用户角色
     *
     * @param userId
     * @param roleIds
     */
    @Override
    public void uncorrelationRoles(Long userId, Long... roleIds) {

    }

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        Wrapper<User> wrapper = new QueryWrapper<User>().eq("username", username);
        return userMapper.selectOne(wrapper);
    }

    /**
     * 根据用户名查找角色
     *
     * @param username
     * @return
     */
    @Override
    public Set<String> findRoles(String username) {
        return null;
    }

    /**
     * 根据用户名查找权限
     *
     * @param username
     * @return
     */
    @Override
    public Set<String> findPermissions(String username) {
        return null;
    }

    /**
     * 给用户添加角色
     *
     * @param reqVo
     */
    @Override
    public int userAddRole(UserAddRoleReqVo reqVo) {
        Wrapper<User> wrapper = new QueryWrapper<User>().eq("username", reqVo.getUsername());
        User user = userMapper.selectOne(wrapper);
        if (null == user) {
            throw new GolabException("该用户不存在");
        }
        Wrapper<Role> roleWrapper = new QueryWrapper<Role>().eq("username", reqVo.getUsername()).eq("role_name", reqVo.getRoleName());
        Role role = userRoleMapper.selectOne(roleWrapper);
        if (null != role) {
            throw new GolabException("该用户角色已存在");
        }
        Role roleEntity=new Role();
        roleEntity.setRoleName(reqVo.getRoleName());
        roleEntity.setUsername(reqVo.getUsername());
        return userRoleMapper.insert(roleEntity);
    }
}
