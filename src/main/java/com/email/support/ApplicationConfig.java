package com.email.support;

import com.fasterxml.uuid.Generators;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.net.UnknownHostException;
import java.util.UUID;


/**
 * 主配置文件
 *
 * @author suiguozhen
 * @date 19-7-4 下午9:21
 */
@Configuration
@EnableWebMvc
@MapperScan(basePackages = {"com.email.support.mapper"})
public class ApplicationConfig {

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(stringRedisSerializer);
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
