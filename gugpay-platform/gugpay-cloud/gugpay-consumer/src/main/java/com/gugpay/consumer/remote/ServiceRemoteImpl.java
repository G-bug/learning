/**
 * @author G-bug 2020/1/10
 */
package com.gugpay.consumer.remote;

import org.springframework.stereotype.Component;

/**
 * @author G-bug
 * @Description 熔断处理类
 * @Date 2020/1/10 14:37
 */
@Component
public class ServiceRemoteImpl implements ServiceRemote {

    @Override
    public String getMchInfo(String jsonParam) {
        return "remote error, hystrix response";
    }

    @Override
    public String selectPayChannel(String jsonParam) {
        return "remote error, hystrix response";
    }

    @Override
    public String doAliPayWapReq(String jsonParam) {
        return "remote error, hystrix response";
    }

    @Override
    public String doAliPayPcReq(String jsonParam) {
        return "remote error, hystrix response";
    }

    @Override
    public String doAliPayMobileReq(String jsonParam) {
        return "remote error, hystrix response";
    }

    @Override
    public String doAliPayQrReq(String jsonParam) {
        return "remote error, hystrix response";
    }

    @Override
    public String doWxpayReq(String jsonParam) {
        return "remote error, hystrix response";
    }

    @Override
    public void alipayNotifyRes() {
    }

    @Override
    public String wxpayNotifyRes() {
        return "remote error, hystrix response";
    }

    @Override
    public String queryOrder(String jsonParam) {
        return "remote error, hystrix response";
    }

    @Override
    public String createPayOrder(String jsonParam) {
        return "remote error, hystrix response";
    }
}
