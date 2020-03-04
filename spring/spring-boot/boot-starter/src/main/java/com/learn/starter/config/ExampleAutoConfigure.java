package com.learn.starter.config;

import com.learn.starter.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* starter配置原理
*  1. spring boot 启动时扫描项目所依赖的jar包, 寻找包含 spring.factories 文件的jar包
*  2. 根据 spring.factories 配置加载AutoConfigure类
*  3. 根据@Conditional注解的条件, 进行自动配置将Bean 注入 spring context
* */
@Configuration
@ConditionalOnClass(ExampleService.class) // 当classpath下发现该类的情况下 进行自动配置
@EnableConfigurationProperties(ExampleServiceProperties.class)
public class ExampleAutoConfigure {

    private final ExampleServiceProperties properties;

    @Autowired
    public ExampleAutoConfigure(ExampleServiceProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean // 当spring context 中不存在该Bean时
    // 当配置文件中 example.service  = enabled 为true时
    @ConditionalOnProperty(prefix = "example.service", value = "enabled", havingValue = "true")
    ExampleService exampleService() {
        return new ExampleService(properties.getPrefix(), properties.getSuffix());
    }

}
