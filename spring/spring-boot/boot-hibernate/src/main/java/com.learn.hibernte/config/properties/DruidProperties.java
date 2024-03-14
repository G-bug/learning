/**
 * @author G-bug 2019/10/15
 */
package com.learn.hibernte.config.properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.JdbcConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * @auth Administrator
 */

@Component
@ConfigurationProperties("spring.datasource")
public class DruidProperties {
    private String url;

    private String username;

    private String password;

    /* 默认配置*/

    private String driverClassName = "com.mysql.cj.jdbc.Driver";

    private Integer initialSize = 10;

    private Integer minIdle = 3;

    private Integer maxActive = 60;

    private Integer maxWait = 60000;

    private Boolean removeAbandoned = true;

    private Integer removeAbandonedTimeout = 180;

    private Integer timeBetweenEvictionRunsMillis = 60000;

    private Integer minEvictableIdleTimeMillis = 300000;

    private String validationQuery = "SELECT 'x'";

    private Boolean testWhileIdle = true;

    private Boolean testOnBorrow = false;

    private Boolean testOnReturn = false;

    private Boolean poolPreparedStatements = true;

    private Integer maxPoolPreparedStatementPerConnectionSize = 50;

    private String filters = "stat";

    public void config(DruidDataSource dataSource) {
        // https://github.com/alibaba/druid/wiki/DruidDataSource配置属性列表
        dataSource.setDbType(JdbcConstants.MYSQL);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        // 定义初始连接数
        dataSource.setInitialSize(initialSize);
        // 最小空闲
        dataSource.setMinIdle(minIdle);
        // 最大连接数
        dataSource.setMaxActive(maxActive);
        // 获取连接 等待超时的时间
        dataSource.setMaxWait(maxWait);
        // 超过时间限制是否回收
        dataSource.setRemoveAbandoned(removeAbandoned);
        // 超过时间限制多长
        dataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);

        // 配置间隔多久进行一次检测, 检测需要关闭的空闲连接, 单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        // 配置一个连接在池中最少生存的时间, 单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        // 检测连接是否是有效的sql, 要求是一个查询语句
        dataSource.setValidationQuery(validationQuery);
        // 申请连接时的检测
        dataSource.setTestWhileIdle(testWhileIdle);
        // 测试 申请连接时执行的validationQuery检测是否有效, 配置为true会降低性能
        dataSource.setTestOnBorrow(testOnBorrow);
        // 归还连接时执行validationQuery检测, 配置为true会降低性能
        dataSource.setTestOnReturn(testOnReturn);
        // 开启PSCache, 并指定每个连接上PSCache的大小
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);

        // 监控统计用 filter:stat
        // 日志用 filter:log4j
        // 防御sql注入 filter:wall
        try {
            dataSource.setFilters(filters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public Integer getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(Integer initialSize) {
        this.initialSize = initialSize;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Integer getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public Integer getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }

    public Boolean getRemoveAbandoned() {
        return removeAbandoned;
    }

    public void setRemoveAbandoned(Boolean removeAbandoned) {
        this.removeAbandoned = removeAbandoned;
    }

    public Integer getRemoveAbandonedTimeout() {
        return removeAbandonedTimeout;
    }

    public void setRemoveAbandonedTimeout(Integer removeAbandonedTimeout) {
        this.removeAbandonedTimeout = removeAbandonedTimeout;
    }

    public Integer getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(Integer timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public Integer getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(Integer minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public Boolean getTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(Boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public Boolean getTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(Boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public Boolean getPoolPreparedStatements() {
        return poolPreparedStatements;
    }

    public void setPoolPreparedStatements(Boolean poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
    }

    public Integer getMaxPoolPreparedStatementPerConnectionSize() {
        return maxPoolPreparedStatementPerConnectionSize;
    }

    public void setMaxPoolPreparedStatementPerConnectionSize(Integer maxPoolPreparedStatementPerConnectionSize) {
        this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }
}
