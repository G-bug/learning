/**
 * @author G-bug 2020/1/10
 */
package com.gugpay.consumer.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author G-bug
 * @Description 支付service远程调用类
 * @Date 2020/1/10 14:33
 */

@FeignClient(name = "gugpay-service", fallback = ServiceRemoteImpl.class)
public interface ServiceRemote {

    /**
     * 商户查询
     *
     * @param jsonParam 查询串
     * @return 商户信息
     */
    @GetMapping("/mch/{id}")
    String getMchInfo(@PathVariable("id") String jsonParam);

    /**
     * 支付渠道查询
     *
     * @param jsonParam 查询串
     * @return 支付渠道信息
     */
    @RequestMapping("/pay_channel/select")
    String selectPayChannel(@RequestParam("jsonParam") String jsonParam);

    /////////////// payOrder //////////////////////

    /**
     * 支付订单查询
     *
     * @param jsonParam 查询串
     * @return 前台结果
     */
    @RequestMapping("/pay/query")
    String queryOrder(@RequestParam("jsonParam") String jsonParam);

    /**
     * 支付订单创建
     *
     * @param jsonParam 查询串
     * @return 前台结果
     */
    @RequestMapping("/pay/create")
    String createPayOrder(@RequestParam("jsonParam") String jsonParam);

    ////////////////ali支付/////////////

    /**
     * ali支付渠道支付(手机网站)
     *
     * @param jsonParam 查询串
     * @return 前台结果
     */
    @RequestMapping("/pay/channel/ali_wap")
    String doAliPayWapReq(@RequestParam("jsonParam") String jsonParam);

    /**
     * ali支付渠道支付(pc)
     *
     * @param jsonParam 查询串
     * @return 前台结果
     */
    @RequestMapping("/pay/channel/ali_pc")
    String doAliPayPcReq(@RequestParam("jsonParam") String jsonParam);

    /**
     * ali支付渠道支付(手机app调用)
     *
     * @param jsonParam 查询串
     * @return 前台结果
     */
    @RequestMapping("/pay/channel/ali_app")
    String doAliPayMobileReq(@RequestParam("jsonParam") String jsonParam);

    /**
     * ali支付渠道支付(二维码当面付)
     *
     * @param jsonParam 查询串
     * @return 前台结果
     */
    @RequestMapping("/pay/channel/ali_qr")
    String doAliPayQrReq(@RequestParam("jsonParam") String jsonParam);

    /////////////微信支付//////////////////////

    /**
     * wx支付渠道支付
     *
     * @param jsonParam 查询串
     * @return 前台结果
     */
    @RequestMapping("/pay/channel/wx")
    String doWxpayReq(@RequestParam("jsonParam") String jsonParam);

    ////////////////回调//////////////

    /**
     * ali支付回调接口
     */
    @RequestMapping("/pay/alipayNotifyRes.htm")
    void alipayNotifyRes();

    /**
     * wx支付回调接口
     *
     * @return 回调处理结果
     */
    @RequestMapping("/pay/wxpayNotifyRes.htm")
    String wxpayNotifyRes();
}
