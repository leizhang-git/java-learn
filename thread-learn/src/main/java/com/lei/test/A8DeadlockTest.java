package com.lei.test;

/**
 * 线程死锁
 * 指两个或两个以上的线程在执行过程中，因争夺资源而造成的互相等待的现象！
 * 在无外力作用的情况下，这些线程会一直相互等待而无法继续运行下去
 * @Author zhanglei
 * @Date 2022/3/8 20:39
 */
public class A8DeadlockTest {

    /*
        死锁产生的必要条件：
            互斥条件（资源同时只由一个线程占用）
            请求并持有条件（线程己经持有了至少1个资源，但又提出了新的资源请求而新资源己被其 线程占有）
            不可剥夺条件（指线程获取到的资源在自己使用完之前不能被其线程抢占）
            环路等待条件（指在发生死锁时 必然存在一个线程→资源的环形链）
     */

    private static Object oa = new Object();
    private static Object ob = new Object();

    /**
     * 目前只有请求并持有和环路等待条 是可被破坏的
     * @param args
     */
    public static void main(String[] args) {
        Thread ta = new Thread(() -> {
            synchronized (oa) {
                System.out.println(Thread.currentThread() + "oa ....");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread() + "waiting ob...");
                synchronized (ob) {
                    System.out.println(Thread.currentThread() + "ob .....");
                }
            }
        });

        Thread tb = new Thread(() -> {
            synchronized (ob) {
                System.out.println(Thread.currentThread() + "ob ....");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread() + "waiting oa...");
                synchronized (oa) {
                    System.out.println(Thread.currentThread() + "oa .....");
                }
            }
        });

        ta.start();
        tb.start();
    }
}
