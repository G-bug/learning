package com.learn.jasper.util;

import com.learn.jasper.entity.JasperModel;
import com.learn.jasper.entity.Point;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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

    private static final String JASPER_PREFIX = "";
    // jar包运行则 jar包所在路径, idea运行则为当前根路径
    private static final String EXPORT_PREFIX = "/app/rep/";

    /**
     * 统一处理加工表内数据
     *
     * @param dataSource 源
     * @return model对象
     */
    private static List<JasperModel> createDataSource(List<?> dataSource) {
        return new ArrayList<JasperModel>() {{
            add(new JasperModel() {{
                setDate("");
                setTable(new JRBeanCollectionDataSource(dataSource));
            }});
        }};
    }

    /**
     * 导出excel表格
     *
     * @param dataSource 数据源
     * @param fileName   文件名
     * @return 文件地址
     * @throws JRException jasper异常
     */
    public static String exportXls(List<?> dataSource, String fileName) throws JRException {
        String exportFilePath = EXPORT_PREFIX + fileName + XLS;

        String printFilePath = getPrintPath(createDataSource(dataSource), fileName);
        if (printFilePath != null) {
            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setExporterInput(new SimpleExporterInput(printFilePath));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportFilePath));
            exporter.setConfiguration(new SimpleXlsxReportConfiguration() {{
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
     * @return 路径
     * @throws JRException jasper异常
     */
    public static String exportHtml(List<?> dataSource, String fileName) throws JRException {
        String exportFilePath = EXPORT_PREFIX + fileName + HTML;

        String printFilePath = getPrintPath(createDataSource(dataSource), fileName);
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
     * @throws JRException jasper异常
     */
    public static void exportPdfStream(List<?> dataSource, String fileName, OutputStream outputStream) throws JRException {
        JasperExportManager.exportReportToPdfStream(getPrint(createDataSource(dataSource), fileName), outputStream);
    }

    /**
     * 输出xls流
     *
     * @param dataSource   数据源
     * @param fileName     文件路径
     * @param outputStream 目标流
     * @throws JRException jasper异常
     */
    public static void exportXlsStream(List<?> dataSource, String fileName, OutputStream outputStream) throws JRException {
        String printFilePath = getPrintPath(createDataSource(dataSource), fileName);
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
     * @return 文件地址
     * @throws JRException jasper异常
     */
    public static String exportPdf(List<?> dataSource, String fileName) throws JRException {
        String exportFilePath = EXPORT_PREFIX + fileName + PDF;

        String printFilePath = getPrintPath(createDataSource(dataSource), fileName);
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
     * @return 文件地址
     * @throws JRException jasper异常
     */
    private static String getPrintPath(List<?> dataSource, String fileName) throws JRException {

        String jasperFilePath = getJasperPath(fileName);

        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(dataSource);
        // 全量填充
        return JasperFillManager.fillReportToFile(
                jasperFilePath,
                new HashMap<String, Object>() {{
                    // 供jrxml取出
                    put("format", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                }},
                jrBeanCollectionDataSource);
    }

    /**
     * 获取 print对象
     *
     * @param dataSource 数据源
     * @param fileName 文件名
     * @return 文件地址
     * @throws JRException jasper异常
     */
    private static JasperPrint getPrint(List<?> dataSource, String fileName) throws JRException {

        String jasperFilePath = getJasperPath(fileName);

        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(dataSource);

        // 全量填充
        return JasperFillManager.fillReport(
                jasperFilePath,
                new HashMap<String, Object>() {{
                    /* 传入jrxml param
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

    /**
     * 动态输出
     *
     * @param dataSource 源
     * @param fileName 文件名
     * @return 地址
     * @throws JRException 异常
     */
    public static String reportDesign(List<Point> dataSource, String fileName) throws JRException {

        dataSource.forEach(o -> {
            if ("4".equals(o.getType())) {
                compiler(dataSource, o);
            }
        });

        List<Point> list = toDesigns(dataSource);

        JasperReport report = JasperDesignDemo.getJasperReport(
                JASPER_PREFIX + fileName + XML, list);

        // 一定要传  new JREmptyDataSource() 指定空数据源
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, new HashMap<>(), new JREmptyDataSource());

        JasperExportManager.exportReportToHtmlFile(jasperPrint, EXPORT_PREFIX + fileName + HTML);

        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(EXPORT_PREFIX + fileName + XLS));
        exporter.setConfiguration(new SimpleXlsxReportConfiguration() {{
        }});

        exporter.exportReport();

        return "report1" + HTML;
    }

    /**
     * 公式处理
     *
     * @param points 实体列表
     * @param point 对象
     * @return 实体
     */
    public static Point compiler(List<Point> points, Point point) {
        String[] childPointCode = point.getCont().split("\\+");
        Integer[] sum = new Integer[childPointCode.length];
        int i = 0;

        for (String chile : childPointCode) {
            Point dependPoint = points.stream().filter(o -> chile.equals(o.getCellCode())).findAny().orElse(null);
            if ("4".equals(point.getType())) {
                sum[i] = Integer.valueOf(compiler(points, dependPoint).getCont());
                dependPoint.setType("1");
                dependPoint.setCont(sum[i].toString());
                i++;
            } else {
                sum[i++] = Integer.valueOf(point.getCont());
            }
        }

        point.setCont((Arrays.stream(sum).mapToInt(o -> o).sum()) + "");
        point.setType("5");
        return point;
    }

    /**
     * 转换为动态输出所需对象结构
     *
     * @param source
     * @return
     */
    public static List<Point> toDesigns(List<Point> source) {
        Map<Integer, List<Point>> rowMap = source.stream().collect(
                Collectors.groupingBy(Point::getX, Collectors.toList())
        );

        List<Point> list = new ArrayList<>();
        for (Map.Entry<Integer, List<Point>> entry : rowMap.entrySet()) {
            list.add(new Point() {{
                setX(entry.getKey());
                setRow(entry.getValue());
            }});
        }
        return list;
    }

    private static InputStream getJrxml(String fileName) throws IOException {
        ClassPathResource cpr = new ClassPathResource("static/report1.jrxml");
        InputStream in = cpr.getInputStream();
        return in;
    }

    /**
     * poi动态导出
     *
     * @param source   源
     * @param fileName 文件
     * @return 路径
     * @throws JRException 异常
     */
    public static String reportPoiDesign(List<Point> source, String fileName) throws JRException, IOException {
        JasperReport report = JasperDesignDemoPoi.getJasperReport(getJrxml(fileName), poiCompiler(source));

        // 一定要传  new JREmptyDataSource() 指定空数据源
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, new HashMap<>(), new JREmptyDataSource());

        JasperExportManager.exportReportToHtmlFile(jasperPrint, EXPORT_PREFIX + fileName + HTML);

        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(EXPORT_PREFIX + fileName + XLS));
        exporter.setConfiguration(new SimpleXlsxReportConfiguration() {{
            // 设置sheet页
            // setSheetNames();
        }});

        exporter.exportReport();
        return "report1" + HTML;
    }

    /**
     * 转换poi 以完成公式计算
     *
     * @param source 源
     * @return
     */
    public static Sheet poiCompiler(List<Point> source) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("xx");

        List<Point> rowPoint;
        Point cellPoint;
        for (int i = 0; i < source.size(); i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < (rowPoint = source.get(i).getRow()).size(); j++) {
                cellPoint = rowPoint.get(j);
                Cell cell = row.createCell(j);
                if (Objects.equals("4", cellPoint.getType())) {
                    cell.setCellType(Cell.CELL_TYPE_FORMULA);
                    cell.setCellFormula(cellPoint.getCont());
                } else {
                    if (Objects.equals("1", cellPoint.getType())) {
                        cell.setCellValue(Double.parseDouble(cellPoint.getCont()));
                    } else {
                        cell.setCellValue(cellPoint.getCont());
                    }
                }
            }
        }

        workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();
        return sheet;
    }
}
