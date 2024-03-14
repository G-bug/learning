/**
 * @author G-bug 2019/12/26
 */
package com.gugpay.service.controller;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceAbstractImpl;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.gugpay.dal.dao.model.PayChannel;
import com.gugpay.dal.dao.model.PayOrder;
import com.gugpay.service.channel.PayConfigUtil;
import com.gugpay.service.service.PayChannelService;
import com.gugpay.service.service.PayNotifyService;
import com.gugpay.service.service.PayOrderService;
import constant.PayConstant;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author G-bug
 * @Description 微信支付回调
 * @Date 2019/12/26 10:31
 */
@RestController
public class PayNotifyWxController {

    private static Logger log = LoggerFactory.getLogger(PayNotifyWxController.class);

    @Autowired
    private PayOrderService payOrderService;

    @Autowired
    private PayChannelService payChannelService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PayNotifyService payNotifyService;

    @RequestMapping("/pay/wxpayNotifyRes.htm")
    public String wxpayNotifyRes() {
        log.info("=======接收wx回调======");
        try {
            String xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
            WxPayService wxpayService = new WxPayServiceImpl();

            WxPayOrderNotifyResult result = WxPayOrderNotifyResult.fromXML(xmlResult);
            // 校验签名
            result.checkResult((WxPayServiceAbstractImpl) wxpayService);

            Map<String, Object> payContext = new HashMap<>(16);
            payContext.put("parameters", result);

            // 查找 payOrder,payConfig
            if (!verifyWxpayParams(payContext)) {
                return WxPayNotifyResponse.fail((String) payContext.get("retMsg"));
            }

            PayOrder payOrder = (PayOrder) payContext.get("payOrder");
            wxpayService.setConfig((WxPayConfig) payContext.get("wxpayConfig"));

            byte payStatus = payOrder.getStatus();
            if (payStatus != PayConstant.PAY_STATUS_SUCCESS && payStatus != PayConstant.PAY_STATUS_COMPLETE) {

                int updatePayOrderRows = payOrderService.updateStatusSuccess(payOrder.getPayOrderId());

                if (updatePayOrderRows != 1) {
                    log.error("WxPayNotify update payOrder failed:(rows != 1). payOrderId={},rows={},status={}->success",
                            payOrder.getPayOrderId(), updatePayOrderRows, payOrder.getStatus());
                    return WxPayNotifyResponse.fail("处理订单失败");
                }

                log.info("WxPayNotify update payOrder success. payOrderId={},status={}->success", payOrder.getPayOrderId(), payOrder.getStatus());
                payOrder.setStatus(PayConstant.PAY_STATUS_SUCCESS);
            }

            // 通知商户
            payNotifyService.doNotify(payOrder);

            return WxPayNotifyResponse.success("处理成功");

        } catch (WxPayException e) {
            log.error("WxPayNotify WxPayException, errCode={},errCodeDes={}", e.getErrCode(), e.getErrCodeDes());
            return WxPayNotifyResponse.fail(e.getMessage());
        } catch (Exception e) {
            log.error("WxPayNotify exception", e);
            return WxPayNotifyResponse.fail(e.getMessage());
        }
    }

    /**
     * 对微信传递的数据进行校验
     */
    private boolean verifyWxpayParams(Map<String, Object> payContext) {

        WxPayOrderNotifyResult params = (WxPayOrderNotifyResult) payContext.get("parameters");

        // TODO:微信文档中对这两个值有没有很模糊
        if (!PayConstant.RETURN_VALUE_SUCCESS.equalsIgnoreCase(params.getResultCode())
                && !PayConstant.RETURN_VALUE_SUCCESS.equalsIgnoreCase(params.getReturnCode())) {
            log.error("Pay is failed, returnCode={},resultCode={},errCode={},errCodeDes={}",
                    params.getReturnCode(), params.getResultCode(), params.getErrCode(), params.getErrCodeDes());
            payContext.put("retMsg", "notify data failed");
            return false;
        }

        // 订单金额
        Integer totalFee = params.getTotalFee();
        String payOrderId = params.getOutTradeNo();

        PayOrder payOrder = payOrderService.selectPayOrder(payOrderId);
        if (payOrder == null) {
            log.error("not found payOrder. payOrderId={}", payOrderId);
            payContext.put("retMsg", "Can`t found payOrder");
            return false;
        }

        String mchId = payOrder.getMchId();
        String channelId = payOrder.getChannelId();

        PayChannel payChannel = payChannelService.selectPayChannel(channelId, mchId);
        if (payChannel == null) {
            log.error("not found payChannel. mchId={},channelId={}", mchId, channelId);
            payContext.put("retMsg", "");
            return false;
        }

        payContext.put("wxpayConfig", PayConfigUtil.getWxPayConfig(payChannel.getParam()));

        long wxpayAmt = new BigDecimal(totalFee).longValue();
        long dbPayAmt = payOrder.getAmount();

        if (dbPayAmt != wxpayAmt) {
            log.error("db payOrder amount not equals for wx total_fee. payOrderId={},total_fee={}", payOrderId, totalFee);
            payContext.put("retMsg", "total_fee is not the same");
            return false;
        }

        payContext.put("payOrder", payOrder);
        return true;
    }

}
