package com.lei.test;

/**
 * Synchronized
 * Java提供的一种原子性内置锁，可以看作同步锁，监视器锁
 * 线程的执行代码在进入synchronized代码块前会自动获取内部锁，其他线程访问该同步代码块时会被阻塞挂起
 * 注意：由于Java中的线程是与操作系统的原生线程一一对应的，所以当阻塞一个线程时，需要从用户态切换到内核态执行阻塞操作！
 * synchronized的使用就会导致上下文切换
 */
public class B3SynchronizedTest {

    /*
        获取内部锁后有以下三种情况会释放锁：
            1.正常退出同步代码块
            2.抛出异常
            3.在同步代码块中调用了该内置锁资源的wait系列方法时释放该内置锁
        内置锁是排他锁：即其他线程必须等待该线程释放锁后才能获取该锁
     */


    /*
        synchronized内存语义：
            进入synchronized内存语义：
                把在synchronized块内使用到的变量从线程的工作内存中清除，这样一来，synchronized块内使用
                到该变量是就不会从线程的工作内存中获取，而是直接从主内存中获取
            退出synchronized内存语义：
                把在synchronized块内对共享变量的修改刷新到主内存
     */
}
