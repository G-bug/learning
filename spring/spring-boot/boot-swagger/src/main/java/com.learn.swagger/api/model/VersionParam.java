/**
 * @author G-bug 2019/10/29
 */
package com.learn.swagger.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auth Administrator
 */
@ApiModel(value = "App版本检查参数", description = "App版本检查参数")
public class VersionParam {

    @ApiModelProperty(value = "IMEI码", name = "imei", example = "2324DEEFAXX122", required = true)
    private String imei;

    @ApiModelProperty(value = "APP应用ID，每个APP都有唯一的Application Id", name = "applicationId", example = "com.xncoding.xzpay", required = true)
    private String applicationId;

    @ApiModelProperty(value = "APP的当前版本号", name = "version", example = "1.2.0", required = true)
    private String version;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

