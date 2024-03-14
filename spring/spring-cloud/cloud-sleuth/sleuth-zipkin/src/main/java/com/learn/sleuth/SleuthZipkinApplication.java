/**
 * @author G-bug 2019/11/29
 */
package com.learn.sleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * @auth Administrator
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer
public class SleuthZipkinApplication {

    public static void main(String[] args) {
        SpringApplication.run(SleuthZipkinApplication.class, args);
    }

}
