/**
 * @author G-bug 2019/12/2
 */
package com.learn.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.factory.RequestRateLimiterGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.RetryGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class GatewayConsulApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayConsulApplication.class, args);
    }

    /*
    Java方式实现路由定位
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes().route("path_route", r -> r.path("/search").uri("http://www.baidu.com/")).build();
    }
    */
}
