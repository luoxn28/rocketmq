package org.apache.rocketmq.test.demo;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;

/**
 * @author xiangnan.
 */
public class ConsumerDemo {

    public static void main(String[] args) throws Exception {
        consumeNormalMessage();
    }

    /**
     * 集群消费普通消息
     */
    private static void consumeNormalMessage() throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(BaseInfo.defaultConsumerGroup);
        consumer.setNamesrvAddr(BaseInfo.namesrvAddr);
        consumer.subscribe(BaseInfo.topic, "*");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            msgs.forEach(msg -> {
                System.out.println(msg);
                System.out.println("    " + new String(msg.getBody()));
            });
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();

        // wait for quit
        System.in.read();
    }
}
