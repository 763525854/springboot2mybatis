package com.rhyme.fsbp;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FsbpApplication.class)
class FsbpApplicationTests {
    @Autowired
    StringEncryptor encryptor;
    @Test
    public void contextLoads() {
        String url=encryptor.encrypt("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC");
        String name = encryptor.encrypt("root");
        String password = encryptor.encrypt("root");
        System.out.println("  "+url+"  ");
        System.out.println("  "+name+" ");
        System.out.println("  "+password+"  ");
    }

}
