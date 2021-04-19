package com.rhyme.fsbp.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserReqVo {
    @NotNull
    private  String name;
}
