package com.learn.jasper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author g-bug
 * @date 2020/7/13 下午5:29
 */
@SpringBootApplication
@MapperScan("com.learn.jasper.mapper")
public class JasperApplication {

    public static void main(String[] args) {
        SpringApplication.run(JasperApplication.class, args);
    }
}
