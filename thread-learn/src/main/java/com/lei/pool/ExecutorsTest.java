package com.lei.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 不建议使用！！！
 */
public class ExecutorsTest {
    public static void main(String[] args) {

        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // 执行线程任务(原生创建时采用的是 newLinkedBlockingQueue<Runnable>() 是一个无边界队列，一直加任务，会导致内存问题！)
        executorService.execute(() -> System.out.println("111"));
    }
}
