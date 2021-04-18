package com.rhyme.fsbp.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "tb_user")

public class User implements Serializable {
    private Long id;
    private String username;
    private String password;
}
