package com.lei.test;

/**
 * 守护线程与用户线程
 * daemon 守护线程
 * user 用户线程
 */
public class A9ShouhuTest {

    /*
        若希望主线程结束后JVM进程马上结束，那么创建线程时可以将其设置为守护线程
        如果希望在主线程结束后子线程继续工作，等子线程结束后再让JVM进程结束，那么就将子线程设置为用户线程。
     */
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {

        });

        //设置为守护线程
        thread.setDaemon(true);
        thread.start();
    }
}
