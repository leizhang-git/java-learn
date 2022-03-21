package com.lei.test;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * LinkedBlockingQueue 原理（独占锁实现的阻塞队列）
 * LinkedBlockingQueue也是使用单向链表实现的，其也有两个Node，分别用来存放首、尾节点
 * 并且还有一个初始值为0的原子变量count，用来记录队列元素的个数
 * 还有两个ReentrantLock的实例，分别用来控制元素入队和出队的原子性
 * takeLock控制同时只能有一个线程可以从队列头获取元素，其他线程必须等待
 * putLock控制同时只能有一个线程可以获取锁，在队列尾部添加元素，其他线程必须等待
 * @Author: zhang lei
 * @Date: 2022/3/17 14:20
 */
public class G2LinkedBlockingQueueTest {

    public static void main(String[] args) {
        LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>();
        //向队列尾部插入一个元素，若队列有空闲则插入成功，否则丢弃当前元素返回false，e为null会抛出异常(非阻塞)
        queue.offer("1");
        //向队列尾部插入一个元素，若空闲则擦汗如，若队列满则阻塞当前线程，知道队列有空闲插入成功后返回
        try {
            queue.put("2");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //从队列头部获取并移除一个元素（不阻塞）
        queue.poll();
        //获取队列头部元素，但是不移除！（不阻塞）
        queue.peek();
        //获取当前队列头部元素并从队列里面移除它（阻塞）
        try {
            queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //删除指定元素
        queue.remove("1");
        //获取个数
        queue.size();
    }
}
