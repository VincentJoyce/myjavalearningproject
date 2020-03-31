package cn.lhq;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerDemo extends Thread {

    private final KafkaProducer<Integer, String> producer;
    private final String topic;

    public KafkaProducerDemo(String topic) {
        this.topic = topic;
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "192.168.246.131:9092,192.168.246.132:9092,192.168.246.133:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaProducerDemo");
        //ProducerConfig.ACKS_CONFIG：
        // 0-消息发送给broker以后，不需要确认（性能较高，但是会出现数据丢失）；
        // 1-只需要获得kafka集群中leader节点的确认即可返回（leader/follower）
        // all(-1)-需要ISR中所有Replia进行确认（需要集群中的所有节点确认），最安全
        properties.put(ProducerConfig.ACKS_CONFIG, "-1");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<Integer, String>(properties);
    }

    @Override
    public void run() {
        int num = 0;
        while (num < 50) {
            String message = "message_" + num;
            System.out.println("begin send message:" + message);
            producer.send(new ProducerRecord<Integer, String>(topic, message));
            num ++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        new KafkaProducerDemo("test").start();
    }
}
