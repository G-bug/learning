package com.learn.jasper.controller;

import com.learn.jasper.service.UserService;
import com.learn.jasper.util.JasperUtil;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author g-bug
 * @date 2020/7/13 下午5:35
 */
@RestController("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("report_xls")
    public String report() {
        try {
            return JasperUtil.exportXls(userService.getAllUsers(), "report");
        } catch (JRException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @GetMapping("report_pdf")
    public String report1() {
        try {
            return JasperUtil.exportPdf(userService.getAllUsers(), "report");
        } catch (JRException e) {
            e.printStackTrace();
        }
        return "error";
    }
}
