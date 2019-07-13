package com.email.support.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 配置数据源容器对象
 * @author suiguozhen
 * @date 19/07/06
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getContextHolder();
    }

}
