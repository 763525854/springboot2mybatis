package com.rhyme.fsbp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rhyme
 * @version 1.0
 * @Description
 * @date 2021/5/2 15:13
 */
@RestController
public class TestUnifiedException {
    @GetMapping ("/willerror")
    public String getException(){
        throw new NullPointerException();
    }
}
