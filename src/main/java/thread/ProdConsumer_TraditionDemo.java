package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 传统方式生产者消费者
 * 题目: 一个初始值为0的变量, 两个线程交替操作, 一个加1一个减1, 来5轮
        1. 线程 操作(方法) 资源类
        2. 判断 干活 通知
        3. 防止虚假判断机制  -- 使用while判断而不是if
 */

// 资源类
class ShareData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void increment() throws Exception {
        lock.lock();
        try {
            // 1 判断
            while (number != 0) {
                // 等待, 不能生产 (物品大于0)
                condition.await();
            }
            // 2 干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t " + number);

            // 3 通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception {
        lock.lock();
        try {
            // 1 判断
            while (number == 0) {
                // 等待, 不能消费 (物品等于0)
                condition.await();
            }
            // 2 干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t " + number);

            // 3 通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}

public class ProdConsumer_TraditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "DD").start();
    }
}
