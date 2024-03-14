/**
 * @author G-bug 2019/11/6
 */
package com.learn.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @auth Administrator
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args){
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
