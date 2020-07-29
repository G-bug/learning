package com.learn.jasper.util;

import net.sf.jasperreports.engine.JRChild;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

/**
 * @author g-bug
 * @date 2020/7/24 上午7:08
 */
public class JasperDesignDemoPoi {

    public static JasperReport getJasperReport(String xmlFilePath,
                                               HSSFSheet sheet) throws JRException {
        JasperDesign design = getJasperDesign(xmlFilePath);

        JRDesignBand columnHeader = (JRDesignBand) design.getColumnHeader();
        reSetColumnHeaderHeight(columnHeader, sheet);
        reSetshapeAndPosition(columnHeader, sheet);
        addElementToColumnHeader(columnHeader, sheet);
        return JasperCompileManager.compileReport(design);
    }

    private static JasperDesign getJasperDesign(String filePath)
            throws JRException {
        return JRXmlLoader.load(filePath);
    }

    private static void reSetColumnHeaderHeight(JRDesignBand columnHeader,
                                                HSSFSheet sheet) {
        columnHeader.setHeight(columnHeader.getHeight() * sheet.getLastRowNum() + 20);
    }

    private static JRDesignStaticText getFlagTextInDesign(
            JRDesignBand columnHeader) {
        return (JRDesignStaticText) columnHeader.getElementByKey("flag");
    }

    /**
     * 处理 columnHeader中的内容
     */
    private static void reSetshapeAndPosition(JRDesignBand columnHeader,
                                              HSSFSheet sheet) {
        // 获得 模板文本框
        JRDesignStaticText flagText = getFlagTextInDesign(columnHeader);

        // columnHeader 子类
        Iterator<JRChild> children = columnHeader.getChildren().iterator();

        JRDesignStaticText element;
        while (children.hasNext()) {
            // columnHeader 下的每个文本框
            element = (JRDesignStaticText) children.next();
        }
    }

    private static void addElementToColumnHeader(JRDesignBand columnHeader,
                                                 HSSFSheet sheet) {
        // 删除 模板 框
        JRDesignStaticText flagText = getFlagTextInDesign(columnHeader);
        columnHeader.removeElement(flagText);

        Cell cell;
        JRDesignStaticText firstElement;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            Row row;
            for (int j = 0; j < (row = sheet.getRow(i)).getLastCellNum(); j++) {
                try {
                    cell = row.getCell(j);
                    // 复制 模板框
                    JRDesignStaticText newElement = (JRDesignStaticText) BeanUtils
                            .cloneBean(flagText);

                    // 填充内容
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            newElement.setText(cell.getNumericCellValue() + "");
                            newElement.setBackcolor(Color.YELLOW);
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                            newElement.setText(cell.getNumericCellValue() + "");
                            newElement.setBackcolor(Color.MAGENTA);
                            break;
                        default:
                            newElement.setBackcolor(Color.lightGray);
                            newElement.setText(cell.getStringCellValue());
                            break;
                    }

                    // 设置位置
                    if (j == 0) {
                        firstElement = (JRDesignStaticText) BeanUtils.cloneBean(flagText);
                        firstElement.setX(flagText.getX());
                        firstElement.setY(flagText.getY() + flagText.getHeight() * i);
                        firstElement.setWidth(20);
                        firstElement.setText((i + 1) + "");
                        firstElement.setBackcolor(Color.white);
                        columnHeader.addElement(firstElement);

                        newElement.setX(flagText.getX() + 20);
                        newElement.setBackcolor(Color.white);
                        newElement.setWidth(flagText.getWidth() - 20);
                        newElement.setY(flagText.getY() + flagText.getHeight() * i);
                        columnHeader.addElement(newElement);
                    } else {
                        newElement.setX(flagText.getX() + flagText.getWidth() * j);
                        newElement.setY(flagText.getY() + flagText.getHeight() * i);
                        columnHeader.addElement(newElement);
                    }

                } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
