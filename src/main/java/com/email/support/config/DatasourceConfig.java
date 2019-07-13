package com.email.support.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
    private static Logger LOG = LoggerFactory.getLogger(DatasourceConfig.class);

    /**
     * 引入druid数据库连接池类型
     */
    @Value("${druid.datasource.type}")
    private Class<? extends DataSource> datasourceType;

    /**
     * 主数据源
     * @return DataSource
     */
    @Primary
    @Bean("masterDatasource")
    @ConfigurationProperties("druid.datasource.master")
    public DataSource masterDatasource() {
        LOG.info("=========== master run =========");
        return DataSourceBuilder.create().type(datasourceType).build();
    }

    /**
     * 从数据源
     * @return DataSource
     */
    @Bean("slaveDatasource")
    @ConfigurationProperties("druid.datasource.slave")
    public DataSource slaveDatasource() {
        LOG.info("============ slave run ==========");
        return DataSourceBuilder.create().type(datasourceType).build();
    }

    /**
     * druid的servlet
     * @return ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        servletRegistrationBean.addInitParameter("deny", "/deny");
        return servletRegistrationBean;
    }

    /**
     * druid的filter
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean druidFilter() {
        FilterRegistrationBean<WebStatFilter> webStatFilterRegistrationBean = new FilterRegistrationBean<>();
        webStatFilterRegistrationBean.setFilter(new WebStatFilter());
        webStatFilterRegistrationBean.addUrlPatterns("/*");
        webStatFilterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return webStatFilterRegistrationBean;
    }
}
