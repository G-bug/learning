/**
 * @author G-bug 2019/10/29
 */
package com.learn.swagger.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auth Administrator
 */
@ApiModel(value = "POS绑定网点参数", description = "POSPOS绑定见网点请求时需要传入参数")
public class BindParam {

    @ApiModelProperty(value = "IMEI码(1-32位)", name = "imei", dataType = "String", example = "23420DEEFAXX122", required = true)
    private String imei;

    @ApiModelProperty(value = "归属网点(1-64位)", name = "location", dataType = "String", example = "昆明团团圆圆", required = true)
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
