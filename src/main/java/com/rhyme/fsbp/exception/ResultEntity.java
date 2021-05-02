package com.rhyme.fsbp.exception;

import lombok.Data;

/**
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/2 14:38
 */
@Data
public class ResultEntity {
    private final static ResultEntity resultEntity = new ResultEntity();
    private String message;

    public static ResultEntity fail(String message) {
        resultEntity.setMessage(message);
        return resultEntity;
    }
}
