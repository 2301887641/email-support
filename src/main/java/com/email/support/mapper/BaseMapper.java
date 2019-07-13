package com.email.support.mapper;

import com.email.support.entity.MailSendEntity;

/**
 * @author suiguozhen
 * @date 19/07/13
 */
public interface BaseMapper {
    /**
     * 插入操作
     */
    void insert(MailSendEntity mailSendEntity);

    /**
     * 修改操作
     */
    void updateSelective(MailSendEntity mailSendEntity);
}
