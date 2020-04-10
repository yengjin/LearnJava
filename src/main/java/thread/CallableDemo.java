package thread;


import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

class MyThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("************come in Callable");
        return 1024;
    }
}

public class CallableDemo {

    public static void main(String[] args) throws Exception {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());


        new Thread(futureTask, "AA").start();

        System.out.println(Thread.currentThread().getName() + "**********");
        int result01 = 100;
        int result02 = 0;
        while (!futureTask.isDone()) {
            result02 = futureTask.get();    // 建议放在最后
        }
        System.out.println("******result:" + (result01 + result02));

    }
}
