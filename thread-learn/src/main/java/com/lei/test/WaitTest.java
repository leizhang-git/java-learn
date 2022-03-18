package com.lei.test;

/**
 * @Author zhanglei
 * @Date 2022/2/14 15:15
 */
public class WaitTest {
    //wait set是一个虚拟的概念，每个Java类的实例都有一个wait set，当对象执行wait方法时，当前线程就会暂停，并进入该对象的wait set

    /*
        当发生以下事件时，线程才会退出 wait set：
            有其它线程以notify方法唤醒该线程
            有其它线程以notifyAll方法唤醒该线程
            有其它线程以interrupt方法唤醒该线程
            wait方法已到期
     */

    //当前线程若要执行obj.wait()，则必须先获取该对象锁。当线程进入wait set后，就已经释放了该对象锁

    //notify方法：
    //notify方法相当于从wait set中从挑出一个线程并唤醒
    //调用notify方法后，并不会立即释放锁，会有一段时间差

    //notifyAll方法：
    //notifyAll方法相当于将wait set中的所有线程都唤醒

    /*
        wait、notify、notifyAll这三个方法都是java.lang.Object类的方法（注意，不是Thread类的方法）。
        若线程没有拿到当前对象锁就直接调用对象的这些方法，都会抛出java.lang.IllegalMonitorStateException异常。
     */
}
