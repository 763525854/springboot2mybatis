package com.rhyme.fsbp.service.shiro;

import org.apache.shiro.authz.Permission;

/**
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/22 17:36
 */
public interface PermissionService {
    public Permission createPermission(Permission permission);
    public void deletePermission(Long permissionId);
}
