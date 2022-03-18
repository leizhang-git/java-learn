package com.lei.test;

/**
 * @Author zhanglei
 * @Date 2022/2/14 15:05
 */
public class synchronizedTest {

    //每个锁在同一时刻，只能由一个线程持有
    //synchronized实例方法采用this锁（即当前对象）去做线程的共享互斥。
    public synchronized void deposit(int m) {
        System.out.print("This is synchronized method.");
    }

    //synchronized类方法采用类对象锁（即当前类的类对象）去做线程的共享互斥。
    public static synchronized void deposit1(int m) {
        System.out.print("This is synchronized static method.");
    }

    public void deposit2(int m) {
        synchronized (this) {
            System.out.print("This is synchronized statement with this lock.");
        }
        synchronized (synchronizedTest.class) {
            System.out.print("This is synchronized statement with class lock.");
        }
    }

    /*
        synchronized本质
            线程进入synchronized（两类操作）
                1、强制写入主存储器
                    当线程欲进入synchronized时，如果该线程的工作存储器（working memory）上有未映像到主存储器的拷贝，
                    则这些内容会强制写入主存储器（store->write），则这些计算结果就会对其它线程可见（visible）
                2、工作存储器的释放
                    当线程欲进入synchronized时，工作存储器上的工作拷贝会被全部丢弃。
                    之后，欲引用主存储器上的值的线程，必定会从主存储器将值拷贝到工作拷贝（read->load）
            线程退出synchronized
                1、强制写入主存储器
                    当线程欲退出synchronized时，如果该线程的工作存储器（working memory）上有未映像到主存储器的拷贝，
                    则这些内容会强制写入主存储器（store->write），则这些计算结果就会对其它线程可见（visible）

            线程欲退出synchronized时，不会执行工作存储器（working memory）的释放 操作
     */



    /*
        volatile本质
            volatile具有以下两种功能
                1、内存同步
                    volatile只能做内存同步，不能取代synchronized关键字做线程同步
                    当线程欲引用volatile字段的值时，通常都会发生从主存储器到工作存储器的拷贝操作
                    相反的，将值指定给写着volatile的字段后，工作存储器的内容通常会立即映像到主存储器
                2、以原子方式进行long、double的指定

     */
}
