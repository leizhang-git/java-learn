package com.lei.test;

import java.util.LinkedList;
import java.util.Random;

/**
 * guarded：“被保护着的”、“被防卫着的”
 * suspension：“暂停”
 * 当现在并不适合马上执行某个操作时，就要求想要执行该操作的线程等待 即为 Guarded Suspension Pattern
 * @Author zhanglei
 * @Date 2022/2/15 10:20
 */
public class GuardedSuspensionPatternTest {

    //Guarded Suspension Pattern 会要求线程等候，以保障实例的安全性，其它类似的称呼还有
    // guarded wait、spin lock等

    /*
        eg：一种简单的消息处理模型，客户端线程发起请求，有请求队列缓存请求，然后发送给服务端线程进行处理
     */
    public static void main(String[] args) {
        RequestQueue requestQueue = new RequestQueue();
        new ClientThread(requestQueue, "Alice", 3141592L).start();
        new ServerThread(requestQueue, "Bobby", 6535897L).start();
    }

    //Guarded Suspension Pattern 模式
    /*
        角色：
            GuardedObject (被防卫的对象)参与者
        GuardedObject 参与者是一个拥有被防卫的方法（guardedMethod）的类。
        当线程执行guardedMethod时，只要满足警戒条件，就能继续执行，否则线程会进入wait set区等待。
     */

    // 使用while语句和wait方法来实现guardedMethod的
    // 使用notify/notifyAll方法实现stateChangingMethod
}

class Request {

    private final String name;

    Request(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                '}';
    }
}

//客户端不断生成请求，插入请求队列
class ClientThread extends Thread {
    private Random random;
    private RequestQueue requestQueue;

    public ClientThread(RequestQueue requestQueue, String name, long seed) {
        super(name);
        this.requestQueue = requestQueue;
        this.random = new Random(seed);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            Request request = new Request("No." + i);
            System.out.println(Thread.currentThread().getName() + " requests " + request);
            requestQueue.putRequest(request);

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {

            }
        }
    }
}

//客户端线程不断从请求队列中获取请求，然后处理请求
class ServerThread extends Thread {
    private Random random;
    private RequestQueue requestQueue;
    public ServerThread(RequestQueue requestQueue, String name, long seed) {
        super(name);
        this.requestQueue = requestQueue;
        this.random = new Random(seed);
    }
    public void run() {
        for (int i = 0; i < 10000; i++) {
            Request request = requestQueue.getRequest();
            System.out.println(Thread.currentThread().getName() + " handles  " + request);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
            }
        }
    }
}


//请求队列类
class RequestQueue {

    private final LinkedList<Request> queue = new LinkedList<>();

    public synchronized Request getRequest() {
        //Guarded Suspension Pattern 的警戒条件（guard condition）
        while (queue.size() <= 0) {
            try {
                wait();
            }catch (InterruptedException e) {

            }
        }
        return (Request) queue.removeFirst();
    }

    public synchronized void putRequest(Request request) {
        queue.addLast(request);
        notifyAll();
    }
}