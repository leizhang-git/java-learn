package com.lei.test;

import java.util.Random;

/**
 * Random类及其局限性：
 *
 * @Author: zhang lei
 * @Date: 2022/3/15 20:14
 */
public class C1RandomTest {

    /**
     * 由于原子变量更新是CAS操作，同时只有一个线程会成功，
     * 所以会造成大量线程进行自旋重试 这会降低并发性能，所以ThreadLocalRandom 应运而生。
     * @param args
     */
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            //输出一个[0,5)之前的随机数
            System.out.println(random.nextInt(5));
        }
    }

    /**
     *  随机数源码：
     *     protected int next(int bits) {
     *         long oldseed, nextseed;
     *         AtomicLong seed = this.seed;
     *         do {
     *             //获取当前原子变量种子的值
     *             oldseed = seed.get();
     *             //根据旧数据计算新种子
     *             nextseed = (oldseed * multiplier + addend) & mask;
     *         } while (!seed.compareAndSet(oldseed, nextseed));    //CAS操作，采用新种子更新老种子（保证只有1个线程可以更新老种子为新的）
     *         //固定算法进行计算
     *         return (int)(nextseed >>> (48 - bits));
     *     }
     */

}
