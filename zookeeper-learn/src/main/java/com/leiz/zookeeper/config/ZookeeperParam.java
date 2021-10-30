package com.leiz.zookeeper.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhanglei
 * @date 2021/10/30 11:00 下午
 */
@Component
@Data
public class ZookeeperParam {

    /**
     * 是否启用(true/false)
     */
    @Value("${zookeeper.enabled}")
    private boolean enabled;

    /**
     * 服务器地址
     */
    @Value("${zookeeper.server}")
    private String server;

    /**
     * 命名空间
     */
    @Value("${zookeeper.nameSpace}")
    private String nameSpace;

    /**
     * 控制权限，加密
     */
    @Value("${zookeeper.digest}")
    private String digest;

    /**
     * 会话超时时间
     */
    @Value("${zookeeper.sessionTimeoutMs}")
    private Integer sessionTimeoutMs;

    /**
     * 连接超时时间
     */
    @Value("${zookeeper.connectionTimeoutMs}")
    private Integer connectionTimeoutMs;

    /**
     * 最大重试次数
     */
    @Value("${zookeeper.maxRetries}")
    private Integer maxRetries;

    /**
     * 初始休眠时间
     */
    @Value("${zookeeper.baseSleepTimeMs}")
    private Integer baseSleepTimeMs;
}
