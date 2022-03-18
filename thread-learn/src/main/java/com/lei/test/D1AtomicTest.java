package com.lei.test;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: zhang lei
 * @Date: 2022/3/15 20:33
 */
public class D1AtomicTest  {

    /**
     * 高并发下存在性能问题，有更好的 LongAdder类
     */
    private static AtomicLong atomicLong = new AtomicLong();

    private static Integer[] arrayOne = new Integer[] {0,1,2,3,0,5,6,0,56,0};
    private static Integer[] arrayTwo = new Integer[] {10,2,3,4,0,5,6,0,56,0};

    /**
     * incrementAndGet：原始值+1，返回递增后的值
     * decrementAndGet：原始值-1，返回递减后的值
     * getAndIncrement：原始值+1，返回原始值
     * getAndDecrement：原始值-1，返回原始值
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        /**
         * unsafe.getAndAddLong()
         * jdk1.7：每个线程先拿到变量的当前值（有volatile最新值），然后在工作内存中对其进行+1操作
         * 之后使用CAS操作修改变量的值！若设置失败，循环继续尝试，直至成功
         */

        //线程1统计0的个数
        Thread t1 = new Thread(() -> {
            int size = arrayOne.length;
            for (int i = 0; i < size; i++) {
                if (arrayOne[i].intValue() == 0) {
                    atomicLong.incrementAndGet();
                }
            }
        });

        //线程2统计0的个数
        Thread t2 = new Thread(() -> {
            int size = arrayTwo.length;
            for (int i = 0; i < size; i++) {
                if (arrayTwo[i].intValue() == 0) {
                    atomicLong.incrementAndGet();
                }
            }
        });

        //启动子线程
        t1.start();
        t2.start();

        //等待线程执行完毕
        t1.join();
        t2.join();

        System.out.println("count 0:" + atomicLong.get());
    }
}
