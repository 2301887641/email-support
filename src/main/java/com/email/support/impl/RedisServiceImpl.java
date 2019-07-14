package com.email.support.impl;

import com.alibaba.fastjson.JSON;
import com.email.support.entity.MailSendEntity;
import com.email.support.enums.UserPriorityEnums;
import com.email.support.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author suiguozhen
 * @date 19-7-14 下午2:57
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void addList(MailSendEntity mailSendEntity) {
        try{
            ListOperations<String, String> queue = redisTemplate.opsForList();
            UserPriorityEnums userPriorityEnums = UserPriorityEnums.getByPriority(mailSendEntity.getSendPriority());
            //返回的结果是最新的容器长度
            queue.rightPush(userPriorityEnums.name(), JSON.toJSONString(mailSendEntity));
        }catch (Exception e){
            throw new RuntimeException("redis error");
        }
    }
}
