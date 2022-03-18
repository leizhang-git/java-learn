package com.lei.test;

/**
 * join方法是Thread类直接提供的，join是无参且返回值为void的方法  用于汇总
 * sleep静态方法被调用后，调用线程会暂时让出指定时间的执行权，即这期间不参与CPU的调度，
 * 但是该线程所拥有的监视器资源，比如锁还是持有不让出的。
 * 指定的睡眠时间到了后该函数会正常返回，线程就处于就绪状态，然后参与CPU的调度。获取到CPU资源后就可以继续运行了
 * 若在睡眠期间其他线程调用了该线程的 interrupt()方法中断了该线程，则该线程会在调用sleep方法时抛出异常
 * @Author zhanglei
 * @Date 2022/3/7 13:11
 */
public class A5JoinTest {

    public static void main(String[] args) {
        A5JoinTest a5JoinTest = new A5JoinTest();
        a5JoinTest.jsonTest();
    }

    public void jsonTest() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("child thread1 over!");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("child thread2 over!");
            }
        });

        //启动子线程
        t1.start();
        t2.start();

        System.out.println("wait all child thread over!");

        //等待子线程执行完毕，返回
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("all child thread over!");
    }
}
