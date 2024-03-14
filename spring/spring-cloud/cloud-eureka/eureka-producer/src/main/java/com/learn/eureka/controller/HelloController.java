/**
 * @author G-bug 2019/11/6
 */
package com.learn.eureka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    @Autowired
    HttpServletRequest request;

    @RequestMapping("/hello")
    public String index(@RequestParam("name") String name) {
        return "hello +" + name + ", " + request.getLocalPort();
    }

}
