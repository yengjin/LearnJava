package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁代码
 * 同一个线程, 外层函数获得锁之后, 内层函数仍然能获取锁.
 * 线程可以进入它已经拥有锁的全部代码.
 *
 * 证明1: synchronized是典型的可重入锁
 * t2	 invoked sendSMS    t2在外层方法获取锁
 * t2	 ##### invoked sendEmail    t2在内层方法获得锁 (重入锁)
 * t1	 invoked sendSMS    t1在外层方法获取锁
 * t1	 ##### invoked sendEmail    t1在内层方法获得锁 (重入锁)
 *
 * 证明2: ReentrantLock是典型的可重入锁
 * Thread-1	 invoked get
 * Thread-1	 ######invoked set
 * Thread-0	 invoked get
 * Thread-0	 ######invoked set
 */

class Phone implements Runnable {
    Lock lock = new ReentrantLock();    // 非公平锁
    @Override
    public void run() {
        get();
    }

    public void get() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoked get");
            set();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t ######invoked set");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Thread t3 = new Thread(phone);
        Thread t4 = new Thread(phone);
        t3.start();
        t4.start();
    }
}
