CREATE TABLE edm_record_a
(
    send_id varchar(40) PRIMARY KEY NOT NULL COMMENT '主键',
    send_user_id varchar(40) NOT NULL COMMENT '投递人的id',
    send_to varchar(40) NOT NULL COMMENT '投递邮箱',
    send_content varchar(400) NOT NULL COMMENT '投递内容',
    send_priority decimal(10) NOT NULL COMMENT '投递优先级 修改密码优先级高',
    send_count decimal(10) NOT NULL default 0 COMMENT '投递次数',
    send_status varchar(10) NOT NULL default 0 COMMENT '投递状态',
    remark varchar(200) COMMENT '备注',
    version decimal(10) NOT NULL default 0 COMMENT '版本号',
    update_by varchar(40) NOT NULL COMMENT '更新人',
    update_time timestamp DEFAULT current_timestamp() NOT NULL COMMENT '更新时间'
);

CREATE UNIQUE INDEX edm_record_a_send_id_uindex ON edm_record_a (send_id);

