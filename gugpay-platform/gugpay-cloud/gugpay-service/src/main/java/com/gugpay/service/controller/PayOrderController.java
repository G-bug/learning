/**
 * @author G-bug 2020/1/2
 */
package com.gugpay.service.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gugpay.dal.dao.model.PayOrder;
import com.gugpay.service.service.PayNotifyService;
import com.gugpay.service.service.PayOrderService;
import constant.PayConstant;
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
 * @Description 支付下单 和 查询
 * @Date 2020/1/8 9:24
 */
@RestController
public class PayOrderController {

    private Logger log = LoggerFactory.getLogger(PayOrderController.class);

    @Autowired
    private PayOrderService payOrderService;

    @Autowired
    private PayNotifyService payNotifyService;

    @RequestMapping("/pay/create")
    public String createPayOrder(@RequestParam String jsonParam) {
        log.info("create pay order request param={}", jsonParam);

        JSONObject retObj = new JSONObject();
        if (StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001");
            retObj.put("msg", "param is empty");
            return retObj.toJSONString();
        }

        try {
            PayOrder payOrder = JSON.parseObject(CustomerBase64.decode(jsonParam), PayOrder.class);
            int result = payOrderService.createPayOrder(payOrder);
            retObj.put("code", "0000");
            retObj.put("result", result);
        } catch (Exception e) {
            retObj.put("code", "0002");
            retObj.put("result", "db-payOrder create exception");
        }

        return retObj.toJSONString();
    }

    @RequestMapping("/pay/query")
    public String queryOrder(@RequestParam String jsonParam) {
        log.info("query payOrder={}", jsonParam);

        JSONObject retObj = new JSONObject();
        if (StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001");
            retObj.put("msg", "param is empty");
            return retObj.toJSONString();
        }

        JSONObject paramObj = JSON.parseObject(CustomerBase64.decode(jsonParam));
        String mchId = paramObj.getString("mchId");
        String payOrderId = paramObj.getString("payOrderId");
        String mchOrderNo = paramObj.getString("mchOrderNo");

        PayOrder payOrder;
        if (StringUtils.isNotBlank(payOrderId)) {
            payOrder = payOrderService.selectByMchIdAndId(mchId, payOrderId);
        } else {
            payOrder = payOrderService.selectByMcIdAndMchOrderNo(mchId, mchOrderNo);
        }

        if (payOrder == null) {
            retObj.put("code", "0002");
            retObj.put("msg", "db-payOrder is null");
            return retObj.toJSONString();
        }

        // 查询之后是否再次通知 商户
        boolean executeNotify = paramObj.getBooleanValue("executeNotify");
        if (executeNotify && payOrder.getStatus() == PayConstant.PAY_STATUS_SUCCESS) {
            payNotifyService.doNotify(payOrder);
        }

        retObj.put("code", "0000");
        retObj.put("result", JSON.toJSON(payOrder));

        log.info("payOrder query retObj={}", retObj.toJSONString());
        return retObj.toJSONString();
    }

}
