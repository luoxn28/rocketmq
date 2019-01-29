package org.apache.rocketmq.test.demo;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.Date;

/**
 * @author xiangnan.
 */
public class ProviderDemo {

    public static void main(String[] args) throws Exception {
        orderMessage();
    }

    /**
     * 普通消息发送
     */
    private static void orderMessage() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(BaseInfo.defaultProviderGroup);
        producer.setNamesrvAddr(BaseInfo.namesrvAddr);
        producer.start();

        do {
            Message message = new Message(BaseInfo.topic, ("message " + new Date()).getBytes());
            SendResult result = producer.send(message);
            System.out.println(result);
        } while (System.in.read() != 'q');

        producer.shutdown();
    }
}
