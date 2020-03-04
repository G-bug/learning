/**
 * @author G-bug 2020/1/2
 */
package com.gugpay.service.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.github.binarywang.wxpay.util.SignUtils;
import com.gugpay.dal.dao.model.MchInfo;
import com.gugpay.dal.dao.model.PayChannel;
import com.gugpay.dal.dao.model.PayOrder;
import com.gugpay.service.channel.PayConfigUtil;
import com.gugpay.service.channel.WxpayPartConfig;
import com.gugpay.service.service.MchInfoService;
import com.gugpay.service.service.PayChannelService;
import com.gugpay.service.service.PayOrderService;
import constant.PayConstant;
import enums.ErrCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import util.CustomerBase64;
import util.PlatFormUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author G-bug
 * @Description
 * @Date 2020/1/6 11:12
 */
@RestController
public class PayChannelWxController {

    private Logger log = LoggerFactory.getLogger(PayChannelWxController.class);

    @Autowired
    private PayChannelService payChannelService;

    @Autowired
    private PayOrderService payOrderService;

    @Autowired
    private MchInfoService mchInfoService;

    @Autowired
    private WxpayPartConfig wxpayPartConfig;

    @RequestMapping("/pay/channel/wx")
    public String doWxpayReq(@RequestParam String jsonParam) {
        try {
            JSONObject paramObj = JSON.parseObject(CustomerBase64.decode(jsonParam));
            String tradeType = paramObj.getString("tradeType");
            PayOrder payOrder = paramObj.getObject("payOrder", PayOrder.class);
            String mchId = payOrder.getMchId();
            String channelId = payOrder.getChannelId();
            MchInfo mchInfo = mchInfoService.selectMchInfo(mchId);
            String resKey = ((mchInfo == null) ? "" : mchInfo.getResKey());
            if ("".equals(resKey)) {
                log.info("wx pay machInfo resKey is empty");
                return PlatFormUtils.makeRetFail(PlatFormUtils.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "resKey is empty",
                        PayConstant.RETURN_VALUE_FAIL, ErrCodeEnum.ERR_0001));
            }

            PayChannel payChannel = payChannelService.selectPayChannel(channelId, mchId);
            WxPayConfig wxPayConfig = PayConfigUtil.getWxPayConfig(payChannel.getParam(), tradeType,
                    wxpayPartConfig.getCertRootPath(), wxpayPartConfig.getNotifyUrl());
            WxPayService wxPayService = new WxPayServiceImpl();
            wxPayService.setConfig(wxPayConfig);

            // 构建 统一下单请求 对象
            WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = buildUnifiedOrderRequest(payOrder, wxPayConfig);

            String payOrderId = payOrder.getPayOrderId();
            WxPayUnifiedOrderResult wxPayUnifiedOrderResult;
            try {
                // 统一下单
                wxPayUnifiedOrderResult = wxPayService.unifiedOrder(wxPayUnifiedOrderRequest);
                log.info("wx_wpa unified order success");

                Map<String, Object> map = PlatFormUtils.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "",
                        PayConstant.RETURN_VALUE_SUCCESS, null);
                map.put("payOrderId", payOrderId);
                map.put("prepayId", wxPayUnifiedOrderResult.getPrepayId());

                int record = payOrderService.updateStatusIng(payOrderId, channelId);
                log.info("update payOrder status->{},record={}", PayConstant.PAY_STATUS_PAYING, record);

                // 支付类型
                switch (tradeType) {
                    case PayConstant.WxConstant.TRADE_TYPE_NATIVE: {
                        map.put("codeUrl", wxPayUnifiedOrderResult.getCodeURL());
                        break;
                    }
                    case PayConstant.WxConstant.TRADE_TYPE_APP: {
                        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
                        // 随机串
                        String nonceStr = String.valueOf(System.currentTimeMillis());
                        // 微信开放平台绑定App 得到的appId
                        String appId = wxPayConfig.getAppId();
                        String partnerId = wxPayConfig.getMchId();
                        String packageValue = "Sign=WXPay";

                        // 用于调起支付的参数, 格式固定
                        Map<String, String> configMap = new HashMap<>(16);
                        configMap.put("prepayid", wxPayUnifiedOrderResult.getPrepayId());
                        configMap.put("partnerid", partnerId);
                        configMap.put("package", packageValue);
                        configMap.put("timestamp", timestamp);
                        configMap.put("noncestr", nonceStr);
                        configMap.put("appid", appId);

                        // 与微信服务器通信所需参数
                        Map<String, String> payInfo = new HashMap<>();
                        payInfo.put("sign", SignUtils.createSign(configMap, wxPayConfig.getMchKey(), null));
                        payInfo.put("prepayId", wxPayUnifiedOrderResult.getPrepayId());
                        payInfo.put("partnerId", partnerId);
                        payInfo.put("appId", appId);
                        payInfo.put("packageValue", packageValue);
                        payInfo.put("nonceStr", nonceStr);

                        map.put("payParams", payInfo);
                        break;
                    }
                    case PayConstant.WxConstant.TRADE_TYPE_JSPAI: {
                        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
                        String nonceStr = String.valueOf(System.currentTimeMillis());

                        // 调起支付所需
                        Map<String, String> payInfo = new HashMap<>();
                        payInfo.put("appId", wxPayUnifiedOrderResult.getAppid());
                        payInfo.put("timeStamp", timestamp);
                        payInfo.put("nonceStr", nonceStr);
                        payInfo.put("package", "prepay_id=" + wxPayUnifiedOrderResult.getPrepayId());
                        payInfo.put("signType", WxPayConstants.SignType.MD5);
                        payInfo.put("paySign", SignUtils.createSign(payInfo, wxPayConfig.getMchKey(), null));

                        map.put("payParams", payInfo);
                        break;
                    }
                    case PayConstant.WxConstant.TRADE_TYPE_MWEB: {
                        map.put("payUrl", wxPayUnifiedOrderResult.getMwebUrl());
                        break;
                    }
                    default:
                        break;
                }

                return PlatFormUtils.makeRetData(map, resKey);

            } catch (WxPayException e) {
                log.info("wx_pay unified order fail", e);
                return PlatFormUtils.makeRetData(PlatFormUtils.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "", PayConstant.RETURN_VALUE_FAIL,
                        "0111", "wx_pay unified order fail," + e.getErrCode() + ":" + e.getErrCodeDes()), resKey);
            }
        } catch (Exception e) {
            log.error("wx_pay unified order request exception", e);
            return PlatFormUtils.makeRetFail(PlatFormUtils.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "",
                    PayConstant.RETURN_VALUE_FAIL, ErrCodeEnum.ERR_0001));
        }
    }

    private WxPayUnifiedOrderRequest buildUnifiedOrderRequest(PayOrder payOrder, WxPayConfig wxPayConfig) {
        String tradeType = wxPayConfig.getTradeType();
        String payOrderId = payOrder.getPayOrderId();
        Integer totalFee = payOrder.getAmount().intValue();
        String deviceInfo = payOrder.getDevice();
        String body = payOrder.getBody();
        String spBillCreateIp = payOrder.getClientIp();
        String notifyUrl = wxPayConfig.getNotifyUrl();
        String feeType = "CNY";

        String productId = null;
        String openId = null;
        String sceneInfo = null;

        switch (tradeType) {
            case PayConstant.WxConstant.TRADE_TYPE_NATIVE:
                productId = JSON.parseObject(payOrder.getExtra()).getString("productId");
                break;
            case PayConstant.WxConstant.TRADE_TYPE_JSPAI:
                openId = JSON.parseObject(payOrder.getExtra()).getString("openId");
                break;
            case PayConstant.WxConstant.TRADE_TYPE_MWEB:
                sceneInfo = JSON.parseObject(payOrder.getExtra()).getString("sceneInfo");
                break;
            default:
                break;
        }

        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
        request.setOutTradeNo(payOrderId);
        request.setFeeType(feeType);
        request.setTotalFee(totalFee);
        request.setSpbillCreateIp(spBillCreateIp);
        request.setDeviceInfo(deviceInfo);
        request.setBody(body);
        request.setNotifyURL(notifyUrl);
        request.setTradeType(tradeType);
        request.setProductId(productId);
        request.setOpenid(openId);
        request.setSceneInfo(sceneInfo);

        // 非必需参数
        request.setDetail(null);
        request.setAttach(null);
        request.setTimeStart(null);
        request.setTimeExpire(null);
        request.setGoodsTag(null);
        request.setLimitPay(null);
        return request;
    }

}
