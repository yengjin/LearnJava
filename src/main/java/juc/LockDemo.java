package juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyTicket {
    int number = 1000000;
    Lock lock = new ReentrantLock(false);

    // Lock
    public void saleTicket() {
        lock.lock();
        try {
            number--;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class LockDemo {
    // synchronized  lock


    public static void main(String[] args) {
        MyTicket ticket = new MyTicket();   // 资源类

        for (int i = 1; i <= 1000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    ticket.saleTicket();
                }
            }, String.valueOf(i)).start();
        }



        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(ticket.number);
    }


}
