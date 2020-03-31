package cn.lhq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSQueueReceiver {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.246.132:61616");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();

            //Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            Session session = connection.createSession(Boolean.TRUE, Session.CLIENT_ACKNOWLEDGE);
            //创建目的地
            Destination destination = session.createQueue("myQueue");
            //创建消费者
            MessageConsumer consumer = session.createConsumer(destination);
            //接收消息，consumer.receive()消息队列中如果没有消息则会阻塞
            TextMessage message = (TextMessage) consumer.receive();
            System.out.println(message.getText());

            session.commit();
            session.close();
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
