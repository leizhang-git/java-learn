package com.lei.test;

/**
 * @Author zhanglei
 * @Date 2022/2/11 9:34
 */
public class A1ThreadTest extends Thread{

    /*
        创建了thread 对象后该线程并没有被启动执行，直到调用了 start 方法后才真正启动了线程!
        其实调用start方法后线程并没有马上执行，而是处于就绪状态（就绪状态指的是：获取了除CPU资源外的其他资源）
        等获取了CPU资源才会真正处于运行状态，一旦run方法运行完毕，该线程就处于终止状态
     */
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.print(i + " ");
        }
    }

    /*
        此类创建线程方法缺点：
        继承了Thread类就无法继续继承其他类，且任务与代码没有分离
     */
    public static void main(String[] args) {
        A1ThreadTest threadTest = new A1ThreadTest();
        threadTest.start();
        //主线程继续同时向下执行
        for (int i = 0; i < 10000; i++) {
            System.out.print(i + " ");
        }
    }
}



