package util;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Demo class
 *
 * @author panda
 * @date 2017/10/28
 */
public class ActivemqProducer {

    public static void main(String[] args) {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();

        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false,  Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("PANDA_FOO");

            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            String text = "Hello world!";
            TextMessage message = session.createTextMessage(text);

            producer.send(message);

            connection.close();
            session.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
