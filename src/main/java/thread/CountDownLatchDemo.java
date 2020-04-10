package thread;

import enums.CountryEnum;

import java.util.concurrent.CountDownLatch;

import static java.lang.String.valueOf;
import static java.lang.System.out;

// 闭锁
public class CountDownLatchDemo {
    // 锁门的例子
    public static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 上完自习 离开教室");
                countDownLatch.countDown(); // 计数器减少1
            }, String.valueOf(i)).start();
        }
        // 直到计数器=0, 等待
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t ***********班长最后关门走人");
    }
    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 国, 被秒");
                countDownLatch.countDown();
            }, CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();
        }
        countDownLatch.await(); // 等待计数器置0
        System.out.println("******一统天下成功******");
    }
}
