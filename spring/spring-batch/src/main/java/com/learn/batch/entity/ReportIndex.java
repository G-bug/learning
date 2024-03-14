package com.learn.batch.entity;

import lombok.Data;

/**
 * @author g-bug
 * @date 2020/10/9 下午6:42
 */
@Data
public class ReportIndex {

    private Integer id;

    private String code;

    private String val;

    private String sqlStr;

    private String dependency;

}
