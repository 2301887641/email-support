package com.email.support.impl;

import com.email.support.annotation.SlaveDatasource;
import com.email.support.entity.MailSendEntity;
import com.email.support.mapper.MailSendAMapper;
import com.email.support.mapper.MailSendBMapper;
import com.email.support.service.MailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author suiguozhen
 * @date 19/07/13
 */
@Service
public class MailSendServiceImpl implements MailSendService {

    private Integer countMapper = 2;

    @Autowired
    private MailSendAMapper mailSendAMapper;
    @Autowired
    private MailSendBMapper mailSendBMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void insert(MailSendEntity mailSendEntity) {
        int hashCode = mailSendEntity.hashCode();
        //取模运算 决定插入哪个数据库
        if (hashCode % countMapper > 0) {
            mailSendAMapper.insert(mailSendEntity);
        }else{
            mailSendBMapper.insert(mailSendEntity);
        }
        //将数据投递到redis
//        ListOperations<String, String> queue = redisTemplate.opsForList();

    }
}
