package com.lei.test;

/**
 * LongAdder类只是LongAccumulator的一个特例，
 * accumulatorFunction是一个双目运算器接口，其根据输入的两个参数返回一个计算器，identity则是LongAccumulator累加器的初始值
 * @Author: zhang lei
 * @Date: 2022/3/16 16:02
 */
public class D3LongAccumulatorTest {
    /**
     * LongAccumulator相比于LongAdder，可以为累加器提供非0的初始值，后者只能提供默认的0值
     * 另外，前者还可以指定累加规则， 如不进行累加而进行相乘，只需要在构造 LongAccumulator时传入自定义的双目运算器即可
     * @param args
     */
    public static void main(String[] args) {

    }
}
