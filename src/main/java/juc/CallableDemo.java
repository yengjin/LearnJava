package juc;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 1. 继承Thread
 * 2. 实现Runnable接口
 * 3. Callable
 * 多线程中, 第3种获得多线程的方式
 */

class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("Hello, World!");
        // 暂停一会线程
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        return 1024;
    }
}

public class CallableDemo {
    // 1. 抛异常
    // 2. 有返回值
    public static void main(String[] args) throws Exception {
        FutureTask futureTask = new FutureTask(new MyCallable());
        Thread thread = new Thread(futureTask);
        thread.start();

        futureTask.isDone();

        System.out.println(futureTask.get());
    }
}
