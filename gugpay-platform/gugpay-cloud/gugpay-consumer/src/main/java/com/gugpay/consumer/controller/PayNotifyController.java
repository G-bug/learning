package com.gugpay.consumer.controller;

import com.gugpay.consumer.remote.ServiceRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author G-bug
 * @Description 支付回调
 * @Date 2020/1/10 19:01
 */
@RestController("/pay/notify")
public class PayNotifyController {

    @Autowired
    private ServiceRemote serviceRemote;

    @PutMapping("/wx")
    public String wxpayNotifyRes() {
        return serviceRemote.wxpayNotifyRes();
    }

    @PutMapping("/ali")
    public void alipayNotifyRes() {
        serviceRemote.alipayNotifyRes();
    }

}
