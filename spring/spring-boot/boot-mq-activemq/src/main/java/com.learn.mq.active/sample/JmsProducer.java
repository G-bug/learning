/**
 * @author G-bug 2019/10/16
 */
package com.learn.mq.active.sample;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Date;

/**
 * @auth Administrator
 */

public class JmsProducer {

    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;

    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

    private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    private static final int SEND_NUM = 10;

    public static void run() {

        ConnectionFactory connectionFactory;

        Connection connection = null;

        Session session;

        Destination destination;

        MessageProducer messageProducer;

        connectionFactory = new ActiveMQConnectionFactory(JmsProducer.USERNAME, JmsProducer.PASSWORD, JmsProducer.BROKER_URL);

        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

            destination = session.createQueue("SAMPLE_MQ_QUEUE1");
            messageProducer = session.createProducer(destination);
            sendMessage(session, messageProducer, "QUEUE");

            destination = session.createTopic("SAMPLE_MQ_TOPIC1");
            messageProducer = session.createProducer(destination);
            sendMessage(session, messageProducer, "TOPIC");
            session.commit();
            Thread.sleep(1000);

        } catch (Exception e) {
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

    private static void sendMessage(Session session, MessageProducer messageProducer, String type) throws Exception {
        for (int i = 0; i < JmsProducer.SEND_NUM; i++) {
            Date date = new Date();
            TextMessage message = session.createTextMessage("ActiveMQ发送消息时间:" + date);
            System.out.println("\t" + type + "\t发送消息时间:" + date);
            messageProducer.send(message);
        }
    }
}
