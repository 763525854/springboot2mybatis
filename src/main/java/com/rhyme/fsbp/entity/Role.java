package com.rhyme.fsbp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/22 17:41
 */
@Data
@TableName(value = "user_roles")
public class Role implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String roleName;
}
