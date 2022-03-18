package com.lei.test;

import java.util.Date;

/**
 * 双重检查锁定（具有危险性）
 * @Author zhanglei
 * @Date 2022/2/14 15:54
 */
public class DoubleCheckLockingPatternTest {

    //危险性1、可能存在缺陷的单例模式
}


class Main extends Thread {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            new Main().start();
        }
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":" + MySystem.getInstance().getDate());
    }
}

/**
 * 单例类：延迟加载 + 双重锁
 */
class MySystem {

    /*
        此处可以采用懒加载模型！
     */
//    private static MySystem instance = null;
    private static MySystem instance = new MySystem();

    private Date date = new Date();

    private MySystem() {

    }

    public Date getDate() {
        return date;
    }

    public static MySystem getInstance() {
        if(instance == null) {
            synchronized (MySystem.class) {
                if(instance == null) {
                    instance = new MySystem();
                }
            }
        }
        return instance;
    }
}