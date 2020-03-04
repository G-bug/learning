/**
 * @author G-bug 2019/10/15
 */
package com.learn.hibernte.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.learn.hibernte.config.properties.DruidProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @auth Administrator
 */
@Configuration
@EnableTransactionManagement(order = 2)
public class DataSourceConfig {

    @Resource
    private DruidProperties properties;

    @Bean
    public DruidDataSource singleDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        properties.config(dataSource);
        return dataSource;
    }

}
