package com.rhyme.fsbp.service.shiro;

import com.rhyme.fsbp.entity.Role;

/**
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/22 17:38
 */
public interface RoleService {
    public Role createRole(Role role);

    public void deleteRole(Long roleId);

    //添加角色-权限关系
    public void correlationPermissions(Long roleId, Long... permissionIds);

    //移除角色-权限关系
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
