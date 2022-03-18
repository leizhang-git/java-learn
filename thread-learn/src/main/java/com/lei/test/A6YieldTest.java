package com.lei.test;

/**
 * yield方法：线程调用时，实际就是在暗示线程调度器当前线程请求让出自己的CPU使用
 * 但是线程调度器可以无条件忽略这个暗示！
 * @Author zhanglei
 * @Date 2022/3/7 13:56
 */
public class A6YieldTest {

    /*
        当一个线程调用yield方法时，当前线程就会让出CPU使用权，然后处于就绪状态
        sleep与yield方法的区别在于：
            当线程调用sleep方法时调用线程会被阻塞挂起指定的时间，在这期间线程调度器不会去调度该线程
            调用yield方法时，线程只是让出自己剩余的时间片，并没有被阻塞挂起，而是处于就绪状态
     */
}
