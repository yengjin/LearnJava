package collection;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

/** Unsafe**/
public class UnsafeDemo {

    private int i = 0;

    private static Unsafe UNSAFE = null;
    private static long I_OFFSET;

    static {

        try {
            // 只有通过bootstrap加载的才能直接调getUnsafe()获取UNSAFE
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);
            //UNSAFE = Unsafe.getUnsafe();
            I_OFFSET = UNSAFE.objectFieldOffset(UnsafeDemo.class.getDeclaredField("i"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final UnsafeDemo unsafeDemo = new UnsafeDemo();

        new Thread(() -> {
            while (true) {
                //unsafeDemo.i++;
                boolean b = UNSAFE.compareAndSwapInt(unsafeDemo, I_OFFSET, unsafeDemo.i, unsafeDemo.i + 1);

                if (b) {
                    // System.out.println(unsafeDemo.i);
                    System.out.println(UNSAFE.getIntVolatile(unsafeDemo, I_OFFSET));
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();


        new Thread(() -> {
            while (true) {
                //unsafeDemo.i++;

                boolean b = UNSAFE.compareAndSwapInt(unsafeDemo, I_OFFSET, unsafeDemo.i, unsafeDemo.i + 1);

                if (b) {
                    //System.out.println(unsafeDemo.i);
                    System.out.println(UNSAFE.getIntVolatile(unsafeDemo, I_OFFSET));
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();


    }
}
