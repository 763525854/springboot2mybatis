package com.rhyme.fsbp.timedtask;

import com.rhyme.fsbp.model.User;
import com.rhyme.fsbp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

//@Component
public class Task1 {
    @Autowired
    private UserService userService;
    @Scheduled(fixedRate = 2000)
    public void fixedRate() {
        System.out.println("fixedRate>>>" + new Date());
    }

    @Scheduled(fixedDelay = 2000)
    public void fixedDelay() {
        System.out.println("fixedDelay>>>" + new Date());
    }

    @Scheduled(initialDelay = 2000, fixedDelay = 2000)
    public void initialDelay() {
        System.out.println("initialDelay>>>" + new Date());
    }

    @Scheduled(cron = "0/5 * * * * *")
    public void cron() {
        System.out.println("cron>>>" + new Date());
    }

    @Scheduled(cron = "0/5 * * * * *")
    public void crongetUser() {
        List<User> users=userService.findAll();
        for (int i=0;i<users.size();i++){
            User user=users.get(i);
            System.out.println("myname is "+user.getUsername()+", my password is "+user.getPassword());
        }
    }
}
