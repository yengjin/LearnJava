package jvm;

// 对象自我拯救Demo
public class FinalizeDemo {

    public static FinalizeDemo SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("我复活了 :)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize()方法触发了...");
        FinalizeDemo.SAVE_HOOK = this;  // 重点
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeDemo();

        // 对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();    // 有没有被回收?

        // 因为finalize()方法有优先级很低，所以暂停1s以等待他
        Thread.sleep(1000);

        if (null != SAVE_HOOK)
            SAVE_HOOK.isAlive();
        else
            System.out.println("我死了 :(");

        //下面这段代码与上面完全相同，但是这次自救却失败了，因为finalize方法只会被触发一次
        // 对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();

        // 因为finalize()方法有优先级很低，所以暂停1s以等待他
        Thread.sleep(1000);

        if (null != SAVE_HOOK)
            SAVE_HOOK.isAlive();
        else
            System.out.println("我死了 :(");
    }

}
