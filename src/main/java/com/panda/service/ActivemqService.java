package com.panda.service;

import com.panda.config.Constants;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * MQ 服务
 *
 * @author panda
 * @date 2018/2/24
 */
public class ActivemqService {

    private static final Logger logger = LoggerFactory.getLogger(ActivemqService.class);

    private Connection connection;

    private Session session;

    private MessageConsumer consumer;

    public ActivemqService() {
        initConnection();
    }

    private void initConnection() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(Constants.AMQ_URL);
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (Exception e) {
            logger.error("创建 Session 异常:", e);
        }
    }

    public void close() {
        try {
            if (session != null) {
                session.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            logger.error("关闭连接异常:", e);
        }
    }

    public void createConsumer(String destinationName) {
        try {
            Destination destination = session.createQueue(destinationName);
            consumer = session.createConsumer(destination);
        } catch (Exception e) {
            logger.error("创建消费者异常:", e);
        }
    }

    public String getMessage() {
        String messageText = null;
        try {
            Message message = consumer.receive();
            if (message instanceof TextMessage) {
                messageText = ((TextMessage) message).getText();
            }
        } catch (Exception e) {
            logger.error("接收 MQ 消息异常:", e);
        }
        return messageText;
    }

    public void sendMessage(String destinationName, String message) {
        try {
            Destination destination = session.createQueue(destinationName);
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            TextMessage textMessage = session.createTextMessage(message);
            producer.send(textMessage);
        } catch (Exception e) {
            logger.error("发送消息到 MQ 异常:", e);
        }
    }

}
