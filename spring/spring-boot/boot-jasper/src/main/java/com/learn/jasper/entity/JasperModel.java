package com.learn.jasper.entity;

import lombok.Data;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author g-bug
 * @date 2020/7/22 上午9:34
 */
@Data
public class JasperModel {

    private JRBeanCollectionDataSource table;

    private String date;
}
