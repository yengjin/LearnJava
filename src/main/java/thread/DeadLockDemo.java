package thread;

import java.util.concurrent.TimeUnit;

/**
 * 死锁代码
 * 死锁指两个或两个以上的进程在执行过程中,
 * 因争夺资源而造成的一种互相等待的现象, 若无外力干涉那它们都将无法推进下去
 */

class HoldLockThread implements Runnable {
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }
    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t 自己持有" + lockA + "\t 尝试获得: " + lockB);
            // 暂停一会线程
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有" + lockB + "\t 尝试获得: " + lockA);
            }
        }
    }
}
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA, lockB), "ThreadAAA").start();
        new Thread(new HoldLockThread(lockB, lockA), "ThreadBBB").start();

        /**
         * Linux  ps -ef|grep xxxx          ls -l
         * windows下的Java运行程序 也有类似ps的查看进程的命令, 但是我们目前需要查看的知识java
         *      jps = java版的ps
         *
         *      1. jps -l 查看java进程, 获取死锁pid进程号
         *      2. jstack pid    打印出 Found 1 deadLock, 表示有死锁
         */
    }
}
