package com.gugpay.consumer.controller;

import com.gugpay.consumer.remote.ServiceRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.CustomerBase64;

/**
 * @author G-bug
 * @Description
 * @Date 2020/1/14 14:29
 */
@RestController("/pay/channel")
public class PayChannelController {

    @Autowired
    private ServiceRemote serviceRemote;

    @GetMapping("/{channel_info}")
    public String getPayChannel(@PathVariable("channel_info") String jsonParam) {
        return serviceRemote.selectPayChannel(CustomerBase64.decode(jsonParam));
    }

    @PatchMapping("/ali_wap/{info}")
    public String aliPayWap(@PathVariable("info") String jsonParam) {
        return serviceRemote.doAliPayWapReq(CustomerBase64.decode(jsonParam));
    }

    @PatchMapping("/ali_pc/{info}")
    public String doAliPayPcReq(@PathVariable("info") String jsonParam) {
        return serviceRemote.doAliPayPcReq(CustomerBase64.decode(jsonParam));
    }

    @PatchMapping("/ali_app/{info}")
    public String doAliPayMobileReq(@PathVariable("info") String jsonParam) {
        return serviceRemote.doAliPayMobileReq(CustomerBase64.decode(jsonParam));
    }

    @PatchMapping("/ali_qr/{info}")
    public String doAliPayQrReq(@PathVariable("info") String jsonParam) {
        return serviceRemote.doAliPayQrReq(CustomerBase64.decode(jsonParam));
    }

    @PatchMapping("/wx/{info}")
    public String doWxpayReq(@PathVariable("info") String jsonParam) {
        return serviceRemote.doWxpayReq(CustomerBase64.decode(jsonParam));
    }

}
