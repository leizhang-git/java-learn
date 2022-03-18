package com.lei.test;

/**
 * Volatile关键字
 * 使用锁可以剞劂共享变量内存可见性问题，但是使用锁太笨重，因为它会带来线程上下文的切换开销
 * 使用 volatile关键字可以确保对一个变量的更新对其他线程马上可见！
 */
public class B4VolatileTest {

    /*
        当一个变量被声明为volatile时，线程在写入变量时不会把值缓存在寄存器或者其他地方
        而是会把值刷新回主内存，当其他线程读取该共享变量时，会从主内存重新读取最新值，而不是使用当前线程的工作内存中的值

        volatile 的内存语义和 synchronized 有相似之处，具体来说就是，
        当线程写入了 volatile变量值时就等价于线程退出 synchronized同步块（把写入工作内存的变量值同步到主内存），
        读取 volatile 值时就相当于进入同步块 先清空本地内存变量值，再从主内存获取最新值）
     */

//    private int value;
//
//    public synchronized int getValue() {
//        return value;
//    }
//
//    public synchronized void setValue(int value) {
//        this.value = value;
//    }

    /**
     * volatile 与 synchronized 都可以解决共享变量 value 内存可见性问题，
     * 但后者是独占锁，同时只能有一个线程调用get()方法，其他调用线程会被阻塞
     */
    private volatile int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    /*
        volatile不保证原子性！！！！只保证可见性
        写入变量值不依赖变量的当前值时，以及没有加锁情况时  可以使用volatile
     */
}
