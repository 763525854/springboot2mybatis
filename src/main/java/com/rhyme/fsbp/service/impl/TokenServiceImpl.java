package com.rhyme.fsbp.service.impl;

import com.rhyme.fsbp.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public void createToken() {
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(token, token, 1000L);
    }

    @Override
    public void checkToken(String token) {
        token = redisTemplate.opsForValue().get(token);
        if (token == null || token.equals("")) {
            throw new RuntimeException("业务已经处理过了属于重复提交");
        } else {
            boolean b=redisTemplate.delete(token);
            if (b==false){
                throw new RuntimeException("高并发下，业务已经删除一次了。");
            }
        }
    }
}
