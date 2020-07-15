package com.learn.jasper.controller;

import com.learn.jasper.entity.User;
import com.learn.jasper.service.UserService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsExporterConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsXlsView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author g-bug
 * @date 2020/7/13 下午5:35
 */
@RestController("/")
public class UserController {

    @Resource
    private ApplicationContext applicationContext;

    @Autowired
    private UserService userService;

    @GetMapping("report")
    public ModelAndView report() {

        JasperReportsPdfView view = new JasperReportsPdfView();
        view.setUrl("classpath:report.jrxml");
        view.setApplicationContext(applicationContext);

        Map<String, Object> param = new HashMap<>();
        param.put("datasource", userService.getAllUsers());
        return new ModelAndView(view, param);
    }


    @GetMapping("report1")
    public void report1() {
        String sourceFileName = "/home/g-bug/workspace/git/learning/spring/spring-boot/boot-jasper/" +
                "src/main/resources/report.jasper";
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(userService.getAllUsers());
        Map<String, Object> param = new HashMap<>();

        String printFileName = null;

        try {
            printFileName = JasperFillManager.fillReportToFile(
                    sourceFileName,
                    param,
                    beanCollectionDataSource);
            if (printFileName != null) {
                // gaf
                JasperPrintManager.printReport(printFileName, true);
            }
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String xmlFileName = "/home/g-bug/workspace/git/learning/spring/spring-boot/boot-jasper/" +
                "src/main/resources/report.jrxml";

        // 编译为 jrprint
        try {
            JasperCompileManager.compileReportToFile(xmlFileName);
        } catch (JRException e) {
            e.printStackTrace();
        }

        // 填充为 对应文件
        String jasperFileName = "/home/g-bug/workspace/git/learning/spring/spring-boot/boot-jasper/" +
                "src/main/resources/report.jasper";

        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(new ArrayList<User>(){{
            add(new User(){{
                setId(14);
                setName("dddddddd");
            }});
        }});

        String printFileName = null;

        try {
            printFileName = JasperFillManager.fillReportToFile(
                    jasperFileName,
                    new HashMap<String, Object>() {{
                        put("ReportTitle", "aaaa");
                        put("Author", "bbbb");
                    }},
                    jrBeanCollectionDataSource);
            if (printFileName != null) {

                // 导出表格 中文没问题
                JRXlsExporter exporter = new JRXlsExporter();
                exporter.setExporterInput(new SimpleExporterInput(printFileName));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("/home/g-bug/xxx.xls"));
                exporter.exportReport();

                // 导出pdf
                JasperExportManager.exportReportToPdfFile(printFileName,
                        "/home/g-bug/xx.pdf");
            }
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
