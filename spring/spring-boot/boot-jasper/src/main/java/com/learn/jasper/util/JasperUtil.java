package com.learn.jasper.util;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

/**
 * jasper导出工具类
 *
 * @author g-bug
 * @date 2020/7/15 下午5:26
 */
public class JasperUtil {

    private static final String XML = ".jrxml";
    private static final String PRINT = ".jrprint";
    private static final String JASPER = ".jasper";
    private static final String XLS = ".xls";
    private static final String PDF = ".pdf";

    private static final String JASPER_PREFIX = "/home/g-bug/workspace/git/learning/spring/spring-boot/boot-jasper/src/main/resources/";
    private static final String EXPORT_PREFIX = "/home/g-bug/";

    /**
     * 导出excel表格
     *
     * @param dataSource 数据源
     * @param fileName 文件名
     * @param <T>      数据源
     * @return 文件地址
     * @throws JRException jasper异常
     */
    public static <T> String exportXls(List<T> dataSource, String fileName) throws JRException {
        String exportFilePath = EXPORT_PREFIX + fileName + XLS;
        String jasperFilePath = getJasperPath(fileName);

        String printFilePath = getPrintPath(dataSource, jasperFilePath);
        if (printFilePath != null) {
            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setExporterInput(new SimpleExporterInput(printFilePath));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportFilePath));
            exporter.exportReport();
        }
        return fileName + XLS;
    }

    /**
     * 导出pdf
     *
     * @param dataSource 数据源
     * @param fileName 文件名
     * @param <T>      数据源
     * @return 文件地址
     * @throws JRException jasper异常
     */
    public static <T> String exportPdf(List<T> dataSource, String fileName) throws JRException {
        String exportFilePath = EXPORT_PREFIX + fileName + PDF;
        String jasperFilePath = getJasperPath(fileName);

        String printFilePath = getPrintPath(dataSource, jasperFilePath);
        if (printFilePath != null) {
            JasperExportManager.exportReportToPdfFile(printFilePath, exportFilePath);
        }
        return fileName + PDF;
    }

    /**
     * 获取 .jasper 文件 = 编译
     *
     * @param fileName 地址
     * @return 文件地址
     * @throws JRException jasper异常
     */
    private static String getJasperPath(String fileName) throws JRException {
        String xmlFilePath = JASPER_PREFIX + fileName + XML;
        String jasperFilePath = JASPER_PREFIX + fileName + JASPER;

        boolean jasperIsExists = Files.exists(Paths.get(jasperFilePath));
        if (!jasperIsExists) {
            return JasperCompileManager.compileReportToFile(xmlFilePath);
        }

        return jasperFilePath;
    }

    /**
     * 获取 .jrprint文件 = 填充
     *
     * @param dataSource       数据源
     * @param jasperFilePath .jasper
     * @param <T>            数据源
     * @return 文件地址
     * @throws JRException jasper异常
     */
    private static <T> String getPrintPath(List<T> dataSource, String jasperFilePath) throws JRException {
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(dataSource);
        return JasperFillManager.fillReportToFile(
                jasperFilePath,
                new HashMap<String, Object>() {{
                    /*
                    put("ReportTitle", "aaaa");
                    put("Author", "bbbb");
                    */
                }},
                jrBeanCollectionDataSource);
    }
}
