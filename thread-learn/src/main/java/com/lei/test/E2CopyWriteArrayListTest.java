package com.lei.test;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyWriteArrayList
 * @Author: zhang lei
 * @Date: 2022/3/16 16:42
 */
public class E2CopyWriteArrayListTest {

    public static void main(String[] args) {
        /**
         * 1.初始化
         * 无参构造：内部创建了一个大小为0的Object数组作为array的初始值
         * 有参构造：创建一个list，其内部元素是入参toCopyIn的副本，入参为集合，将集合里面的元素复制到本list
         */
        CopyOnWriteArrayList<Object> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

        /**
         * 2.添加元素
         * 首先会获取独占锁，若多个线程都调用add方法则只有一个线程会获取到该锁，其他线程会被阻塞挂起直到锁被释放！
         * 线程获取锁后获取array，然后将其复制到一个新数组（新数组大小是原来数组大小+1，所以是无界数组），且把新增的元素添加到新数组
         * 然后将新数组替换原数组，并在返回前释放锁!!! 整个add操作是原子性操作
         * （整个过程在快照上进行）
         */
        copyOnWriteArrayList.add("1");

        /**
         * 3.获取指定位置元素
         * 可能会产生弱一致性问题！（1.查找数组  2.找下标）线程A -1 线程2将其删除
         */
        Object o = copyOnWriteArrayList.get(1);

        /**
         * 4.修改指定元素
         * 指定位置的元素不存在则抛出 IndexOutOfBoundsException 异常
         * 首先获取独占锁，组织其他线程对array数组进行修改，然后获取当前数组，并调用 get 方法获取指定位置的元素，
         * 如果指定位置的元素值与新值不一致则创建新数组井复制元素，然后在新数组上修改指定位置的元素值并设置新数组到 array
         * 为了保证volatile语义！即使元素值不变，也需要重新设置array
         */
        Object set = copyOnWriteArrayList.set(2, "2");

        /**
         * 5.删除元素
         * 首先获取独占锁以保证删除数据期间其他线程不能对 array 进行修改，
         * 然后获取数组中要被删除的元素，并把剩余的元素复制到新数组，之后使用新数组替换原来的数组，最后在返回前释放锁
         */
        copyOnWriteArrayList.remove(1);


        /**
         * 弱一致性的迭代器（返回迭代器后，其他线程对 list的增删改对迭代器是不可见的！）
         */
        Iterator<Object> iterator = copyOnWriteArrayList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


        /**
         * CopyOnWriteArrayList 使用写时复制的策略来保证 list 的一致致性，而获取 - 修改 - 写入
         * 三步操作并不是原子性的，所以在增删改的过程中都使用了独占锁，来保证在某个时间只有一个线程能对 list 数组进行修改
         * 另外 opyOnWriteArrayList 供了弱一致性的法代器，从而保证在获取迭代器后，其他线程对 list 修改是不可见的，
         * 迭代器遍历的数组是一个快照
         */
    }
}
