package juc;

import java.util.concurrent.*;

public class ThreadPoolDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3,
                5,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        pool.shutdownNow();
        for (int i = 0; i < 5; i++) {
            Future future = pool.submit(() -> {
                System.out.println(Thread.currentThread().getName() + "\t");
                Object obj = null;
                System.out.println(obj.toString());
            });
            try {
                future.get();
            } catch (Exception e) {
                System.out.println("发生异常");
            }
        }
    }
}
