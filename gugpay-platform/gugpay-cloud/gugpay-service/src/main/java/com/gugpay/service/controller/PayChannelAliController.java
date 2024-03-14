/**
 * @author G-bug 2020/1/2
 */
package com.gugpay.service.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.gugpay.dal.dao.model.MchInfo;
import com.gugpay.dal.dao.model.PayChannel;
import com.gugpay.dal.dao.model.PayOrder;
import com.gugpay.service.channel.AlipayConfig;
import com.gugpay.service.channel.PayConfigUtil;
import com.gugpay.service.service.MchInfoService;
import com.gugpay.service.service.PayChannelService;
import com.gugpay.service.service.PayOrderService;
import constant.PayConstant;
import enums.ErrCodeEnum;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import util.CurrencyUtil;
import util.CustomerBase64;
import util.PlatFormUtils;

import java.util.Currency;
import java.util.Map;

/**
 * @author G-bug
 * @Description alipay
 * @Date 2020/1/2 17:11
 */
@RestController
public class PayChannelAliController {

    private Logger log = LoggerFactory.getLogger(PayChannelAliController.class);

    @Autowired
    private PayOrderService payOrderService;

    @Autowired
    private PayChannelService payChannelService;

    @Autowired
    private MchInfoService mchInfoService;

    @Autowired
    private AlipayConfig alipayConfig;

    /**
     * 手机H5
     */
    @RequestMapping("pay/channel/ali_wap")
    public String doAliPayWapReq(@RequestParam String jsonParam) {
        JSONObject paramObj = JSON.parseObject(CustomerBase64.decode(jsonParam));
        PayOrder payOrder = paramObj.getObject("payOrder", PayOrder.class);
        String payOrderId = payOrder.getPayOrderId();
        String mchId = payOrder.getMchId();
        String channelId = payOrder.getChannelId();
        MchInfo mchInfo = mchInfoService.selectMchInfo(mchId);
        String resKey = (mchInfo == null) ? "" : mchInfo.getResKey();

        if ("".equals(resKey)) {
            log.error("ali_wpa create order, mch resKey is empty");
            return PlatFormUtils.makeRetFail(PlatFormUtils.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "resKey is empty",
                    PayConstant.RETURN_VALUE_FAIL, ErrCodeEnum.ERR_0001));
        }

        PayChannel payChannel = payChannelService.selectPayChannel(channelId, mchId);
        alipayConfig = PayConfigUtil.getAlipayConfig(payChannel.getParam());

        AlipayClient client = new DefaultAlipayClient(alipayConfig.getRequestUrl(), alipayConfig.getAppId(),
                alipayConfig.getRsaPrivateKey(), PayConstant.RETURN_ALIPAY_FORMAT, PayConstant.RETURN_ALIPAY_SIGN_CHARSET,
                alipayConfig.getAlipayPublicKey(), PayConstant.RETURN_ALIPAY_SIGN_TYPE);

        AlipayTradeWapPayRequest aliPayRequest = new AlipayTradeWapPayRequest();
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(payOrderId);
        model.setSubject(payOrder.getSubject());
        model.setTotalAmount(CurrencyUtil.centToDollar(payOrder.getAmount().toString()));
        model.setBody(payOrder.getBody());

        // extra, 特定channel的附加参数(如: openId)
        String objParams = payOrder.getExtra();
        if (StringUtils.isNotEmpty(objParams)) {
            try {
                JSONObject objParamsJson = JSON.parseObject(objParams);
                // 付款中途退出时的返回地址
                if (StringUtils.isNotBlank(objParamsJson.getString("quit_url"))) {
                    model.setQuitUrl(objParamsJson.getString("quit_url"));
                }
            } catch (Exception e) {
                log.error("ali_wpa extra={} is not empty, but error", objParams);
            }
        }

        // 公共参数之外的所有参数的集合
        aliPayRequest.setBizModel(model);
        aliPayRequest.setNotifyUrl(alipayConfig.getNotifyUrl());
        aliPayRequest.setReturnUrl(alipayConfig.getReturnUrl());
        String payUrl = null;

        try {
            // 完成请求
            payUrl = client.pageExecute(aliPayRequest).getBody();
        } catch (AlipayApiException e) {
            log.info("ali_wpa request exception");
            e.printStackTrace();
        }

        // 更新payOrder状态
        int record = payOrderService.updateStatusIng(payOrderId, null);
        log.info("ali_wap update payOrder status->{},rows={}", PayConstant.PAY_STATUS_PAYING, record);

        // 请求后输出
        Map<String, Object> map = PlatFormUtils.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "ali_wpa success",
                PayConstant.RETURN_VALUE_SUCCESS, null);
        map.put("payOrderId", payOrderId);
        map.put("payUrl", payUrl);

        return PlatFormUtils.makeRetData(map, resKey);
    }

    /**
     * 电脑支付
     */
    @RequestMapping("pay/channel/ali_pc")
    public String doAliPayPcReq(@RequestParam String jsonParam) {
        JSONObject paramObj = JSON.parseObject(CustomerBase64.decode(jsonParam));
        PayOrder payOrder = paramObj.getObject("payOrder", PayOrder.class);
        String payOrderId = payOrder.getPayOrderId();
        String mchId = payOrder.getMchId();
        String channelId = payOrder.getChannelId();

        MchInfo mchInfo = mchInfoService.selectMchInfo(mchId);
        String resKey = (mchInfo == null ? "" : mchInfo.getResKey());
        if ("".equals(resKey)) {
            return PlatFormUtils.makeRetFail(PlatFormUtils.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "resPay is empty",
                    PayConstant.RETURN_VALUE_FAIL, ErrCodeEnum.ERR_0001));
        }

        PayChannel payChannel = payChannelService.selectPayChannel(channelId, mchId);
        alipayConfig = PayConfigUtil.getAlipayConfig(payChannel.getParam());
        AlipayClient client = new DefaultAlipayClient(alipayConfig.getNotifyUrl(), alipayConfig.getAppId(), alipayConfig.getRsaPrivateKey(),
                PayConstant.RETURN_ALIPAY_FORMAT, PayConstant.RETURN_ALIPAY_SIGN_CHARSET, alipayConfig.getAlipayPublicKey(), PayConstant.RETURN_ALIPAY_SIGN_TYPE);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        AlipayTradePagePayModel aliPayModel = new AlipayTradePagePayModel();
        aliPayModel.setOutTradeNo(payOrderId);
        aliPayModel.setSubject(payOrder.getSubject());
        aliPayModel.setTotalAmount(CurrencyUtil.centToDollar(payOrder.getAmount().toString()));
        aliPayModel.setBody(payOrder.getBody());
        aliPayModel.setProductCode("FAST_INSTANT_TRADE_PAY");

        String objParams = payOrder.getExtra();
        // pc扫码模式 自嵌二维码(0,1,3) 或 利用支付宝页面(2,4(可定义支付宝页面的二维码大小))
        String qrPayMode = "2";
        // mode=4 时有效
        String qrCodeWidth = "200";
        if (StringUtils.isNotEmpty(objParams)) {
            try {
                JSONObject objParamsJson = JSON.parseObject(objParams);
                qrPayMode = ObjectUtils.toString(objParamsJson.getString("qr_pay_mode"), "2");
                qrCodeWidth = ObjectUtils.toString(objParamsJson.getString("qrcode_width"), "200");
            } catch (Exception e) {
                log.error("ali_pc extra={} is not empty, but error", objParams);
            }
        }
        aliPayModel.setQrPayMode(qrPayMode);
        aliPayModel.setQrcodeWidth(Long.valueOf(qrCodeWidth));

        alipayRequest.setBizModel(aliPayModel);
        alipayRequest.setNotifyUrl(alipayConfig.getNotifyUrl());
        alipayRequest.setReturnUrl(alipayConfig.getReturnUrl());
        String payUrl = null;
        try {
            payUrl = client.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            log.error("ali_pc request exception", e);
            e.printStackTrace();
        }

        log.info("ali_pc request response payUrl={}", payUrl);
        int record = payOrderService.updateStatusIng(payOrderId, null);
        log.info("ali_pc update payOrder status->{},rows={}", PayConstant.PAY_STATUS_PAYING, record);

        Map<String, Object> map = PlatFormUtils.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "ali_pc success",
                PayConstant.RETURN_VALUE_SUCCESS, null);
        map.put("payOrderId", payOrderId);
        map.put("payUrl", payUrl);
        return PlatFormUtils.makeRetData(map, resKey);
    }

    /**
     * app支付
     */
    @RequestMapping("pay/channel/ali_app")
    public String doAliPayMobileReq(@RequestParam String jsonParam) {
        JSONObject paramObj = JSON.parseObject(CustomerBase64.decode(jsonParam));
        PayOrder payOrder = paramObj.getObject("payOrder", PayOrder.class);
        String payOrderId = payOrder.getPayOrderId();
        String mchId = payOrder.getMchId();
        String channelId = payOrder.getChannelId();

        MchInfo mchInfo = mchInfoService.selectMchInfo(mchId);
        String resKey = (mchInfo == null ? "" : mchInfo.getResKey());
        if ("".equals(resKey)) {
            return PlatFormUtils.makeRetFail(PlatFormUtils.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "resPay is empty",
                    PayConstant.RETURN_VALUE_FAIL, ErrCodeEnum.ERR_0001));
        }

        PayChannel payChannel = payChannelService.selectPayChannel(channelId, mchId);
        alipayConfig = PayConfigUtil.getAlipayConfig(payChannel.getParam());
        AlipayClient client = new DefaultAlipayClient(alipayConfig.getNotifyUrl(), alipayConfig.getAppId(), alipayConfig.getRsaPrivateKey(),
                PayConstant.RETURN_ALIPAY_FORMAT, PayConstant.RETURN_ALIPAY_SIGN_CHARSET, alipayConfig.getAlipayPublicKey(), PayConstant.RETURN_ALIPAY_SIGN_TYPE);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        AlipayTradePagePayModel aliPayModel = new AlipayTradePagePayModel();
        aliPayModel.setOutTradeNo(payOrderId);
        aliPayModel.setSubject(payOrder.getSubject());
        aliPayModel.setTotalAmount(CurrencyUtil.centToDollar(payOrder.getAmount().toString()));
        aliPayModel.setBody(payOrder.getBody());
        aliPayModel.setProductCode("QUICK_SECURITY_PAY");

        alipayRequest.setBizModel(aliPayModel);
        alipayRequest.setNotifyUrl(alipayConfig.getNotifyUrl());
        alipayRequest.setReturnUrl(alipayConfig.getReturnUrl());

        String payParams = null;
        try {
            payParams = client.sdkExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            log.error("ali_mobile request exception", e);
        }

        int record = payOrderService.updateStatusIng(payOrderId, null);
        log.info("ali_mobile update payOrder status->{},rows={}", PayConstant.PAY_STATUS_PAYING, record);

        Map<String, Object> map = PlatFormUtils.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "ali_mobile success",
                PayConstant.RETURN_VALUE_SUCCESS, null);
        map.put("payOrderId", payOrderId);
        map.put("payParams", payParams);
        return PlatFormUtils.makeRetData(map, resKey);
    }

    /**
     * 当面扫码
     */
    @RequestMapping("pay/channel/ali_qr")
    public String doAliPayQrReq(@RequestParam String jsonParam) {
        JSONObject paramObj = JSON.parseObject(CustomerBase64.decode(jsonParam));
        PayOrder payOrder = paramObj.getObject("payOrder", PayOrder.class);
        String payOrderId = payOrder.getPayOrderId();
        String mchId = payOrder.getMchId();
        String channelId = payOrder.getChannelId();

        MchInfo mchInfo = mchInfoService.selectMchInfo(mchId);
        String resKey = (mchInfo == null ? "" : mchInfo.getResKey());
        if ("".equals(resKey)) {
            return PlatFormUtils.makeRetFail(PlatFormUtils.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "resPay is empty",
                    PayConstant.RETURN_VALUE_FAIL, ErrCodeEnum.ERR_0001));
        }

        PayChannel payChannel = payChannelService.selectPayChannel(channelId, mchId);
        alipayConfig = PayConfigUtil.getAlipayConfig(payChannel.getParam());
        AlipayClient client = new DefaultAlipayClient(alipayConfig.getNotifyUrl(), alipayConfig.getAppId(), alipayConfig.getRsaPrivateKey(),
                PayConstant.RETURN_ALIPAY_FORMAT, PayConstant.RETURN_ALIPAY_SIGN_CHARSET, alipayConfig.getAlipayPublicKey(), PayConstant.RETURN_ALIPAY_SIGN_TYPE);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        AlipayTradePrecreateModel aliPayModel = new AlipayTradePrecreateModel();
        aliPayModel.setOutTradeNo(payOrderId);
        aliPayModel.setSubject(payOrder.getSubject());
        aliPayModel.setTotalAmount(CurrencyUtil.centToDollar(payOrder.getAmount().toString()));
        aliPayModel.setBody(payOrder.getBody());

        String objParams = payOrder.getExtra();
        if (StringUtils.isNotEmpty(objParams)) {
            try {
                JSONObject objParamsJson = JSON.parseObject(objParams);
                if (StringUtils.isNotBlank(objParamsJson.getString("discountable_amount"))) {
                    //可打折金额
                    aliPayModel.setDiscountableAmount(objParamsJson.getString("discountable_amount"));
                }
                if (StringUtils.isNotBlank(objParamsJson.getString("undiscountable_amount"))) {
                    //不可打折金额
                    aliPayModel.setUndiscountableAmount(objParamsJson.getString("undiscountable_amount"));
                }
            } catch (Exception e) {
                log.error("ali_qr extra={} is not empty, but error", objParams);
            }
        }

        alipayRequest.setBizModel(aliPayModel);
        alipayRequest.setNotifyUrl(alipayConfig.getNotifyUrl());
        alipayRequest.setReturnUrl(alipayConfig.getReturnUrl());

        String payUrl = null;
        try {
            payUrl = client.sdkExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            log.error("ali_qr request exception", e);
        }

        log.info("ali_qr request response payUrl={}", payUrl);
        int record = payOrderService.updateStatusIng(payOrderId, null);
        log.info("ali_qr update payOrder status->{},rows={}", PayConstant.PAY_STATUS_PAYING, record);

        Map<String, Object> map = PlatFormUtils.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "ali_qr success",
                PayConstant.RETURN_VALUE_SUCCESS, null);
        map.put("payOrderId", payOrderId);
        map.put("payUrl", payUrl);
        return PlatFormUtils.makeRetData(map, resKey);
    }
}
