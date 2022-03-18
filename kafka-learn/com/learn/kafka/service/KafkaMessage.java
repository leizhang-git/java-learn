package com.learn.kafka.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhanglei
 * @date 2021/10/31 12:32 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaMessage {

    private String topic;

    private String key;

    private String data;
}
