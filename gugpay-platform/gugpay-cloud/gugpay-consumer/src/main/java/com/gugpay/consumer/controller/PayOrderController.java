package com.gugpay.consumer.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.gugpay.consumer.remote.ServiceRemote;
import com.sun.jndi.toolkit.url.Uri;
import constant.PayConstant;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import util.CustomerBase64;
import util.PlatFormUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author G-bug
 * @Description
 * @Date 2020/1/10 19:01
 */
@RestController("/pay/order")
public class PayOrderController {

    @Autowired
    private ServiceRemote serviceRemote;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    private Logger log = LoggerFactory.getLogger(PayOrderController.class);

    /**
     * 统一下单
     */
    @PostMapping("/{order_info}")
    public String createPayOrder(@PathVariable("order_info") String jsonParam) {

        try {
            String serviceUri = loadBalancerClient.choose("gugpay-service").getUri().toString();
            log.info("unity carter payOrder, uri={}, request param={}", serviceUri, jsonParam);

            JSONObject paramObj = JSONObject.parseObject(jsonParam);

            // 校验
            JSONObject result = validateParams(paramObj);
            String retCode = result.getString("retCode");
            String retMsg = result.getString("retMsg");

            if (!"0".equals(retCode)) {
                return PlatFormUtils.makeRetFail(PlatFormUtils.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retMsg, null, null));
            }

            JSONObject orderJsonObj = (JSONObject) result.get("payOrder");

            // 下单
            String createObj = serviceRemote.createPayOrder(orderJsonObj.toJSONString());
            JSONObject resObj = JSON.parseObject(createObj);

            if (resObj == null || !"1".equals(resObj.getString("result"))) {
                return PlatFormUtils.makeRetFail(PlatFormUtils.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心下单失败", null, null));
            }

            String channelId = orderJsonObj.getString("channelId");
            switch (channelId) {
                case PayConstant.PAY_CHANNEL_WX_APP:
                case PayConstant.PAY_CHANNEL_WX_JSAPI:
                case PayConstant.PAY_CHANNEL_WX_MWEB:
                case PayConstant.PAY_CHANNEL_WX_NATIVE:
                    return buildTrade(orderJsonObj, channelId);
                case PayConstant.PAY_CHANNEL_ALIPAY_MOBILE:
                    return buildTrade(orderJsonObj, channelId);
                case PayConstant.PAY_CHANNEL_ALIPAY_PC:
                    return buildTrade(orderJsonObj, channelId);
                case PayConstant.PAY_CHANNEL_ALIPAY_QR:
                    return buildTrade(orderJsonObj, channelId);
                case PayConstant.PAY_CHANNEL_ALIPAY_WAP:
                    return buildTrade(orderJsonObj, channelId);
                default:
                    return PlatFormUtils.makeRetFail(PlatFormUtils.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "channelId error", null, null));
            }

        } catch (Exception e) {
            log.error("create payOrder exception={}", e);
            return PlatFormUtils.makeRetFail(PlatFormUtils.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "create payOrder exception", null, null));
        }
    }

    /**
     * 参数校验
     */
    private JSONObject validateParams(JSONObject paramObj) {
        JSONObject resultObj = new JSONObject();
        String retMsg = "";
        Map<String, String> paramMap = new HashMap<>();

        String mchId = paramObj.getString("mchId");
        String mchOrderNo = paramObj.getString("mchOrderNo");
        String channelId = paramObj.getString("channelId");
        String amount = paramObj.getString("amount");
        String currency = paramObj.getString("currency");
        String clientIp = paramObj.getString("clientIp");
        String device = paramObj.getString("devic");
        String extra = paramObj.getString("extra");
        String param1 = paramObj.getString("param1");
        String param2 = paramObj.getString("param2");
        String notifyUrl = paramObj.getString("notifyUrl");
        String sign = paramObj.getString("sign");
        String subject = paramObj.getString("subject");
        String body = paramObj.getString("body");

        paramMap.put("mchId", mchId);
        paramMap.put("mchOrderNo", mchOrderNo);
        paramMap.put("channelId", channelId);
        paramMap.put("amount", amount);
        paramMap.put("currency", currency);
        paramMap.put("notifyUrl", notifyUrl);
        paramMap.put("sign", sign);
        paramMap.put("subject", subject);
        paramMap.put("body", body);

        for (String key : paramMap.keySet()) {
            if (StringUtils.isNotBlank(paramMap.get(key))) {
                retMsg = "param error [" + key + "] is empty";
                resultObj.put("retCode", "1");
                resultObj.put("retMsg", retMsg);
                return resultObj;
            }
        }

        switch (channelId) {
            case PayConstant.PAY_CHANNEL_WX_JSAPI:
            case PayConstant.PAY_CHANNEL_WX_MWEB:
            case PayConstant.PAY_CHANNEL_WX_NATIVE: {
                if (StringUtils.isNotBlank(extra)) {
                    retMsg = channelId + " pay, [extra] must is not empty";
                    resultObj.put("retCode", "2");
                    resultObj.put("retMsg", retMsg);
                    return resultObj;
                }
            }
            default:
        }
        
        return new JSONObject();
    }

    private String buildTrade(JSONObject payOrder, String channel) {
        JSONObject obj = new JSONObject();
        String type = channel.split("_")[0];
        String trade = channel.split("_")[1];

        if (PayConstant.CHANNEL_NAME_WX.equals(type)) {
            obj.put("tradeType", trade);
            obj.put("payOrder", payOrder);
            return obj.toJSONString();
        }

        obj.put("payOrder", payOrder);
        return obj.toJSONString();
    }

    @GetMapping("/{order_info}")
    public String queryOrder(@PathVariable("order_info") String jsonParam) {
        return serviceRemote.queryOrder(CustomerBase64.encode(jsonParam));
    }

}
