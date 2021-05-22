package com.rhyme.fsbp.reqvo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/23 1:32
 */
@Data
public class UserAddRoleReqVo {
    @NotBlank(message = "username不能为空")
    @NotNull
    private String username;
    @NotBlank(message = "roleName不能为空")
    @NotNull
    private String roleName;
}
