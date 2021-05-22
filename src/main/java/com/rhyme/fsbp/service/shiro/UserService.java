package com.rhyme.fsbp.service.shiro;

import com.rhyme.fsbp.entity.User;

import java.util.Set;

/**
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/22 17:44
 */
public interface UserService {
    /**
     * 创建用户
     *
     * @param user
     * @return
     */
    public User createUser(User user);

    /**
     * 改密码
     *
     * @param userId
     * @param newPassword
     */
    public void changePassword(Long userId, String newPassword);

    /**
     * 添加用户角色
     *
     * @param userId
     * @param roleIds
     */
    public void correlationRoles(Long userId, Long... roleIds);

    /**
     * 移除-用户角色
     *
     * @param userId
     * @param roleIds
     */
    public void uncorrelationRoles(Long userId, Long... roleIds);

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 根据用户名查找角色
     *
     * @param username
     * @return
     */
    public Set<String> findRoles(String username);

    /**
     * 根据用户名查找权限
     *
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username);
}
