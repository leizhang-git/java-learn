package com.learn.kafka.service;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author zhanglei
 * @date 2021/10/31 12:27 上午
 */
@Component
public class KafkaMessagePool {

    private Queue<com.learn.kafka.service.KafkaMessage> messageQueue;

    @PostConstruct
    public void init() {
        messageQueue = new ConcurrentLinkedQueue<>();
    }

    /**
     * 入队
     * @param message
     */
    public void sendMessages(com.learn.kafka.service.KafkaMessage message) {
        messageQueue.add(message);
    }

    /**
     * 出队
     * @return
     */
    public com.learn.kafka.service.KafkaMessage getMessage() {
        return messageQueue.poll();
    }
}
