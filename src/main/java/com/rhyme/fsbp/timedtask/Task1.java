package com.rhyme.fsbp.timedtask;

import com.rhyme.fsbp.model.TbUser;
import com.rhyme.fsbp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

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
        List<TbUser> tbUsers =userService.findAll();
        for (int i = 0; i< tbUsers.size(); i++){
            TbUser tbUser = tbUsers.get(i);
            System.out.println("myname is "+ tbUser.getUsername()+", my password is "+ tbUser.getPassword());
        }
    }
}
