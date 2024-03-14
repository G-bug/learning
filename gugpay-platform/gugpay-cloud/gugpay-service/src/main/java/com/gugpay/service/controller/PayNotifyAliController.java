/**
 * @author G-bug 2019/12/26
 */
package com.gugpay.service.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.gugpay.dal.dao.model.PayChannel;
import com.gugpay.dal.dao.model.PayOrder;
import com.gugpay.service.channel.PayConfigUtil;
import com.gugpay.service.service.PayChannelService;
import com.gugpay.service.service.PayNotifyService;
import com.gugpay.service.service.PayOrderService;
import constant.PayConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author G-bug
 * @Description ali支付回调
 * @Date 2019/12/26 10:29
 */
@RestController
public class PayNotifyAliController {

    private static Logger log = LoggerFactory.getLogger(PayNotifyAliController.class);

    @Autowired
    private PayOrderService payOrderService;

    @Autowired
    private PayChannelService payChannelService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PayNotifyService payNotifyService;

    @Autowired
    private HttpServletResponse response;

    @RequestMapping("/pay/alipayNotifyRes.htm")
    public void alipayNotifyRes() {
        String result = doAlipayRes();

        if (result != null) {
            log.info("");
            response.setContentType("text/html");
            PrintWriter pw;
            try {
                pw = response.getWriter();
                pw.print(result);
            } catch (IOException e) {
                log.error("alipay response writer ioException.", e);
            }
        }

    }

    private String doAlipayRes() {
        log.info("======接收支付宝回调=======");
        Map<String, String> params = new HashMap<>(16);
        Map requestParams = request.getParameterMap();
        for (Object o : requestParams.keySet()) {
            String name = String.valueOf(o);

            String[] values = (String[]) requestParams.get(name);
            StringBuilder valueStr = new StringBuilder();
            for (int i = 0, j = values.length - 1; ; i++) {
                valueStr.append(values[i]);
                if (i == j) {
                    break;
                }
                valueStr.append(",");
            }
            params.put(name, valueStr.toString());
        }

        log.info("alipay notify request params={}", params);

        if (params.isEmpty()) {
            log.error("alipay notify request params is empty");
            return PayConstant.RETURN_ALIPAY_VALUE_FAIL;
        }

        Map<String, Object> payContext = new HashMap<>(16);
        payContext.put("parameters", params);

        if (!verifyAlipayParams(payContext, params)) {
            log.error("alipay notify request params verify error");
            return PayConstant.RETURN_ALIPAY_VALUE_FAIL;
        }

        // 订单状态
        String tradeStatus = params.get("trade_status");
        PayOrder payOrder = null;

        if (tradeStatus.equals(PayConstant.AlipayConstant.TRADE_STATUS_SUCCESS) ||
                tradeStatus.equals(PayConstant.AlipayConstant.TRADE_STATUS_FINISHED)) {
            payOrder = (PayOrder) payContext.get("payOrder");

            log.info("alipay notify update payOrder={}", payOrder.getPayOrderId());

            byte payStatus = payOrder.getStatus();

            if (payStatus != PayConstant.PAY_STATUS_SUCCESS && payStatus != PayConstant.PAY_STATUS_COMPLETE) {
                int updatePayOrderRows = payOrderService.updateStatusSuccess(payOrder.getPayOrderId());
                if (updatePayOrderRows != 1) {
                    log.error("alipay notify update payOrder payStatus={}->success,rows={} != 1", payStatus, updatePayOrderRows);
                    return PayConstant.RETURN_ALIPAY_VALUE_FAIL;
                }

                log.info("alipay notify update payOrder payStatus={}->success, rows={}", payStatus, updatePayOrderRows);
                payOrder.setStatus(PayConstant.PAY_STATUS_SUCCESS);

            } else {
                log.info("alipay notify payOrder(db) payStatus already is->{}", payStatus);
            }
        } else {
            log.info("alipay notify trade_status={} is not must process", tradeStatus);
        }

        // 通知商户
        payNotifyService.doNotify(payOrder);

        log.info("==========支付宝回调处理完毕=============");
        return PayConstant.RETURN_ALIPAY_VALUE_SUCCESS;
    }

    /**
     * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
     * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
     * 3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
     * 4、验证app_id是否为该商户本身。
     */
    private boolean verifyAlipayParams(Map<String, Object> payContext, Map<String, String> params) {
        // 商户订单号
        String outTradeNo = params.get("out_trade_no");
        String totalAmount = params.get("total_amount");
        if (StringUtils.isEmpty(outTradeNo)) {
            log.error("alipay notify request params out_trade_no is empty");
            payContext.put("retMsg", "out_trade_no is empty");
            return false;
        }

        if (StringUtils.isEmpty(totalAmount)) {
            log.error("alipay notify request params total_amount is empty");
            payContext.put("retMsg", "total_amount is empty");
        }

        PayOrder payOrder = payOrderService.selectPayOrder(outTradeNo);
        if (payOrder == null) {
            log.error("alipay notify request params out_trade_no={} not match payOrder(db)", outTradeNo);
            payContext.put("retMsg", "can`t found payOrder");
            return false;
        }

        String mchId = payOrder.getMchId();
        String channelId = payOrder.getChannelId();
        PayChannel payChannel = payChannelService.selectPayChannel(channelId, mchId);
        if (payChannel == null) {
            log.error("ailipay notify request params not match payChannel(db). mchId={},channelId={}", mchId, channelId);
            payContext.put("retMsg", "can`t found payChannel");
            return false;
        }

        boolean verifyResult = false;
        try {
            verifyResult = AlipaySignature.rsaCheckV1(params, PayConfigUtil.getAlipayConfig(payChannel.getParam()).getAlipayPublicKey(),
                    PayConstant.RETURN_ALIPAY_SIGN_CHARSET, "RSA2");
        } catch (AlipayApiException e) {
            log.error("AlipaySignature.rsaCheckV1 error", e);
        }

        if (!verifyResult) {
            log.error("alipay notify request rsaCheckV1 failed.");
            payContext.put("retMsg", "rsaCheckV1 failed.");
            return false;
        }

        long alipayAmt = new BigDecimal(totalAmount).movePointRight(2).longValue();
        long dbPayAmt = payOrder.getAmount();
        if (dbPayAmt != alipayAmt) {
            log.error("alipay notify params total_amount not equals payOrderAmount(db)");
            payContext.put("retMsg", "total_amount is not match");
            return false;
        }

        payContext.put("payOrder", payOrder);
        return true;
    }

}
