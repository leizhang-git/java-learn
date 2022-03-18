package com.lei.test;

/**
 * CopyOnWriteArrayList：
 * 一个线程安全的ArrayList，对其进行的修改操作都是在底层的一个复制的数组（快照）上进行的
 * 使用了写时复制策略
 * @Author: zhang lei
 * @Date: 2022/3/16 16:09
 */
public class E1ListTest {

    /**
     * 每个CopyOnWriteArrayList对象里面有一个array数组对象用来存放具体元素
     * ReentrantLock独占锁对象用来保证同时只有一个线程对array进行修改！
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
