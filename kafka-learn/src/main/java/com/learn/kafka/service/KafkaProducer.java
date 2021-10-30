package com.learn.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zhanglei
 * @date 2021/10/31 12:26 上午
 */
@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    private KafkaMessagePool kafkaMessagePool;

    @Value("${kafka.producer.topic}")
    private String producerTopic;

    private boolean isExit;

    @PostConstruct
    public void init() {
        isExit = false;
    }

    public void send() {
        while (!isExit) {
            KafkaMessage message = kafkaMessagePool.getMessage();
            if(message != null) {
                kafkaTemplate.send(producerTopic, message.getKey(), message.getData());
            }
        }
    }

    public void close() {
        this.isExit = true;
    }
}
