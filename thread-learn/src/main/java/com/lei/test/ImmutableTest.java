package com.lei.test;

/**
 * Immutable：永恒的，不会改变
 * 可以省去使用共享互斥机制所会浪费的时间，提高系统性能（eg：String）
 * @Author zhanglei
 * @Date 2022/2/15 10:05
 */
public class ImmutableTest {

    /*
        eg：
            字段都是私有的，只能通过构造器来设置，且只有get方法，没有set方法。
            这时，即使有多个线程同时访问相同实例，类也是安全的，它的所有方法都不需要定义成synchronized。
     */

    public static void main(String[] args) {
        Person alice = new Person("Alice", "Alaska");
        new PrintPersonThread(alice).start();
        new PrintPersonThread(alice).start();
        new PrintPersonThread(alice).start();
    }

    //Immutable模式
    /*
        角色：
            Immutable(不变的)参与者
        Immutable参与者是一个字段值无法更改的类，也没有任何用来更改字段值的方法。
        当Immutable参与者的实例建立后，状态就完全不再变化

        适用场景：
        Immutable模式的优点在于，“不需要使用synchronized保护”。
        而“不需要使用synchronized保护”的最大优点就是可在不丧失安全性与生命性的前提下，提高程序的执行性能。
        若实例由多数线程所共享，且访问非常频繁，Immutable模式就能发挥极大的优点
     */
}

class PrintPersonThread extends Thread {
    private Person person;

    public PrintPersonThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " prints " + person);
        }
    }
}


/**
 *  字段都是私有的，只能通过构造器来设置，且只有get方法，没有set方法(线程安全)
 */
class Person {
    private final String name;
    private final String address;


    Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}