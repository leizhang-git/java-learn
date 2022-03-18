package com.lei.test;

/**
 * @Author zhanglei
 * @Date 2022/2/14 15:04
 */
public class SleepTest {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
            try {
                //使当前正在执行的线程暂停指定的时间，如果线程持有锁，sleep方法结束前并不会释放该锁
                Thread.sleep(1000);    //当前main线程暂停1000ms
            } catch (InterruptedException e) {
            }
        }
    }
}
