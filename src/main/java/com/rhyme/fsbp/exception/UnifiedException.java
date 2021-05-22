package com.rhyme.fsbp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/2 13:49
 */
@RestControllerAdvice
public class UnifiedException {
    private final static Logger log = LoggerFactory.getLogger(UnifiedException.class);

    @ExceptionHandler
    public ResultEntity handler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        log.info("restful http请求发生异常....");
        if (response.getStatus() == HttpStatus.BAD_REQUEST.value()) {
            log.info("修改返回值状态值为200");
            response.setStatus(HttpStatus.OK.value());
        }
        if (e instanceof NullPointerException) {
            log.error("");
            return ResultEntity.fail("空指针异常");
        }else if (e instanceof IllegalArgumentException){
            return ResultEntity.fail("请求参数不对");
        }else if (e instanceof SQLException){
            return ResultEntity.fail("数据库访问异常");
        }else if (e instanceof GolabException){
            return ResultEntity.fail("golab exception");
        }else {
            return ResultEntity.fail("服务器发生异常，请联系管理员");
        }

    }
}
