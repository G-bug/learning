/**
 * @author G-bug 2019/10/29
 */
package com.learn.swagger.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auth Administrator
 */
@ApiModel(value = "报告参数", description = "报告参数")
public class ReportParam {

    @ApiModelProperty(value = "IMEI码（长度1-32位）", name = "imei", example = "2324DEEFAXX122", required = true)
    private String imei;

    @ApiModelProperty(value = "位置（长度1-255位）", name = "location", example = "广东省广州市天河区五山路321号", required = true)
    private String location;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
