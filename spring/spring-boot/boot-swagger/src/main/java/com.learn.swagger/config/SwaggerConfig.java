/**
 * @author G-bug 2019/10/24
 */
package com.learn.swagger.config;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @auth Administrator
 */
@Configuration
@EnableSwagger2 // 开启swagger2
public class SwaggerConfig {

    @Bean
    // 扫描包生成文档, 默认显示所有接口
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(Sets.newHashSet("application/json"))
                .consumes(Sets.newHashSet("application/json"))
                .protocols(Sets.newHashSet("http", "https"))
                .apiInfo(apiInfo())
                .forCodeGeneration(true)
                .select()
                // 设置controller包
                .apis(RequestHandlerSelectors.basePackage("com.learn.swagger.api.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    // 基本信息设置
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 文档标题
                .title("系统API服务")
                .description("系统API接口文档")
                // .termsOfServiceUrl("https://github.com/")
                .version("v1")
                // .license("MIT 协议")
                // .license("http://www.....")
                // .contact(new Contact("xx", "xxxx"))
                .build();
    }

}
