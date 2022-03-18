package com.lei.test;

/**
 * 指令重排序:
 * Java内存模型允许编译器和处理器对指令重排序以提高运行性能，且
 * 只会对不存在数据依赖性的指令重排序，单线程下重排序没问题，但是多线程下会存在问题！！
 * @Author: zhang lei
 * @Date: 2022/3/14 17:34
 */
public class B8InstructReorderTest {

    private static int num = 0;

    private static boolean ready = false;

    public static class ReadThread extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                if(ready) {
                    System.out.println(num + num);
                }
                System.out.println("read thread...");
            }
        }
    }

    public static class Writethread extends Thread {
        @Override
        public void run() {
            num = 2;
            ready = true;
            System.out.println("writeThread set over...");
        }
    }

    /**
     * 不考虑内存可见性问题下  可能会输出0，重排序在多线程下会导致非预期的程序执行结果！
     * 使用volatile修饰ready可以避免重排序和可见性问题
     * 写 volatile变量时，可以确保 volatile写之前的操作不会被编译器重排序到 volatile写之后
     * 读 volatile变量时，可以确保 volatile读之后的操作不会被编译器重排序到 volatile读之前
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ReadThread rt = new ReadThread();
        rt.start();

        Writethread wt = new Writethread();
        wt.start();

        Thread.sleep(10);
        rt.interrupt();
        System.out.println("main exit");
    }
}
