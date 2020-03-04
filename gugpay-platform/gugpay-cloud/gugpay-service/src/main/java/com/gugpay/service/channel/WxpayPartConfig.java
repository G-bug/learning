/**
 * @author G-bug 2019/12/5
 */
package com.gugpay.service.channel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 * @author G-bug
 * @Description 微信支付配置
 * @Date 2019/12/6 14:54
 */
@Service
@RefreshScope
public class WxpayPartConfig {

    /**
     * https加密文件
     */
    @Value("${cert.root.path}")
    private String certRootPath;

    /**
     * 异步通知地址
     */
    @Value("${wxpay.notify_url}")
    private String notifyUrl;

    public String getCertRootPath() {
        return certRootPath;
    }

    public void setCertRootPath(String certRootPath) {
        this.certRootPath = certRootPath;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}

