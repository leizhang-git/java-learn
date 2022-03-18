package com.lei.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 三种创建方式总结：
 * 采用继承方式好处：方便传参
 * 采用runnable方式：只能使用主线程里面被声明为final的变量
 * FutureTask：可以返回结果
 * @Author zhanglei
 * @Date 2022/3/2 14:07
 */
public class A3CallableTest implements Callable {

    @Override
    public Object call() throws Exception {
        return "hi";
    }

    public static void main(String[] args) {
        //创建异步任务
        FutureTask task = new FutureTask<>(new A3CallableTest());
        //启动线程
        new Thread(task).start();

        try {
            //等待任务执行完毕，并返回结果
            Object o = task.get();
            System.out.println(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
