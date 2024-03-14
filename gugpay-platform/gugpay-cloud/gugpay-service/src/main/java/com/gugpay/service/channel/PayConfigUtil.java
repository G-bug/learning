/**
 * @author G-bug 2019/12/6
 */
package com.gugpay.service.channel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.config.WxPayConfig;
import org.springframework.util.Assert;

import java.io.File;

/**
 * @author G-bug
 * @Description 配置对象获取 工具类
 * @Date 2019/12/6 14:57
 */
public class PayConfigUtil {

    public static AlipayConfig getAlipayConfig(String configParam) {
        AlipayConfig alipayConfig = new AlipayConfig();
        Assert.notNull(configParam, "init alipay filter error");
        JSONObject paramObj = JSON.parseObject(configParam);
        alipayConfig.setAppId(paramObj.getString("appid"));
        alipayConfig.setRsaPrivateKey(paramObj.getString("alipay_private_key"));
        alipayConfig.setAlipayPublicKey(paramObj.getString("alipay_public_pay"));
        alipayConfig.setIsSandBox(paramObj.getShort("isSandBox"));
        if (alipayConfig.getIsSandBox() == 1) {
            alipayConfig.setRequestUrl("https://openapi.alipaydev.com/gateway.do");
        }

        return alipayConfig;
    }

    public static WxPayConfig getWxPayConfig(String configParam, String tradeType, String certRootPath, String notifyUrl) {
        WxPayConfig wxPayConfig = new WxPayConfig();
        JSONObject paramObj = JSON.parseObject(configParam);
        wxPayConfig.setMchId(paramObj.getString("mchId"));
        wxPayConfig.setAppId(paramObj.getString("appId"));
        wxPayConfig.setKeyPath(certRootPath + File.separator + paramObj.getString("certLocalPath"));
        wxPayConfig.setMchKey(paramObj.getString("key"));
        wxPayConfig.setNotifyUrl(notifyUrl);
        wxPayConfig.setTradeType(tradeType);
        return wxPayConfig;
    }

    public static WxPayConfig getWxPayConfig(String configParam) {
        WxPayConfig wxPayConfig = new WxPayConfig();
        JSONObject paramObj = JSON.parseObject(configParam);
        wxPayConfig.setMchId(paramObj.getString("mchId"));
        wxPayConfig.setAppId(paramObj.getString("appId"));
        wxPayConfig.setMchKey(paramObj.getString("key"));
        return wxPayConfig;
    }

}
