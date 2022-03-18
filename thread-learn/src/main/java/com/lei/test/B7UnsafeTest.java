package com.lei.test;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Unsafe类：提供硬件级别的原子性操作
 * @Author: zhang lei
 * @Date: 2022/3/14 17:25
 */
public class B7UnsafeTest {

    static final Unsafe unsafe;

    static final long stateOffset;

    private volatile long state = 0;

    static {
        try {
            //使用反射获取Unsafe的成员变量theUnsafe
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            //设置为可存取
            field.setAccessible(true);
            //获取该变量的值
            unsafe = (Unsafe) field.get(null);
            //获取state在TestUnSafe中的偏移量
            stateOffset = unsafe.objectFieldOffset(B7UnsafeTest.class.getDeclaredField("state"));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw new Error(e);
        }
    }

    public static void main(String[] args) {
        B7UnsafeTest test = new B7UnsafeTest();
        boolean success = unsafe.compareAndSwapInt(test, stateOffset, 0, 1);
        System.out.println(success);
    }
}
