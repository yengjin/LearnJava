package javase;

/**
 * 生产者消费者 - 初版
 * 生产者线程:   如果柜子里没蛋糕了, 就生产, 否则等待
 * 消费者线程:   如果柜子里有蛋糕, 就吃, 否则等待
    资源类
 */

class Cake {
    volatile int number = 0;     // 柜子里有蛋糕

    public synchronized void product() throws Exception {
        // 有蛋糕了, 不需要生产了
        // 虚假唤醒机制
        while (number != 0) {
            wait();     // 阻塞队列
        }
        number++;
        // 我生产完了, 你过来吃吧
        notifyAll();
        System.out.println(Thread.currentThread().getName() + "\t蛋糕数:" + number);
    }

    public synchronized void consume() throws Exception {
        // 没蛋糕了, 没吃的了
        while (number == 0) {
            wait();
        }
        number--;       // number = 0
        notifyAll();    // 唤醒队列中所有线程
        System.out.println(Thread.currentThread().getName() + "\t蛋糕数:" + number);
    }
}

public class ProductorConsumerDemo {
    public static void main(String[] args) {
        Cake cake = new Cake();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    cake.product();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "生产者1").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    cake.consume();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "消费者1").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    cake.product();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "生产者2").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    cake.consume();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "消费者2").start();
    }

}
