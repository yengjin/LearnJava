package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现A->B->C
 * AA打印5次, BB打印10次, CC打印15次
 * 来10轮
 */


class MyResource {
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    private int number = 1;
    public void printA () {
        lock.lock();
        try {
            while (number != 1) {
                conditionA.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t 打印" + i + "次");
            }
            // 修改标志位
            number = 2;
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            while (number != 2) {
                conditionB.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t 打印" + i + "次");
            }
            // 修改标志位
            number = 3;
            conditionC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void printC() {
        lock.lock();
        try {
            while (number != 3) {
                conditionC.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t 打印" + i + "次");
            }
            // 修改标志位并唤醒
            number = 1;
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ThreadOrderAccess {

    public static void main(String[] args) {
        MyResource myResource = new MyResource();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                myResource.printA();
            }, "A").start();

            new Thread(() -> {
                myResource.printB();
            }, "B").start();

            new Thread(() -> {
                myResource.printC();
            }, "C").start();
        }

    }
}
