package com.rhyme.fsbp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.rhyme.fsbp.mapper")
public class FsbpApplication {

    public static void main(String[] args) {
        SpringApplication.run(FsbpApplication.class, args);
    }

}
