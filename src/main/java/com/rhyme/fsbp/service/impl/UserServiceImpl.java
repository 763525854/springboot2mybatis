package com.rhyme.fsbp.service.impl;

import com.rhyme.fsbp.async.TestReceiver;
import com.rhyme.fsbp.mapper.TbUserMapper;
import com.rhyme.fsbp.model.TbUser;
import com.rhyme.fsbp.service.TbUserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service("tbUserService")
public class UserServiceImpl implements TbUserService {
    @Resource
    private TbUserMapper tbUserMapper;
    @Resource
    private TestReceiver testReceiver;

    @Override
    public List<TbUser> findAll() {
        return tbUserMapper.findAll();
    }

    @Override
    public List<TbUser> selectAll() {
        return tbUserMapper.selectList(null);
    }

    @Override
    @Cacheable(value = "user", key = "#id")
    public TbUser findOne(Long id) {
        return tbUserMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(String enable) {
        TbUser tbUser1 = new TbUser();
        tbUser1.setId(3L);
        tbUser1.setUsername("haha");
        tbUser1.setPassword("password");
        int i = tbUserMapper.insert(tbUser1);
        System.out.println(i);
        try {
            Thread.sleep(1000 * 8);
        } catch (InterruptedException e) {
            System.out.println("睡着了，出了异常不怪我");
        }
        if (enable.equals("enable")) {
            testReceiver.testException();
        } else {
            HashMap hashMap = new HashMap();
            hashMap.get("asdfsa").toString();
        }
        TbUser tbUser2 = new TbUser();
        tbUser2.setId(6L);
        tbUser2.setUsername("dodo");
        tbUser2.setPassword("youlike");
        int j = tbUserMapper.insert(tbUser2);
        return i + j;
    }
}
