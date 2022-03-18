package com.lei.test;

/**
 * LongAdder
 * @Author: zhang lei
 * @Date: 2022/3/16 15:30
 */
public class D2LongAdderTest {
    /**
     * 使用LongAdder时，在内部维护多个Cell变量，每个Cell里面有一个初始值为0的long型变量
     * 这样，在同等并发量的情况下，争夺单个变量更新操作的线程量会减少，这变相地减少了争夺共享资源
     * 的并发量
     * 另外，多个线程在争夺同一个Cell原子变量时如果失败了，它并不是在当前Cell变量上一致自旋CAS重试，而是
     * 尝试在其他Cell变量上进行CAS尝试，这个改变增加了当前线程重试 CAS 成功的可能性
     * 最后，在获取 LongAdder当前值时，是把所有 Cell 变量 value 值累加后再加上 base 返回的
     * @param args
     */
    public static void main(String[] args) {
        //Cell使用@sun.misc.Contended注解进行修饰，这避免了cells数组内多个原子变量被放入
        //同一个缓存行，即避免了伪共享，提升了性能
    }
}
