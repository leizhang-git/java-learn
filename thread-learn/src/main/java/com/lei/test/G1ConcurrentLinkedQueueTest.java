package com.lei.test;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * JDK中提供了一系列场景的并发安全队列，按照实现方式的不同可以分为阻塞队列（锁实现）、非阻塞队列（CAS实现）
 * ConcurrentLinkedQueue原理（线程安全的无界非阻塞队列）
 * ConcurrentLinkedQueue内部的队列使用单向链表实现，
 * 其中有两个volatile类型的 Node节点分别用来存放队列的首、尾节点！
 * 默认头、尾节点都是指向item为null的哨兵节点，新元素会被插入队列尾部，出队时从队列头部获取一个元素
 * @Author: zhang lei
 * @Date: 2022/3/17 14:19
 */
public class G1ConcurrentLinkedQueueTest {
    /**
     * ConcurrentLinkedQueue的底层使用单向链表数据结构保存队列，每个元素被包装成一个Node节点
     * 队列是靠头、尾节点来维护的，创建队列时头、尾节点指向一个item为null的哨兵节点，第一次执行
     * peek或者first操作时会把head指向一个真正的队列元素！
     * @param args
     */
    public static void main(String[] args) {
        ConcurrentLinkedQueue<Object> queue = new ConcurrentLinkedQueue<>();
        //offer操作是在队列末尾添加一个元素，传null会抛异常,且使用CAS无阻塞算法，因为不会阻塞挂起调用线程
        queue.offer("1");
        //链表末尾添加一个元素，本质还是offer
        queue.add("2");
        //队列头部获取并移除一个元素,队列为空返回null
        queue.poll();
        //队列头部获取一个元素（只获取，不移除），为空返回null
        queue.peek();
        //元素个数(并发环境下慎用！！！没有加锁)
        queue.size();
        //若队列里面存在该元素则删除元素,如果存在多个则删除第一个，并返回 true 否则返回 false
        queue.remove("1");
        //判断队列里面是否含有指定对象（可能不太精确）
        queue.contains("1");
    }
}
