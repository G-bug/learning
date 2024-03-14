/**
 * @author G-bug 2019/11/8
 */
package com.learn.hystrix.controller;

import com.learn.hystrix.remote.HelloRemote;
import com.learn.hystrix.remote.HelloTwoRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.Remote;

/**
 * @auth Administrator
 */
@RestController
public class ConsumerController {

    @Autowired
    HelloRemote helloRemote;

    @Autowired
    HelloTwoRemote helloTwoRemote;


    @GetMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
        return helloRemote.index(name);
    }


    @GetMapping("/hello2/{name}")
    public String index2(@PathVariable("name") String name) {
        return helloTwoRemote.index(name);
    }
}
