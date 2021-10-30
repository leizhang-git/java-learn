package com.leiz.zookeeper.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author zhanglei
 * @date 2021/10/30 10:55 下午
 */
@Configuration
public class ZookeeperConfig {

    private static final Logger log = LoggerFactory.getLogger(ZookeeperConfig.class);

    @Autowired
    private ZookeeperParam zookeeperParam;

    private static CuratorFramework cline = null;

    @PostConstruct
    public void init() {

        //重试策略，初始时间1s，重试10次
        RetryPolicy policy = new ExponentialBackoffRetry(
                zookeeperParam.getBaseSleepTimeMs(),
                zookeeperParam.getMaxRetries());

        //通过工厂创建 Curator
        cline = CuratorFrameworkFactory.builder()
                .connectString(zookeeperParam.getServer())
                .authorization("digest", zookeeperParam.getDigest().getBytes())
                .connectionTimeoutMs(zookeeperParam.getConnectionTimeoutMs())
                .sessionTimeoutMs(zookeeperParam.getSessionTimeoutMs())
                .retryPolicy(policy).build();

        //开启连接
        cline.start();
        log.info("zookeeper 初始化完成...");
    }

    public CuratorFramework getClient() {
        return cline;
    }

    public void closeClient() {
        if(cline != null) {
            cline.close();
        }
    }
}
