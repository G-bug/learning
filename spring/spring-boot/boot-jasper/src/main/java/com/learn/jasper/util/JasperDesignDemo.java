package com.learn.jasper.util;

import com.learn.jasper.entity.Point;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.commons.beanutils.BeanUtils;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

/**
 * @author g-bug
 * @date 2020/7/24 上午7:08
 */
public class JasperDesignDemo {

    public static JasperReport getJasperReport(String xmlFilePath,
                                               List<Point> dataSource) throws JRException {
        JasperDesign design = getJasperDesign(xmlFilePath);

        JRDesignBand columnHeader = (JRDesignBand) design.getColumnHeader();
        reSetColumnHeaderHeight(columnHeader, dataSource);
        reSetshapeAndPosition(columnHeader, dataSource);
        addElementToColumnHeader(columnHeader, dataSource);
        return JasperCompileManager.compileReport(design);
    }

    private static JasperDesign getJasperDesign(String filePath)
            throws JRException {
        return JRXmlLoader.load(filePath);
    }

    private static void reSetColumnHeaderHeight(JRDesignBand columnHeader,
                                                List<Point> dataSource) {
        columnHeader.setHeight(columnHeader.getHeight() * dataSource.size() + 20);
    }

    private static JRDesignStaticText getFlagTextInDesign(
            JRDesignBand columnHeader) {
        return (JRDesignStaticText) columnHeader.getElementByKey("flag");
    }

    /**
     * 处理 columnHeader中的内容
     */
    private static void reSetshapeAndPosition(JRDesignBand columnHeader,
                                              List<Point> dataSource) {
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
                                                 List<Point> dataSource) {
        // 删除 模板 框
        JRDesignStaticText flagText = getFlagTextInDesign(columnHeader);
        columnHeader.removeElement(flagText);

        Point point;
        JRDesignStaticText firstElement;
        for (int i = 0; i < dataSource.size(); i++) {

            for (int j = 0; j < dataSource.get(i).getRow().size(); j++) {
                try {
                    point = dataSource.get(i).getRow().get(j);
                    // 复制 模板框
                    JRDesignStaticText newElement = (JRDesignStaticText) BeanUtils
                            .cloneBean(flagText);
                    // 填充内容
                    newElement.setText(point.getCont());
                    if ("1".equals(point.getType())) {
                        newElement.setBackcolor(Color.YELLOW);
                    } else if ("2".equals(point.getType())) {
                        newElement.setBackcolor(Color.white);
                    } else if ("3".equals(point.getType())) {
                        newElement.setBackcolor(Color.LIGHT_GRAY);
                    } else if ("4".equals(point.getType())) {
                        newElement.setBackcolor(Color.MAGENTA);
                    } else if ("5".equals(point.getType())) {
                        newElement.setBackcolor(Color.MAGENTA);
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
