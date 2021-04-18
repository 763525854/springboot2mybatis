package com.rhyme.fsbp;

import com.rhyme.fsbp.async.TestReceiver;
import com.rhyme.fsbp.async.TestReceiver2;
import com.rhyme.fsbp.async.TestReceiver3;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.rhyme.fsbp.mapper")
@EnableAsync
@EnableScheduling
public class FsbpApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(FsbpApplication.class, args);
//        TestReceiver t1 = context.getBean(TestReceiver.class);
//        TestReceiver2 t2 = context.getBean(TestReceiver2.class);
//        t1.hello();
//        t2.hello();
        TestReceiver3 t3 = context.getBean(TestReceiver3.class);
        TestReceiver3 t4 = context.getBean(TestReceiver3.class);
//        System.out.println(t3 == t4);
//        t3.hello();
//        t4.hello();
    }

}
