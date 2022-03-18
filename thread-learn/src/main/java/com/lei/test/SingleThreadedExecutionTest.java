package com.lei.test;

/**
 * Single Threaded Execution ：以一个线程执行   =    Critical Section（临界区）
 * @Author zhanglei
 * @Date 2022/2/15 9:40
 */
public class SingleThreadedExecutionTest {

    /*
        eg：有三个人，频繁的通过一扇门，规定每次只能通过一个人，当通过一个人时
            程序会将通过的总人次加1，同时记录该次通过人的姓名和出生地
     */
    public static void main(String[] args) {
        System.out.println("Testing Gate, hit CTRL+C to exit.");
        Gate gate = new Gate();
        new UserThread(gate, "Alice", "Alaska").start();
        new UserThread(gate, "Bobby", "Brazil").start();
        new UserThread(gate, "Chris", "Canada").start();
    }

    //Single Threaded Execution 模式
    /*
        角色：
            SharedResource(共享资源)参与者
        SharedResource就是多线线程会同时访问的资源类，该类通常具有2类方法：
            SafeMethod——从多个线程同时调用也不会发生问题的方法
            UnsafeMethod——从多个线程同时调用会发生问题，这类方法需要加以防护，指定只能由单线程访问区域，即临界区（critical section）。

     */
}


/**
 * 门
 */
class Gate {

    //计数器
    private int counter = 0;

    //通过人的姓名
    private String name = "lei";

    //通过人的地址
    private String address = "Inner Mongolia";

    //通过
    //线程不安全
//    public void pass(String name, String address) {
//        this.counter++;
//        this.name = name;
//        this.address = address;
//        check();
//    }

    public synchronized void pass(String name, String address) {
        this.counter++;
        this.name = name;
        this.address = address;
        check();
    }

    private synchronized void check() {
        if(name.charAt(0) != address.charAt(0)) {
            System.out.println("============ BROKEN ============== " + toString());
        }
    }

    @Override
    public String toString() {
        return "Gate{" +
                "counter=" + counter +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

}

class UserThread extends Thread {

    private final Gate gate;

    private final String myName;

    private final String myAddress;


    UserThread(Gate gate, String myName, String myAddress) {
        this.gate = gate;
        this.myName = myName;
        this.myAddress = myAddress;
    }

    @Override
    public void run() {
        System.out.println(myName + " BEGIN");
        while (true) {
            gate.pass(myName, myAddress);
        }
    }
}
