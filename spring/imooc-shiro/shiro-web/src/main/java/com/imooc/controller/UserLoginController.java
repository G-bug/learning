package com.imooc.controller;

import com.imooc.vo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;


@Controller
public class UserLoginController {


    @RequestMapping(value = "subLogin", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String userLogin(User user) {

        ArrayList a = new ArrayList();

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());

        try {

            token.setRememberMe(user.isRememberMe());

            subject.login(token);

        } catch (AuthenticationException e) {
            return e.getMessage();
        }

        if (subject.hasRole("admin")) {
            return "有admin角色";
        }

        return "登录成功";
    }

    // @RequiresRoles("admin")
    @RequestMapping(value = "/testrole", method = RequestMethod.GET)
    @ResponseBody
    public String testRole() {
        return "testRole success";
    }

    // 权限拦截 @RequiresPermissions("")
    // @RequiresRoles("admin1")
    @RequestMapping(value = "/testrole1", method = RequestMethod.GET)
    @ResponseBody
    public String testRole1() {
        return "testRole1 success";
    }

    @RequestMapping("logout")
    public void logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
