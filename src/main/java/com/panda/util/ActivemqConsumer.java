package com.panda.util;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Demo class
 *
 * @author panda
 * @date 2017/10/28
 */
public class ActivemqConsumer {

    public static void main(String[] args) {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();

        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("PANDA_FOO");

            MessageConsumer consumer = session.createConsumer(destination);

            Message message = consumer.receive(1000);
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                System.out.println(textMessage.getText());
            } else {
                System.out.println("error");
            }

            consumer.close();
            connection.close();
            session.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }


    }

}
