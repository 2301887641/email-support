package com.email.support.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.Servlet;
import javax.servlet.ServletRegistration;
import javax.sql.DataSource;

/**
 * 数据库连接池配置
 *
 * @author suiguozhen
 * @date 19/07/05
 */
@Configuration
@EnableTransactionManagement
public class DatasourceConfig {
    /**
     * 引入druid数据库连接池类型
     */
    @Value("${druid.type}")
    private Class<? extends DataSource> datasourceType;

    @Primary
    @Bean("masterDatasource")
    @ConfigurationProperties("druid.master")
    public DataSource masterDatasource() {
        return DataSourceBuilder.create().type(datasourceType).build();
    }

    @Bean("slaveDatasource")
    @ConfigurationProperties("druid.slave")
    public DataSource slaveDatasource(){
        return DataSourceBuilder.create().type(datasourceType).build();
    }

    @Bean
    public ServletRegistration druidServlet(){
        ServletRegistrationBean<Servlet> servletServletRegistrationBean = new ServletRegistrationBean<>();
    }
}
