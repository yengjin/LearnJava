package javase;
//多线程有序打印1和2，分别打印10次

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MResource {
    int flag = 1;

    public synchronized void printA() throws Exception {
        while (flag != 1) {
            wait();
        }
        System.out.println("1");
        flag = 2;
        notifyAll();
    }

    public synchronized void printB() throws Exception {
        while (flag != 2) {
            wait();
        }
        System.out.println("2");
        flag = 1;
        notifyAll();
    }
}
public class ThreadDemo01 {

    public static void main(String[] args) {
        MResource resource = new MResource();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    resource.printA();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    resource.printB();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
    }
}
