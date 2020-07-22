package com.learn.jasper.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.Date;
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
    private static final String HTML = ".html";
    private static final String PDF = ".pdf";

    private static final String JASPER_PREFIX = "/home/g-bug/workspace/git/learning/spring/spring-boot/boot-jasper/src/main/resources/";
    private static final String EXPORT_PREFIX = "/home/g-bug/";

    /**
     * 导出excel表格
     *
     * @param dataSource 数据源
     * @param fileName   文件名
     * @param <T>        数据源
     * @return 文件地址
     * @throws JRException jasper异常
     */
    public static <T> String exportXls(List<T> dataSource, String fileName) throws JRException {
        String exportFilePath = EXPORT_PREFIX + fileName + XLS;

        String printFilePath = getPrintPath(dataSource, fileName);
        if (printFilePath != null) {
            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setExporterInput(new SimpleExporterInput(printFilePath));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportFilePath));
            exporter.setConfiguration(new SimpleXlsxReportConfiguration(){{
                setWhitePageBackground(false);
                setDetectCellType(true);
            }});
            exporter.exportReport();
        }
        return fileName + XLS;
    }

    /**
     * 导出html
     *
     * @param dataSource 数据源
     * @param fileName   文件路径
     * @param <T>        源
     * @return 路径
     * @throws JRException jasper异常
     */
    public static <T> String exportHtml(List<T> dataSource, String fileName) throws JRException {
        String exportFilePath = EXPORT_PREFIX + fileName + HTML;

        String printFilePath = getPrintPath(dataSource, fileName);
        if (printFilePath != null) {
            JasperExportManager.exportReportToHtmlFile(printFilePath, exportFilePath);
        }
        return fileName + HTML;
    }

    /**
     * 输出pdf流
     *
     * @param dataSource   数据源
     * @param fileName     文件路径
     * @param outputStream 目标流
     * @param <T>          源
     * @throws JRException jasper异常
     */
    public static <T> void exportPdfStream(List<T> dataSource, String fileName, OutputStream outputStream) throws JRException {
        JasperExportManager.exportReportToPdfStream(getPrint(dataSource, fileName), outputStream);
    }

    /**
     * 输出xls流
     *
     * @param dataSource   数据源
     * @param fileName     文件路径
     * @param outputStream 目标流
     * @param <T>          源
     * @throws JRException jasper异常
     */
    public static <T> void exportXlsStream(List<T> dataSource, String fileName, OutputStream outputStream) throws JRException {
        String printFilePath = getPrintPath(dataSource, fileName);
        if (printFilePath != null) {
            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setExporterInput(new SimpleExporterInput(printFilePath));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            exporter.exportReport();
        }
    }

    /**
     * 导出pdf
     *
     * @param dataSource 数据源
     * @param fileName   文件名
     * @param <T>        数据源
     * @return 文件地址
     * @throws JRException jasper异常
     */
    public static <T> String exportPdf(List<T> dataSource, String fileName) throws JRException {
        String exportFilePath = EXPORT_PREFIX + fileName + PDF;

        String printFilePath = getPrintPath(dataSource, fileName);
        if (printFilePath != null) {
            JasperExportManager.exportReportToPdfFile(printFilePath, exportFilePath);
        }
        return fileName + PDF;
    }

    /**
     * 获取 .jrprint文件 = 填充
     *
     * @param dataSource 数据源
     * @param fileName   文件名
     * @param <T>        数据源
     * @return 文件地址
     * @throws JRException jasper异常
     */
    private static <T> String getPrintPath(List<T> dataSource, String fileName) throws JRException {

        String jasperFilePath = getJasperPath(fileName);

        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(dataSource);
        // 全量填充
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

    private static <T> JasperPrint getPrint(List<T> dataSource, String fileName) throws JRException {

        String jasperFilePath = getJasperPath(fileName);

        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(dataSource);

        // 全量填充
        return JasperFillManager.fillReport(
                jasperFilePath,
                new HashMap<String, Object>() {{
                    /*
                    put("ReportTitle", "aaaa");
                    put("Author", "bbbb");
                    */
                }},
                jrBeanCollectionDataSource);
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

    public static void main(String[] args) throws JRException {
        //getJasperPath("report");
        System.out.println(DateFormat.getDateInstance().format(new Date().getTime()));
    }
}
