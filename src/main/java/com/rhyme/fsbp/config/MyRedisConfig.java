package com.rhyme.fsbp.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.UnknownHostException;
import java.util.Arrays;

@Configuration
@JsonIgnoreProperties(ignoreUnknown = true)
public class MyRedisConfig extends CachingConfigurerSupport {
    @Bean(name="redisTemplate2")
    public RedisTemplate<Object, Object> redisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();
        template.setDefaultSerializer(serializer);
        return template;
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            public Object generate(Object target, Method method, Object... objects) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName()).append(".").append(method.getName()).append(Arrays.toString(objects));
                return sb.toString();
            }
        };
    }

    @Autowired
    private Environment env;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() throws IOException {
        String[] profiles = env.getActiveProfiles();
        String profile = "";
        if(profiles.length > 0) {
            profile = "-" + profiles[0];
        }
        return Redisson.create(
                Config.fromYAML(new ClassPathResource("redis.yaml").getInputStream())
        );

//        RedissonClient redisson = Redisson.create();
//
//        Config config = new Config();
//        config.useSingleServer().setAddress("myredisserver:6379");
//        RedissonClient redisson = Redisson.create(config);
    }
}
