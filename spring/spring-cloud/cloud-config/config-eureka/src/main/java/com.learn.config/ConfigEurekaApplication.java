/**
 * @author G-bug 2019/11/12
 */
package com.learn.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ConfigEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigEurekaApplication.class, args);
    }

}
