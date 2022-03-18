package com.lei.test;


import sun.misc.Unsafe;

/**
 * CAS操作
 * @Author: zhang lei
 * @Date: 2022/3/14 16:35
 */
public class B6CASTest {

    /**
     * 当一个线程没有获取到锁时，就会被阻塞挂起。这会导致线程上下文的切换和重新调度开销
     * CAS即 Compare and Swap，是JDK提供的非阻塞原子性操作，通过硬件保证了 比较 - 更新 操作的原子性！
     * JDK里面的Unsafe类提供了一系列的 CompareAndSwap*方法！
     */


    public static void main(String[] args) {
        /**
         * 若对象obj中内存偏移量为 valueOffset 的变量值为 expect，则使用新的值 update替换旧的 expect
         * 四个操作数：obj、valueOffset、expect、update
         * obj：对象内存位置
         * valueOffset：对象中的变量的偏移量
         * expect：变量预期值
         * update：最新的值
         */
        Unsafe.getUnsafe().compareAndSwapLong(1, 1L, 1L, 1L);

        /*
            关于CAS操作有一个ABA问题！
                线程1使用CAS修改初始值为A的变量X，将变量X的值由 A 改为 B，CAS操作成功后，程序运行未必就是成功的！
                因为可能在线程1获取变量X的值A后，执行CAS前，线程2使用CAS修改了变量X的值为B，然后又改为A
                所以虽然线程1执行CAS时X的值为A，但是这个A已经不是线程1获取时的A了，这就是ABA问题
            ABA问题的产生是因为变量的状态值产生了环形转换， A - B - A，若不构成环状，旧不会存在问题
            JDK中 AtomicStampedReference 类给每个变量的状态值都配备了一个时间戳，从而避免ABA问题的产生
         */
    }
}
