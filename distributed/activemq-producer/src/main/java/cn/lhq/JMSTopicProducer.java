package cn.lhq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSTopicProducer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.246.132:61616");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();

            //Boolean.TRUE表示事务性会话，Boolean.FALSE表示非事务性会话
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            //创建目的地
            Destination destination = session.createTopic("myTopic");
            //创建发送者
            MessageProducer producer = session.createProducer(destination);
            //设置消息成持久化
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            //创建需要发送的消息
            TextMessage message = session.createTextMessage("I'm Topic");
            //发送消息
            producer.send(message);

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
