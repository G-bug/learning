/**
 * @author G-bug 2019/12/5
 */
package com.gugpay.service.channel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author G-bug
 * @Description alipay 配置
 * @Date 2019/12/6 14:54
 */
@Service
@RefreshScope
public class AlipayConfig {

    /**
     * 商户 appId
     */
    private String appId;

    /**
     * 私钥
     */
    private String rsaPrivateKey;

    /**
     * 异步通知地址 http/https
     */
    @Value("${alipay.notify_url}")
    private String notifyUrl;

    /**
     * 同步跳转地址
     */
    @Value("${alipay.return_url}")
    private String returnUrl;

    /**
     * 下单地址
     */
    @Value("${alipay.request_url}")
    private String requestUrl;

    /**
     * 加密方式
     */
    @Value("${alipay.signtype}")
    public String signType;

    /**
     * 加密公钥
     */
    private String alipayPublicKey;

    /**
     * 是否沙箱 1:是, 0:否
     */
    private Short isSandBox = 0;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getRsaPrivateKey() {
        return rsaPrivateKey;
    }

    public void setRsaPrivateKey(String rsaPrivateKey) {
        this.rsaPrivateKey = rsaPrivateKey;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    public Short getIsSandBox() {
        return isSandBox;
    }

    public void setIsSandBox(Short isSandBox) {
        this.isSandBox = isSandBox;
    }
}
