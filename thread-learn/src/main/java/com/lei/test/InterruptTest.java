package com.lei.test;

/**
 * 该方法直接对线程调用。当被interrupt的线程正在sleep或wait时，会抛出InterruptedException异常
 * @Author zhanglei
 * @Date 2022/2/14 15:10
 */
public class InterruptTest {

    /*
        interrupt方法只是改变目标线程的中断状态（interrupt status），
        而那些会抛出InterruptedException异常的方法，如wait、sleep、join等，
        都是在方法内部不断地检查中断状态的值
     */

    //interrupt方法
    //Thread实例方法：必须由其它线程获取被调用线程的实例后，进行调用。实际上，只是改变了被调用线程的内部中断状态；

    //Thread.interrupted方法
    //Thread类方法：必须在当前执行线程内调用，该方法返回当前线程的内部中断状态，然后清除中断状态（置为false） ；

    //isInterrupted方法
    //Thread实例方法：用来检查指定线程的中断状态。当线程为中断状态时，会返回true；否则返回false。
}
