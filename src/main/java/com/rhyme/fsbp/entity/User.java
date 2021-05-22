package com.rhyme.fsbp.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/22 17:45
 */
@Data
public class User implements Serializable {
    private String password;
}
