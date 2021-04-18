package com.rhyme.fsbp.service.impl;

import com.rhyme.fsbp.service.MyService;
import org.springframework.stereotype.Service;

@Service
public class MyService2 implements MyService {
    @Override
    public void sayHello() {
        System.out.println("i am myservice2");
    }
}
