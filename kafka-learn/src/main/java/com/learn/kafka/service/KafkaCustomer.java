package com.learn.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhanglei
 * @date 2021/10/31 12:42 上午
 */
@Component
public class KafkaCustomer {

    @Resource
    private KafkaMessagePool kafkaMessagePool;

    @KafkaListener(topics = {"kafka.consumer.topic"})
    public void listen(ConsumerRecord<String,String> consumer) {
        String topic = consumer.topic();
        String key = consumer.key();
        String value = consumer.value();

        KafkaMessage kafkaMessage = new KafkaMessage();
        kafkaMessage.setTopic(topic);
        kafkaMessage.setKey(key);
        kafkaMessage.setData(value);

        kafkaMessagePool.sendMessages(kafkaMessage);
    }
}
