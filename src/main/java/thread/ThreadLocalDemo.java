package thread;

public class ThreadLocalDemo {

    // 也可以使用ThreadLocal.withInitial()
    private static final ThreadLocal<Integer> local = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 100;
        }
    };
    public static void main(String[] args) {


        for (int i = 1; i <= 10; i++) {
            final int t = i;
            new Thread(() -> {
                local.set(t);     // 线程改成200
                System.out.println(Thread.currentThread().getName() + "\t myData: " + local.get()); // 这里打印都是各自的值
            }, String.valueOf(i)).start();
        }

        System.out.println(Thread.currentThread().getName() + "\t myData: " + local.get()); // 这里打印还是100

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
    }
}
