package com.email.support.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.apache.bcel.util.ClassLoaderRepository;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * mybatis配置
 * @author suiguozhen
 * @date 19/07/06
 */
@Configuration
public class MybatisConfig extends MybatisAutoConfiguration {
    /**
     * 主数据源 根据名称注入
     */
    @Resource(name = "slaveDatasource")
    private DataSource slaveDatasource;

    @Resource(name = "masterDatasource")
    private DataSource masterDatasource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        return super.sqlSessionFactory(datasourceStrategy());
    }

    /**
     * 数据源策略
     * @return 数据源
     */
    @SuppressWarnings("unchecked")
    private AbstractRoutingDataSource datasourceStrategy(){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        //软引用map
        ClassLoaderRepository.SoftHashMap softHashMap = new ClassLoaderRepository.SoftHashMap();
        //设置主从
        softHashMap.put(DynamicDataSourceContextHolder.DatasourseType.MASTER, masterDatasource);
        softHashMap.put(DynamicDataSourceContextHolder.DatasourseType.SLAVE, slaveDatasource);
        //默认使用主数据源
        dynamicDataSource.setTargetDataSources(softHashMap);
        //将两个数据源放入map
        dynamicDataSource.setDefaultTargetDataSource(masterDatasource);
        return dynamicDataSource;
    }
}
