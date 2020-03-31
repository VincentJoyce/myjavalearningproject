package cn.lhq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSPersistentTopicReceiver {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.246.132:61616");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            //设置成持久化
            connection.setClientID("lhq-001");
            connection.start();

            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            //创建目的地
            Topic topic = session.createTopic("myTopic");
            //创建消费者
            MessageConsumer consumer = session.createDurableSubscriber(topic,"hq-001");
            //接收消息，consumer.receive()消息队列中如果没有消息则会阻塞
            TextMessage message = (TextMessage) consumer.receive();
            System.out.println(message.getText());

            session.commit();//消息被确认,一个事务的结束，另一个事务的开始
            //session.rollback();//消息会被重新处理
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
