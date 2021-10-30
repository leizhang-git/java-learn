package com.learn.kafka.config;


import com.learn.kafka.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author zhanglei
 * @date 2021/10/31 12:23 上午
 */
@Component
public class InitApplicationRunner implements ApplicationRunner {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public void run(ApplicationArguments args) {
        kafkaProducer.send();
    }
}
