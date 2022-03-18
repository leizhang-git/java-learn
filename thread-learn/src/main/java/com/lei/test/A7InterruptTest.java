package com.lei.test;

/**
 * 线程中断：
 * Java中的线程中断是一种线程间的协作模式，通过设置线程的中断标志并不能直接终止该线程的执行
 *
 * @Author zhanglei
 * @Date 2022/3/8 20:26
 */
public class A7InterruptTest {

    /*
        void interrupt()方法：
            中断线程，会抛异常
        boolean isInterrupted()方法：
            检测当前线程是否被中断，若返回true，否则返回false
     */

    public void test() {

        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("---");
            }
        });
    }


    /*
        上下文切换：
            多线程中，线程个数一般都大于CPU个数，而每个CPU同一时刻只能被一个线程使用，
            为了让用户感觉多个线程是同时执行的，CPU资源的分配采用了时间片轮转的策略！
            即：给每个线程分配一个时间片，线程在时间片内占用CPU执行任务，当前线程使用完时间片之后，
            就会处于就绪状态并让出CPU让其他线程占用，这就是上下文切换！
     */
}
