/**
 * @author G-bug 2019/9/30
 */
package com.learn.cache.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;

@Configuration
@EnableTransactionManagement(order = 2)
@MapperScan(basePackages = {"com.learn.cache.dao.repository"})
public class MybatisPlusConfig {

    @Resource
    private DruidProperties druidProperties;

    /**
    * 单数据源 连接池 配置
    */
    @Bean
    public DruidDataSource singleDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }

    /**
    * mybatis-plus 分页插件 PaginationInterceptor
    */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
