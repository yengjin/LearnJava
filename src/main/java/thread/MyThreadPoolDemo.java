package thread;

import java.util.concurrent.*;

public class MyThreadPoolDemo {

    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(2, 5,
                1L, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(3),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务...");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    public static void threadPoolInit() {
        //ExecutorService threadPool = Executors.newFixedThreadPool(5); // 一池5个处理线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor(); // 一池1个处理线程
        ExecutorService threadPool = Executors.newCachedThreadPool(); // 一池N个处理线程

        // 模拟10个用户来办理业务, 每个用户就是一个来自外部的请求线程
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务...");
                });
                //try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

}
