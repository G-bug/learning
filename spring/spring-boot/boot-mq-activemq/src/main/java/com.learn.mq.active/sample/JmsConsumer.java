/**
 * @author G-bug 2019/10/16
 */
package com.learn.mq.active.sample;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息消费者/接受者
 */
public class JmsConsumer {

    // 默认连接用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    // 默认连接密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    // 默认连接地址
    private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void run() {
        // 连接工厂
        ConnectionFactory connectionFactory;
        // 连接
        Connection connection = null;
        // 会话 接受发送消息的线程
        Session session;
        // 消息 目的地 即 消息所在队列
        Destination destination;
        // 消息 消息者对象
        MessageConsumer messageConsumer;
        // 实例化工厂
        connectionFactory = new ActiveMQConnectionFactory(JmsConsumer.USERNAME, JmsConsumer.PASSWORD, JmsConsumer.BROKER_URL);

        try {

            // 工厂生产连接
            connection = connectionFactory.createConnection();
            connection.start();
            // 连接创建会话
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 设置目的地
            destination = session.createQueue("SAMPLE_MQ_QUEUE1");
            // 创建消费者
            messageConsumer = session.createConsumer(destination);

            while (true) {
                TextMessage textMessage = (TextMessage) messageConsumer.receive(100000);
                if (textMessage != null) {
                    System.out.println("QUEUE\t收到的消息:" + textMessage.getText());
                } else {
                    break;
                }
            }

            destination = session.createTopic("SAMPLE_MQ_TOPIC1");
            messageConsumer = session.createConsumer(destination);
            while (true) {
                TextMessage textMessage = (TextMessage) messageConsumer.receive(100000);
                if (textMessage != null) {
                    System.out.println("TOPIC\t收到的消息:" + textMessage.getText());
                } else {
                    break;
                }
            }

        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
