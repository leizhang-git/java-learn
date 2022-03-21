package com.lei.test;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * ArrayBlockingQueue(有界数组实现的阻塞队列)
 * ArrayBlockingQueue内部有一个数组items，用来存放队列元素，
 * putindex - 入队元素下表      takeIndex - 出队下表  count - 元素个数
 * 无volatile修饰，因为访问这些变量都是在锁块内！另外有独占锁lock保证原子性！
 * @Author: zhang lei
 * @Date: 2022/3/17 15:51
 */
public class G3ArrayBlockingQueueTest {

    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10, true);
        //队列尾部插入元素，非阻塞
        queue.offer("1");
        //同上，阻塞
        try {
            queue.put("2");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //头部获取且移除元素，非阻塞
        queue.poll();
        //同上，阻塞
        try {
            queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //获取头部，不移除
        queue.peek();

    }
}
