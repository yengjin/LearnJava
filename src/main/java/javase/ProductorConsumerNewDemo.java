package javase;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyCake {
    int number = 1;
    Lock lock = new ReentrantLock();    // 可重入锁
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();

    public void print5() throws Exception {
        lock.lock();
        try {
            // 有蛋糕了, 不需要生产了
            // 虚假唤醒机制
            // 标志位
            while (number != 1) {
                conditionA.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t Hello!");
            }
            number = 2;
            conditionB.signal();
            //notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void print10() throws Exception {
        lock.lock();
        try {
            // 没蛋糕了, 没吃的了
            while (number != 2) {
                conditionB.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t Hello!");
            }
            number = 3;
            conditionC.signal();
            //notifyAll();    // 唤醒队列中所有线程
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() throws Exception {
        lock.lock();
        try {
            // 没蛋糕了, 没吃的了
            while (number != 3) {
                conditionC.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t Hello!");
            }
            number = 1;
            //notifyAll();    // 唤醒队列中所有线程
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ProductorConsumerNewDemo {

    public static void main(String[] args) {
        MyCake cake = new MyCake();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    cake.print5();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    cake.print10();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    cake.print15();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
    }

}
