package com.leiz.zookeeper.service;

import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.zookeeper.CreateMode;

import java.util.List;

/**
 * @author zhanglei
 * @date 2021/10/30 11:18 下午
 */
public interface ZookeeperService {

    /**
     * 判断节点是否存在
     * @param path
     * @return
     */
    boolean isExistNode(String path);

    /**
     * 创建节点
     * @param mode
     * @param path
     */
    void createNode(CreateMode mode, String path);

    /**
     * 设置节点数据
     * @param path
     * @param nodeData
     */
    void setNodeData(String path, String nodeData);

    /**
     * 创建节点
     * @param mode
     * @param path
     * @param nodeData
     */
    void createNodeAndData(CreateMode mode, String path, String nodeData);

    /**
     * 获取节点数据
     * @param path
     * @return
     */
    String getNodeData(String path);

    /**
     * 获取节点下数据
     * @param path
     * @return
     */
    List<String> getNodeChild(String path);

    /**
     * 是否递归删除节点
     * @param path
     * @param recursive
     */
    void deleteNode(String path, Boolean recursive);

    /**
     * 获取读写锁
     * @param path
     * @return
     */
    InterProcessReadWriteLock getReadWriteLock(String path);
}
