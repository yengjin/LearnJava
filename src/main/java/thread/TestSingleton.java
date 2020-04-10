package thread;

/**
 * 手写单例模式
 * 1. 饿汉式
 * 2. **懒汉式**
 */
public class TestSingleton {

    private volatile static TestSingleton instance = null;

    public static TestSingleton getInstance() {
        if (instance == null) {
            synchronized (TestSingleton.class) {
                if (instance == null) {
                    instance = new TestSingleton();
                    // memory = allocate();     分配内存空间
                    // instance = memory;       引用赋值
                    // init(memory);            初始化
                }
            }
        }
        return instance;
    }

    private TestSingleton() {

    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 1000; i++) {
            new Thread(() -> {
                TestSingleton.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
