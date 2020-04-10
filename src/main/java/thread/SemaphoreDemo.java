package thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

    public static void main(String[] args) {
        // 许可证
        Semaphore semaphore = new Semaphore(3); // 模拟3个停车位
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();    // 获取许可
                    System.out.println(Thread.currentThread().getName() + "\t 抢到车位");
                    try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
                    System.out.println(Thread.currentThread().getName() + "\t 停车3秒后离开座位");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();    // 许可证退回去
                    //System.out.println(" 剩余座位: " + semaphore.availablePermits());
                }
            }, String.valueOf(i)).start();
        }
    }
}
