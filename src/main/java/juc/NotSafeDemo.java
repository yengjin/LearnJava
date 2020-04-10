package juc;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

// 聊聊你印象最深刻的故障 - 集合类线程不安全
public class NotSafeDemo {

    public static void main(String[] args) {
        //List<String> list = new Vector<>();
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
