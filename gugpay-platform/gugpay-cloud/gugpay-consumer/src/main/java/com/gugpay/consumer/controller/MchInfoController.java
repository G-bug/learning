package com.gugpay.consumer.controller;

import com.gugpay.consumer.remote.ServiceRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import util.CustomerBase64;

/**
 * @author G-bug
 * @Description
 * @Date 2020/1/10 15:00
 */
@RestController
public class MchInfoController {

    @Autowired
    private ServiceRemote remote;

    @GetMapping("/mch/{info}")
    public String getMchInfo(@PathVariable("info") String jsonParam) {
        return remote.getMchInfo(CustomerBase64.encode(jsonParam));
    }

}
