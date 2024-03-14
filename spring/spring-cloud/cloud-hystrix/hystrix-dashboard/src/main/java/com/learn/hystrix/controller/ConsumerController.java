/**
 * @author G-bug 2019/11/8
 */
package com.learn.hystrix.controller;

import com.learn.hystrix.remote.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    HelloRemote helloRemote;

    @GetMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
        return helloRemote.index(name);
    }

}
