/**
 * @author G-bug 2019/10/29
 */
package com.learn.swagger.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @auth Administrator
 */
@ApiModel(value = "App版本检查结果", description = "App版本检查结果")
public class VersionResult {

    @ApiModelProperty(value = "是否发现新版本(true:发现新版本，false:没有发现新版本)", name = "findNew", example = "true", required = true)
    private boolean findNew;

    @ApiModelProperty(value = "APP名称", name = "appName", example = "行政收费")
    private String appName;

    @ApiModelProperty(value = "新版本号", name = "version", example = "v1.3.8")
    private String version;

    @ApiModelProperty(value = "新版本说明", name = "tips", example = "增加人脸识别功能")
    private String tips;

    @ApiModelProperty(value = "新版本发布时间", name = "publishtime", example = "2017-12-24 12:32:19")
    private Date publishtime;

    @ApiModelProperty(value = "新版本下载地址", name = "downloadUrl", example = "http://xncoding.net/files/行政收费_1.3.0.apk")
    private String downloadUrl;

    public boolean isFindNew() {
        return findNew;
    }

    public void setFindNew(boolean findNew) {
        this.findNew = findNew;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}

