package com.email.support.service;

import com.email.support.entity.MailSendEntity;

/**
 * redis服务类
 * @author suiguozhen
 * @date 19-7-14 下午2:55
 */
public interface RedisService {
    /**
     * 添加队列
     * @param mailSendEntity mailSendEntity
     */
    void addList(MailSendEntity mailSendEntity);
}
