package com.lei.test;

/**
 * @Author zhanglei
 * @Date 2022/2/14 15:01
 */
public class A2RunnableTest implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.print(i + " ");
        }
    }

    /*
        此创建方法对比直接集成Thread来说好了一些，但是依旧没有返回值！
     */
    public static void main(String[] args) {
        Thread t = new Thread(new A2RunnableTest());
        t.start();    //启动子线程
        //主线程继续同时向下执行
        for (int i = 0; i < 10000; i++) {
            System.out.print(i + " ");
        }
    }
}
