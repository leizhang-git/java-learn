package com.lei.test;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * PriorityBlockingQueue(带优先级的无界阻塞队列)
 * 每次出队都返回优先级最高或者最低的元素，平衡二叉树堆实现的
 * 直接遍历队列元素不保证有序，默认使用对象的compareTo方法提供比较规则
 * @Author: zhang lei
 * @Date: 2022/3/17 15:51
 */
public class G4PriorityBlockingQueueTest {
    static class Task implements Comparable<Task> {

        private int priority = 0;

        private String taskName;

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public String toString() {
            return "Task{" +
                    "priority=" + priority +
                    ", taskName='" + taskName + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Task o) {
            if (this.priority >= o.priority) {
                return 1;
            }else {
                return -1;
            }
        }
    }
    /**
     * PriorityBlockingQueue内部有一个数组queue，用来存放队列元素
     * size用来存放队列元素个数，allocationSpinLock是个自旋锁，其使用CAS操作来保证同时
     * 只有一个线程可以扩容队列，状态为0或者1，0标识没有进行扩容，1标识正在扩容
     * @param args
     */
    public static void main(String[] args) {
        PriorityBlockingQueue<Task> priorityBlockingQueue = new PriorityBlockingQueue<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            task.setPriority(random.nextInt(10));
            task.setTaskName("taskName" + i);
            priorityBlockingQueue.offer(task);
        }
        while (!priorityBlockingQueue.isEmpty()) {
            Task task = priorityBlockingQueue.poll();
            if(null != task) {
                task.toString();
            }
        }
    }
}
