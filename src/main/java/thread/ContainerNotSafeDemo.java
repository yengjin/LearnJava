package thread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合类不安全的问题
 * ArrayList
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) {

        //Map<String, String> map = new HashMap<>(); -- 线程不安全
        //Map<String, String> map = Collections.synchronizedMap(new HashMap<>()); -- 线程安全
        Map<String, String> map = new ConcurrentHashMap<>(); //线程安全

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }

    }


    public static void setNotSafe() {
        //Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }

    }

    // List线程安全相关
    public static void listNotSafe() {
        //List<String> list = new ArrayList<>();
        //List<String> list = new Vector<>();
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();


        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        // java.util.ConcurrentModificationException  (要记住)

        /**
         * 1. 故障现象
         *      java.util.ConcurrentModificationException
         * 2. 导致原因
         *      并发争抢修改导致, 举例: 向花名册进行签名
         *      一个人正在写入, 另一个同学过来抢夺, 导致数据不一致异常.并发修改异常.
         * 3. 解决方案
         *      3.1 使用Vector
         *      3.2 Collections.synchronizedList(new ArrayList<>());
         *      3.3 new CopyOnWriteArrayList<>();
         * 4. 优化建议 (同样错误不犯第二次)
         */
    }

}
