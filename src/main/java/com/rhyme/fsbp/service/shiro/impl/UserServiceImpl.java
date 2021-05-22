package com.rhyme.fsbp.service.shiro.impl;

import com.rhyme.fsbp.entity.User;
import com.rhyme.fsbp.service.shiro.UserService;
import com.rhyme.fsbp.util.MD5Utils;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/22 17:50
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * 创建用户
     *
     * @param user
     * @return
     */
    @Override
    public User createUser(User user) {
        user.setPassword(MD5Utils.encryptToString(user.getPassword()));
        return null;
    }

    /**
     * 改密码
     *
     * @param userId
     * @param newPassword
     */
    @Override
    public void changePassword(Long userId, String newPassword) {

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
     */
    @Override
    public void findByUsername(String username) {

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
}
