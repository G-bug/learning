package com.gugpay.service.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gugpay.service.service.PayOrderService;
import com.rabbitmq.client.Channel;
import constant.PayConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import util.HttpClient;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * @author G-bug
 * @Description 支付订单通知消息类
 * @Date 2019/12/23 16:51
 */
@Component
public class PayNotifyMq {

    @Autowired
    private PayOrderService payOrderService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public void send(String msg) {
        log.info("send PAY_ORDER_KEY mq, {}", msg);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("DIRECT_EXCHANGE", "PAY_ORDER_KEY", msg, correlationData);
    }

    /**
     * TODO:后期加入delay消息队列
     */
    public void send(String msg, long delay) {
        send(msg);
    }

    /**
     * 支付订单(回调)消息接收
     */
    @RabbitListener(queues = {"PAY_ORDER_QUEUE"})
    public void receive(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        String body = Arrays.toString(message.getBody());
        notifyProcess(body);
    }

    /**
     * 消息处理
     */
    private void notifyProcess(String msg) {
        JSONObject msgObj = JSON.parseObject(msg);
        String urlStr = msgObj.getString("url");
        String orderId = msgObj.getString("orderId");
        int count = msgObj.getInteger("count");

        if (StringUtils.isEmpty(urlStr)) {
            log.warn("notify url is empty.");
            return;
        }

        try {
            StringBuilder sb = new StringBuilder();
            URL targetUrl = new URL(urlStr);

            log.info("MQ send 业务系统 start, orderId={}, count={}, time={}", orderId, count, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            if ("https".equals(targetUrl.getProtocol())) {

                sb.append(HttpClient.callHttpsPost(urlStr));

            } else if ("http".equals(targetUrl.getProtocol())) {

                sb.append(HttpClient.callHttpPost(urlStr, 10 * 1000));

            } else {
                log.error("not do protocol. protocol=%s", targetUrl.getProtocol());
                return;
            }

            log.info("MQ send 业务系统 over, orderId={}, count={}, time={}", orderId, count, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            if (PayConstant.PAY_NOTIFY_RETURN_STR.equalsIgnoreCase(sb.toString().trim())) {

                try {

                    int result1 = payOrderService.updateStatusComplete(orderId);
                    log.info("update payOrderId={}, status=complete, {}", orderId, result1 == 1 ? "成功" : "失败");

                    // 成功则把count 归 1
                    int result2 = payOrderService.updateNotify(orderId, (byte) 1);
                    log.info("update payOrderId={}, notifyCount=1, {}", orderId, result2 == 1 ? "成功" : "失败");

                } catch (Exception e) {
                    log.error("notify success, update payOrder exception", e);
                }

            } else {

                log.warn("notify failed. url:{}, response body:{}", urlStr, sb.toString());

                // 通知返回错误，延时再通知
                int cnt = count + 1;
                log.info("notify count={}", cnt);

                try {

                    int result = payOrderService.updateNotify(orderId, (byte) cnt);
                    log.info("update payOrderId={}, notifyCount={}, {}", orderId, cnt, result == 1 ? "成功" : "失败");

                } catch (Exception e) {
                    log.error("notify failed, update payOrder exception", e);
                }

                if (cnt > PayConstant.PAY_NOTIFY_MAX_COUNT) {
                    log.info("notify count > 5 stop. url={}", urlStr);
                    return;
                }

                msgObj.put("count", cnt);

                // 再次通知
                this.send(msgObj.toJSONString(), cnt * 60 * 1000);
            }

        } catch (Exception e) {

            log.info("MQ send 业务系统 exception, url={}, orderId={}, count={}, time={}", urlStr, orderId, count, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        }

    }
}
