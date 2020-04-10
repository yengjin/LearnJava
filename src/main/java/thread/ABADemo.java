package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题的解决方案 - 时间戳原子引用
 */
public class ABADemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);
    public static void main(String[] args) {

        System.out.println("========以下是ABA问题的产生========");
        new Thread(() -> {
            // 模拟ABA
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "t1").start();

        new Thread(() -> {
            // 暂停一会t2线程, 保证t1线程先执行完ABA
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            // 模拟ABA
            System.out.println(atomicReference.compareAndSet(100, 2020) + "\t" + atomicReference.get());
        }, "t2").start();

        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("========以下是ABA问题的解决========");
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();  // 获取初始版本号1
            System.out.println(Thread.currentThread().getName() + "\t第1次版本号: " + stamp);

            // 暂停一会t3线程, 保证t4获取版本号
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t第2次版本号: " + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t第3次版本号: " + atomicStampedReference.getStamp());
        }, "t3").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();  // 获取初始版本号1
            System.out.println(Thread.currentThread().getName() + "\t第1次版本号: " + stamp);

            // 暂停3秒钟t4线程, 保证t3能做完ABA
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            boolean result = atomicStampedReference.compareAndSet(100, 2020, stamp, stamp+1);

            System.out.println(Thread.currentThread().getName() + "\t修改成功否: " + result + "\t当前最新实际版本号:" + atomicStampedReference.getStamp());

            System.out.println(Thread.currentThread().getName() + "\t当前最新实际值: " + atomicStampedReference.getReference());

        }, "t4").start();
    }

}
