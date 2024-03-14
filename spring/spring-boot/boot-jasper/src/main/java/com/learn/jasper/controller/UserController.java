package com.learn.jasper.controller;

import com.learn.jasper.service.UserService;
import com.learn.jasper.util.JasperUtil;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;

/**
 * @author g-bug
 * @date 2020/7/13 下午5:35
 */
@RestController("/")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * poi 加入计算
     * @param request
     * @param response
     * @return
     */
    @GetMapping("report_design_v1")
    public String report6(HttpServletRequest request, HttpServletResponse response) {
        try {
            JasperUtil.reportPoiDesign(userService.getMapCells(), "report1");
            return "success";
        } catch (JRException | IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @GetMapping("report_design")
    public String report5() {
        try {
            return JasperUtil.reportDesign(userService.getCells(), "report1");
        } catch (JRException e) {
            e.printStackTrace();
        }
        return "error";
    }

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
    public String report4(HttpServletResponse response, HttpServletRequest request) {
        try {
            return JasperUtil.exportHtml(userService.getAllUsers(), request.getContextPath() + "");
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
