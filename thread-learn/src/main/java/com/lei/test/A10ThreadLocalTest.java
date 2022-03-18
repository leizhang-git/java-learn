package com.lei.test;

/**
 * ThreadLocal
 * 创建了ThreadLocal变量，那么访问这个变量的每个线程都会有这个变量的一个本地副本
 * 当多个线程操作这个变量时，实际操作的是自己本地内存里面的变量，从而避免了线程安全问题
 */
public class A10ThreadLocalTest {

    static ThreadLocal<String> localVariable = new ThreadLocal<>();

    static void printf(String str) {
        //打印当前线程本地内存中 localVariable变量的值
        System.out.println(str + ":" + localVariable.get());
        //清除该变量的值
//        localVariable.remove();
    }

    /**
     * 在每个线程内部都有一个名为threadLocals的成员变量，类型为HashMap
     * key为我们定义的ThreadLocal变量的this引用
     * value为我们使用set方法设置的值
     * 每个线程的本地变量存放在线程自己的内存变量threadLocals中，若当前线程一直不消亡
     * 那么这些本地变量会一直存在，所以可能会造成内存溢出，因此使用完毕后要记得调用ThreadLocal的remove方法删除本地变量
     * @param args
     */
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            localVariable.set("xxxxxxx1111");

            printf("t1");

            System.out.println("t1 remove after" + ":" + localVariable.get());
        });

        Thread t2 = new Thread(() -> {
            localVariable.set("xxxxxxx2222");

            printf("t2");

            System.out.println("t2 remove after" + ":" + localVariable.get());
        });

        t1.start();
        t2.start();
    }

    /*
        ThreadLocal 不支持继承性!
        因为子线程thread里面调用get方法时当前线程为thread线程，set方法设置线程变量的时main线程！
        InheritableThreadLocal继承自ThreadLocal，且可以让子线程访问在父线程中设置的本地变量
     */
}
