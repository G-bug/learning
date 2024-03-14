/**
 * @author G-bug 2019/9/24
 */
package com.learn.aop.controller;

import com.learn.aop.aspect.UserAccess;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auth Administrator
 */
@RestController
public class UserController {

    @RequestMapping("/first")
    public Object first() {
        return "first ";
    }

    @RequestMapping("/doError")
    public Object error() {
        return "error";
    }

    @RequestMapping("/second")
    @UserAccess(desc = "second")
    public Object second() {
        Integer a = null;
        a.toString();
        return "second";
    }

}
