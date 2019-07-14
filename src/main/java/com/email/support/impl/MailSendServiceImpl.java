package com.email.support.impl;

import com.email.support.annotation.SlaveDatasource;
import com.email.support.entity.MailSendEntity;
import com.email.support.enums.UserStatusEnums;
import com.email.support.mapper.MailSendAMapper;
import com.email.support.mapper.MailSendBMapper;
import com.email.support.service.MailSendService;
import com.email.support.service.RedisService;
import com.email.support.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author suiguozhen
 * @date 19/07/13
 */
@Service
public class MailSendServiceImpl implements MailSendService {

    private Integer countMapper = 2;

    @Autowired
    private RedisService redisService;
    @Autowired
    private MailSendAMapper mailSendAMapper;
    @Autowired
    private MailSendBMapper mailSendBMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert(MailSendEntity mailSendEntity) {
        //添加uuid
        mailSendEntity.setSendId(UUIDUtils.generate());
        //修改状态
        mailSendEntity.setSendStatus(UserStatusEnums.QUEUE.getOrdinal());
        int hashCode = mailSendEntity.hashCode();
        int modulo = hashCode % countMapper;
        //把数据扔到redis中  警惕这样是否会有重复消费问题
        redisService.addList(mailSendEntity);
        //取模运算 决定插入哪个数据库
        if (modulo > 0) {
            mailSendAMapper.insert(mailSendEntity);
        }else{
            mailSendBMapper.insert(mailSendEntity);
        }
    }


}
