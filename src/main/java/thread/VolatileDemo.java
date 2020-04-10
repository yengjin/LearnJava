package thread;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 1. 验证volatile保证可见性
 *      1.1 假如 int number = 0; number变量之前根本没有添加volatile关键字修饰, 没有可见性
 *      1.2 添加了volatile, 可以解决可见性问题
 *
 * 2. 验证volatile不保证原子性
 *      2.1 原子性是什么意思?
 *          不可分割, 完整性, 也即某个线程正在做某个具体业务时, 中间不可以被加塞或分割, 需要整体完整.
 *          要么同时成功, 要么同时失败.
 *      2.2 volatile是否保证原子性? - 不保证原子性的案例演示
 *
 *      2.3 volatile为什么不能保证原子性?
 *
 *      2.4 如何解决原子性?
 *          * 加synchronized (不能杀鸡用牛刀)
 *          * 使用JUC下的AtomicInteger
 */


class MyData {
    volatile int number = 0;

    public void addTo60() {
        this.number = 60;
    }

    // 请注意, 此时number前面是加了volatile关键字修饰的, 不保证原子性
    public void addPlusPlus() {
        number++;
    }

    // 默认值: 0
    AtomicInteger atomicInteger = new AtomicInteger();


    public void addMyAtomic() {
        atomicInteger.getAndIncrement();    // 相当于i++
    }
}


public class VolatileDemo {

    public static void main(String[] args) {
        atomicOkByVolatile();
    }

    // volatile保证可见性的例子, 及时通知其他线程, 主物理内存中共享资源已被修改
    public static void seeOkByVolatile() {
        MyData myData = new MyData();   //资源类

        // 第一个线程 AAA
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            // 模拟进行了3秒运算
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 写入主内存
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t updated number value: " + myData.number);
        }, "AAA").start();

        // 第二个线程 main线程
        while (myData.number == 0) {
            // main线程的工作内存里数据还是0 (如果不加volatile)
            // main线程就一直在这里等待循环, 直到number值不再等于0 (可见性被触发)
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over, main get number value: " + myData.number);
    }

    // volatile不保证原子性的例子, 怎么解决: AtomicInteger

    public static void atomicOkByVolatile() {
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    //myData.addPlusPlus();   // 相当于add++
                    myData.addMyAtomic();
                }
            }, String.valueOf(i)).start();
        }

        // 需要等待上面20个线程全部计算完成后, 再用main线程取得最终结果值
        // main线程, GC线程 = 2, 所以大于2个说明有其他线程在执行
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        // 结果小于 20 * 1000, 说明volatile不保证原子性
        //System.out.println(Thread.currentThread().getName() + "\t int type, finally number value: " + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t AtomicInteger type, finally number value: " + myData.atomicInteger);
    }

}
