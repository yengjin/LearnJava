package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁Demo
 * 题目: 实现一个自旋锁
 * 自旋锁好处: 循环获取锁, 直到成功为止. 没有类似wait阻塞
 *
 * 通过CAS操作完成自旋锁. A线程先进来调用mylock方法持有锁5秒钟, B随后进来发现
 * 当前线程持有锁, 不是null, 所以只能通过自旋等待, 直到A释放锁后B抢到.
 * AA	 come in   此时AA线程获取锁, 并休眠5秒
 * BB	 come in   此时BB线程一直自旋
 * AA	 invoked myUnlock()  此时AA线程释放资源
 * BB	 invoked myUnlock()  此时BB线程获得资源
 */
public class SpinLockDemo {
    // 原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    // 解锁
    public void myUnlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t invoked myUnlock()");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            spinLockDemo.myLock();
            // 暂停
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLockDemo.myUnlock();
        }, "AA").start();

        // 保证AA线程先执行
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        // BB线程一直尝试, 直到AA线程调用unlock方法
        new Thread(() -> {
            spinLockDemo.myLock();
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLockDemo.myUnlock();
        }, "BB").start();
    }
}
