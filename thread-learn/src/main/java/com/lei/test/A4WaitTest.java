package com.lei.test;

import java.util.Queue;

/**
 * Java-Object类是所有类的父类，因此Java把所有类都需要的方法放到了Object类里面
 * wait()函数
 * @Author zhanglei
 * @Date 2022/3/4 10:44
 */
public class A4WaitTest {

    Object obj;
    
    Queue queue;
    
    /*
        当一个线程调用一个共享变量的wait()方法时,该调用线程会被阻塞挂起,直到出现以下情况：
            1.其他线程调用了该共享对象的notify()或notifyAll()方法
            2.其他线程调用了该线程的interrupt()方阿飞，该线程抛出 InterruptedException 异常返回

            注意：若调用wait()方法的线程没有事先获取该对象的监视器锁，则调用wait()方法时会抛出 IllegalMonitorStateException异常

            一个线程获取一个共享变量的监视器锁的方法：
                1.执行synchronized同步代码块时，使用该共享变量作为参数
                    synchronized(共享变量) {

                    }
                2.调用该共享变量的方法，并且该方法使用了synchronized修饰
                    synchronized void add(int a, int b) {

                    }

            注意：一个线程可以从挂起状态变为可以运行状态（即被唤醒），即使该线程没有被其他线程调用notify()、notifyAll()方法进行通知
            相当于虚假唤醒！
     */


    /**
     * 首先通过同步块获取obj上面的监视器锁，然后在while循环内调用obj的wait()方法
     */
    public void waitTest() {
        
        //生产线程
        synchronized (queue) {
            //消费队列满，则等待队列空闲
            while (queue.size() == Integer.MAX_VALUE) {
                try {
                    //挂起当前线程，并释放通过同步块获取的queue上的锁，让消费者线程可以获取该锁
                    //然后获取队列里面的元素
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //空闲则生成元素，并通知消费者线程
            queue.add("ele");
            queue.notifyAll();
        }


        //消费者线程
        synchronized (queue) {
            //消费者队列为空
            while (queue.size() == 0) {
                try {
                    //挂起当前线程，并释放通过同步块获取的queue上的锁，让生产者线程可以获取该锁，将生产者元素放入队列
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //消费元素，并通知唤醒生产者线程
            queue.poll();
            queue.notifyAll();
        }
    }

    /*
            notify()函数：
                一个线程调用共享对象的notify()方法后，会唤醒一个在该共享变量上调用wait系列方法后被挂起的线程，具体唤醒哪个是随机的
                此外，被唤醒的线程不能马上从wait方法返回并继续执行，其必须在获取了共享对象的监视器锁后才可以返回，也就是唤醒它的线程
                释放了共享变量上的监视器锁后，被唤醒的线程也不一定会获取到共享对象的监视器锁，因为该线程还需要和其他线程一起竞争该锁
            notifyAll()函数：
                唤醒所有在该共享变量上由于调用wait系列方法而被挂起的线程
     */
}
