/**
 * @author G-bug 2020/1/2
 */
package com.gugpay.service.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gugpay.dal.dao.model.PayChannel;
import com.gugpay.service.service.PayChannelService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import util.CustomerBase64;

/**
 * @author G-bug
 * @Description 获取支付渠道(mchId + channelId)
 * @Date 2020/1/7 15:13
 */
@RestController
public class PayChannelController {

    private Logger log = LoggerFactory.getLogger(PayChannelController.class);

    @Autowired
    private PayChannelService payChannelService;

    @RequestMapping("/pay_channel/select")
    public String selectPayChannel(@RequestParam String jsonParam) {
        log.info("select payChannel request param={}", jsonParam);
        JSONObject retObj = new JSONObject();

        if (StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001");
            retObj.put("msg", "param is empty");
            return retObj.toJSONString();
        }

        JSONObject paramObj = JSON.parseObject(CustomerBase64.decode(jsonParam));
        String channelId = paramObj.getString("channelId");
        String mchId = paramObj.getString("mchId");
        PayChannel payChannel = payChannelService.selectPayChannel(channelId, mchId);
        if (payChannel == null) {
            retObj.put("code", "0002");
            retObj.put("msg", "db-payChannel is null");
            return retObj.toJSONString();
        }

        retObj.put("code", "0000");
        retObj.put("result", JSON.toJSON(payChannel));

        log.info("select payChannel retObj={}", retObj);
        return retObj.toJSONString();
    }

}
