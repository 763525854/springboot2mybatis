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
 * @date 2021/5/22 17:45
 */
@Data
@TableName(value = "users")
public class User implements Serializable {
    //mybaits plus提供默认生成策略为雪花算法。
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String passwordSalt;
}
