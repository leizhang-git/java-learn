package com.lei.test;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: zhang lei
 * @Date: 2022/3/15 20:28
 */
public class C2ThreadLocalRandom {
    public static void main(String[] args) {
        /**
         * 实现原理与ThreadLocal类似
         * 每个线程都维护了一个种子变量，该种子变量只有在使用随机数时才会被初始化
         * 在多线程下计算新种子时是根据自己线程内维护的种子变 进行更新，从而避免了竞争。
         */
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(5));
        }
    }

    /*
        ThreadLocal实现原理：让每一个线程复制一份变量，使得在每个线程对变量进行操作时实际是操作
        自己本地内存里面的副本，从而避免了对共享变量进行同步！
     */
}
