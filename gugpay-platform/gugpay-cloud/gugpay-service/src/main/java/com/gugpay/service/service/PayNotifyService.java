/**
 * @author G-bug 2019/12/26
 */
package com.gugpay.service.service;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.gugpay.dal.dao.model.MchInfo;
import com.gugpay.dal.dao.model.PayOrder;
import com.gugpay.service.mq.PayNotifyMq;
import constant.PayConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.DigestUtil;
import util.PlatFormUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author G-bug
 * @Description 支付回调服务
 * @Date 2019/12/26 10:34
 */
@Service
public class PayNotifyService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MchInfoService mchInfoService;

    @Autowired
    private PayNotifyMq payNotifyMq;

    /**
     * 拼接所需参数
     */
    private String createNotifyUrl(PayOrder payOrder, String backType) {
        String mchId = payOrder.getMchId();
        MchInfo mchInfo = mchInfoService.selectMchInfo(mchId);
        String resKey = mchInfo.getResKey();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("payOrderId", payOrder.getPayOrderId() == null ? "" : payOrder.getPayOrderId());
        paramMap.put("mchId", payOrder.getMchId() == null ? "" : payOrder.getMchId());
        paramMap.put("mchOrderNo", payOrder.getMchOrderNo() == null ? "" : payOrder.getMchOrderNo());
        paramMap.put("channelId", payOrder.getChannelMchId() == null ? "" : payOrder.getChannelId());
        paramMap.put("amount", payOrder.getAmount() == null ? "" : payOrder.getAmount());
        paramMap.put("currency", payOrder.getCurrency() == null ? "" : payOrder.getCurrency());
        paramMap.put("clientId", payOrder.getClientIp() == null ? "" : payOrder.getClientIp());
        paramMap.put("status", payOrder.getStatus() == null ? "" : payOrder.getStatus());
        paramMap.put("device", payOrder.getDevice() == null ? "" : payOrder.getDevice());
        paramMap.put("subject", payOrder.getSubject() == null ? "" : payOrder.getSubject());
        paramMap.put("channelOrderNo", payOrder.getChannelOrderNo() == null ? "" : payOrder.getChannelOrderNo());
        paramMap.put("param1", payOrder.getParam1() == null ? "" : payOrder.getParam1());
        paramMap.put("param2", payOrder.getParam2() == null ? "" : payOrder.getParam2());
        paramMap.put("paySuccTime", payOrder.getPaySuccTime() == null ? "" : payOrder.getPaySuccTime());
        // TODO: backType 不知其义
        paramMap.put("backType", backType == null ? "" : backType);

        String reqSign = DigestUtil.getSign(paramMap, resKey);
        paramMap.put("sign", reqSign);
        try {
            paramMap.put("device", URLEncoder.encode(paramMap.get("device").toString(), PayConstant.RESP_UTF8));
            paramMap.put("subject", URLEncoder.encode(paramMap.get("subject").toString(), PayConstant.RESP_UTF8));
            paramMap.put("param1", URLEncoder.encode(paramMap.get("param1").toString(), PayConstant.RESP_UTF8));
            paramMap.put("param2", URLEncoder.encode(paramMap.get("param2").toString(), PayConstant.RESP_UTF8));
        } catch (UnsupportedEncodingException e) {
            log.error("URL CN Encode exception", e);
            return null;
        }

        String param = PlatFormUtils.toUrlQueryString(paramMap);
        return payOrder.getNotifyUrl() + "?" + param;
    }

    private JSONObject createNotifyInfo(PayOrder payOrder) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("method", "GET");
        jsonObject.put("url", createNotifyUrl(payOrder, "2"));
        jsonObject.put("orderId", payOrder.getPayOrderId());
        jsonObject.put("count", payOrder.getNotifyCount());
        jsonObject.put("createTime", System.currentTimeMillis());
        return jsonObject;
    }

    public void doNotify(PayOrder payOrder) {
        log.info("pay notify mch start");
        JSONObject jsonObject = createNotifyInfo(payOrder);
        try {
            payNotifyMq.send(jsonObject.toJSONString());
        } catch (Exception e) {
            log.error("pay notify mch exception={}, url={}", e, jsonObject.toJSONString());
        }
        log.info("pay notify mch over");
    }

}
