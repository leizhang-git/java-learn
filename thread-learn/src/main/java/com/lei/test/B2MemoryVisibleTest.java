package com.lei.test;

/**
 * 内存可见性
 * Java内存模型规定，将所有的变量都存放在主内存中，当线程使用变量时，
 * 会把主内存里面的变量复制到自己的工作空间（工作内存）线程读写变量时操作的是自己工作内存中的变量！
 * 但是线程A修改的共享变量对于线程B不可见！若A修改了共享变量，线程B获取的还是之前的
 */
public class B2MemoryVisibleTest {

    /*
        线程A：           线程B：
            控制器             控制器
            运算器             运算器
            L1 Cache          L1 Cache

              所有CPU 共享的 L2 Cache
              主内存（共享变量1、共享变量2）
     */

    private static Integer i = 100;

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            i++;
        });
        t1.start();

        System.out.println(i);
    }
}
