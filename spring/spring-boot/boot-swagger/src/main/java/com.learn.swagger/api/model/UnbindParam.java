/**
 * @author G-bug 2019/10/29
 */
package com.learn.swagger.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auth Administrator
 */
@ApiModel(value = "解绑通知参数", description = "解绑通知参数")
public class UnbindParam {

    @ApiModelProperty(value = "IMEI码", name = "imei", example = "TUYIUOIU234234YUII")
    private String imei;

    @ApiModelProperty(value = "网点", name = "location", example = "舒服")
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
