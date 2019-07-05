package com.email.support;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 主配置文件
 * @author suiguozhen
 * @date 19-7-4 下午9:21
 */
@Configuration
@MapperScan(basePackages = {"com.email.support.mapper"})
public class ApplicationConfig {

}
