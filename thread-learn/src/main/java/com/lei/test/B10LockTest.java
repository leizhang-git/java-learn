package com.lei.test;

import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁：
 * @Author: zhang lei
 * @Date: 2022/3/15 14:29
 */
public class B10LockTest {

    JdbcTemplate jdbcTemplate;
    /**
     * 悲观锁：对数据被外界修改持保守态度，认为数据很容易被其他线程修改，所以直接在数据被处理器就加锁
     * 实现上往往依靠数据库提供的锁机制！增加排他锁，
     * 乐观锁：认为数据在一般情况下不会造成冲突，所以在访问记录前不会加排他锁，而是在进行数据提交更新时
     * 才会正式对数据冲突与否进行检测，乐观锁一般不会使用数据库提供的锁机制，一般在表中添加version字段或者用业务状态实现
     * 乐观锁直到提交时才锁定，不会产生死锁！！！
     * @param id
     */
    public int updateEntry(long id) {
        return 1;
    }

    /**
     * 公平锁：线程获取锁的顺序是按照线程请求锁的事件早晚来决定的
     * 非公平锁：运行时闯入
     * 在没有公平性需求的前提下尽量使用非公平锁，因为公平锁会带来性能开销!!!
     */
    public void test() {
        //公平锁
        ReentrantLock reentrantLock = new ReentrantLock(true);
        //非公平锁(若构造函数不传递参数，默认是非公平锁)
        ReentrantLock reentrantLock1 = new ReentrantLock(false);
    }

    /**
     * 独占锁：保证任何时候都只有一个线程能得到锁，ReentrantLock就是以独占方式实现的
     *          独占锁是一种悲观锁，由于每次访问资源都先加上互斥锁，会限制并发性！（读写不会影响数据一致性）
     * 共享锁：可以同时由多个线程持有，例如ReadWriteLock读写锁，其允许一个资源可以被多线程同时进行读操作
     *          共享锁是乐观锁
     */
    public void test1() {

    }

    /**
     * 可重入锁：当一个线程再次获取它自己已经获取的锁时，如果不被阻塞，则称该锁是可重入锁！
     * 即：只要该线程获取了该锁，那么可以无限次数的进入被该锁锁住的代码
     */
    public synchronized void test2() {
        System.out.println("test2");
    }

    /**
     * 实际上synchronized内部锁是可重入锁，可重入锁的原理是在锁内部维护一个标识，用来标识该锁目前被哪个线程占用
     * 当一个线程获取了该锁时，计数器的值会变为1，此时其他线程来获取锁时发现锁的所有者不是自己而被阻塞挂起！
     * 但是当获取了该锁的线程再次获取锁时会发现锁拥有者是自己，就会把计数器 + 1，释放锁时 计数器 - 1，计数器 = 0
     * 代码标识被重置为 null，这时被阻塞的线程 会被唤醒来竞争获取该锁
     */
    public synchronized void test3() {
        System.out.println("test3");
        test2();
    }

    /**
     * 自旋锁：
     * 自旋锁则是，当前线程在获取锁时，如果发现锁已经被其他线程占有，
     * 它不马上阻塞自己，在不放弃 CPU 使用权的情况下，多次尝试获取（默认次数是 10 ，可以使用 XX PreB lockS pin 数设置该值）
     * 很有可能在后面几次尝试中其他线程己经释放了锁，如果尝试指定的次数后仍没有获取到锁则当前线程才会被阻塞挂起
     * 由此看来自旋锁是使用 CPU 时间换取线程阻塞与调度的开销，但是很有可能这些 CPU 时间白白浪费！！！
     */
    public void test4() {

    }
}
