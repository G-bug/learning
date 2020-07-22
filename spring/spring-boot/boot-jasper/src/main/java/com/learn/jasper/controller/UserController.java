package com.learn.jasper.controller;

import com.learn.jasper.service.UserService;
import com.learn.jasper.util.JasperUtil;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

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

    @GetMapping("report_html")
    public String report4() {
        try {
            return JasperUtil.exportHtml(userService.getAllUsers(), "report");
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

    @GetMapping("report_pdf_stream")
    public String report2(HttpServletResponse response) {
        try (OutputStream outputStream = response.getOutputStream()) {
            JasperUtil.exportPdfStream(userService.getAllUsers(), "report", outputStream);
            outputStream.flush();
        } catch (JRException | IOException ignore) {
            return "fail";
        }
        return "succ";
    }

    @GetMapping("report_xls_stream")
    public String report3(HttpServletResponse response) {
        try (OutputStream outputStream = response.getOutputStream()) {
            JasperUtil.exportXlsStream(userService.getAllUsers(), "report", outputStream);
            outputStream.flush();
        } catch (JRException | IOException e) {
            e.printStackTrace();
            return "fail";
        }
        return "succ";
    }
}
