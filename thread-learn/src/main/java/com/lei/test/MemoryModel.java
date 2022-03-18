package com.lei.test;

/**
 * Java内存模型
 * @Author zhanglei
 * @Date 2022/2/14 15:24
 */
public class MemoryModel {

    //Java内存模型分为：主存储器（main memory）和工作存储器（working memory）两种

    //主存储器（main memory）：
    //类的实例所存在的区域，main memory为所有的线程所共享

    //工作存储器（working memory）：
    //每个线程各自独立所拥有的作业区，在working memory中，存有main memory中的部分拷贝，称之为工作拷贝（working copy）

    /*
        Java语言规范定义了线程的六种原子操作：
            read
            负责从主存储器（main memory）拷贝到工作存储器（working memory）
            write
            与上述相反，负责从工作存储器（working memory）拷贝到主存储器（main memory）
            use
            表示线程引用工作存储器（working memory）的值
            assign
            表示线程将值指定给工作存储器（working memory）
            lock
            表示线程取得锁定
            unlock
            表示线程解除锁定
     */
}
