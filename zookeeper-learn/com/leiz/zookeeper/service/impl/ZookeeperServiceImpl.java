package com.leiz.zookeeper.service.impl;

import com.leiz.zookeeper.config.ZookeeperConfig;
import com.leiz.zookeeper.service.ZookeeperService;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanglei
 * @date 2021/10/30 11:43 下午
 */
@Service
public class ZookeeperServiceImpl implements ZookeeperService {

    private static final Logger log = LoggerFactory.getLogger(ZookeeperServiceImpl.class);

    @Resource
    private ZookeeperConfig zookeeperConfig;

    /**
     * 判断节点是否存在
     * @param path
     * @return
     */
    @Override
    public boolean isExistNode(String path) {
        CuratorFramework client = zookeeperConfig.getClient();
        client.sync();
        try {
            //checkExists：启动一个现成的构造器，返回一个stat对象，判断是否存在
            //forPath：使用给定的路径实行当前的操作
            Stat stat = client.checkExists().forPath(path);
            return stat != null;
        } catch (Exception e) {
            log.error("isExistNode error ...", e);
        }
        return false;
    }

    /**
     * 创建节点
     * @param mode CreateMode 规定了 zNode 是如何在zookeeper上创建的
     * @param path
     */
    @Override
    public void createNode(CreateMode mode, String path) {
        CuratorFramework client = zookeeperConfig.getClient();
        try {
            //递归创建所需要的父节点 creatingParentsIfNeeded
            client.create().creatingParentsIfNeeded().withMode(mode).forPath(path);
        } catch (Exception e) {
            log.error("isExistNode error ...", e);
            e.printStackTrace();
        }
    }

    /**
     * 设置节点数据
     * @param path
     * @param nodeData
     */
    @Override
    public void setNodeData(String path, String nodeData) {
        CuratorFramework client = zookeeperConfig.getClient();
        try {
            client.setData().forPath(path, nodeData.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("isExistNode error ...", e);
            e.printStackTrace();
        }
    }

    /**
     * 创建节点
     * @param mode
     * @param path
     * @param nodeData
     */
    @Override
    public void createNodeAndData(CreateMode mode, String path, String nodeData) {
        CuratorFramework client = zookeeperConfig.getClient();
        try {
            client.create().creatingParentsIfNeeded().withMode(mode).forPath(path,nodeData.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("createNode error...");
        }
    }

    /**
     * 获取节点数据
     * @param path
     * @return
     */
    @Override
    public String getNodeData(String path) {
        CuratorFramework client = zookeeperConfig.getClient();
        try {
            byte[] dataByte = client.getData().forPath("UTF-8");
            String data = new String(dataByte, StandardCharsets.UTF_8);
            if(StringUtils.isNotBlank(data)) {
                return data;
            }
        } catch (Exception e) {
            log.error("getNodeData error...", e);
        }
        return null;
    }

    /**
     * 获取节点下的数据
     * @param path
     * @return
     */
    @Override
    public List<String> getNodeChild(String path) {
        CuratorFramework client = zookeeperConfig.getClient();
        List<String> nodeChildDataList = new ArrayList<>();
        try {
            // 节点下数据集
            nodeChildDataList = client.getChildren().forPath(path);
        } catch (Exception e) {
            log.error("getNodeChild error...", e);
        }
        return nodeChildDataList;
    }

    /**
     * 是否递归删除节点
     * @param path
     * @param recursive
     */
    @Override
    public void deleteNode(String path, Boolean recursive) {
        CuratorFramework client = zookeeperConfig.getClient();
        try {
            if(recursive) {
                //递归删除节点
                client.delete().guaranteed().deletingChildrenIfNeeded().forPath(path);
            }else {
                //删除单个节点
                client.delete().guaranteed().forPath(path);
            }
        }catch (Exception e) {
            log.error("deleteNode error...", e);
        }
    }

    /**
     * 获取读写锁
     * @param path
     * @return InterProcessReadWriteLock 分布式读写锁
     */
    @Override
    public InterProcessReadWriteLock getReadWriteLock(String path) {
        CuratorFramework client = zookeeperConfig.getClient();
        //写锁互斥、读写互斥
        return new InterProcessReadWriteLock(client, path);
    }
}
