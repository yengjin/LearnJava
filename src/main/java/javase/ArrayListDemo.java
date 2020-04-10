package javase;

import java.util.*;

public class ArrayListDemo {

    public static void main(String[] args) {

        //List<String> list = new ArrayList<>();
        //List<String> list = new Vector<>();

        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        //List<String> list = new CopyOnWriteArrayList<>();

        //Set<String> set = new HashSet<String>();
        //Set<String> set = Collections.synchronizedSet(new HashSet<>());
        //Set<String> set = new CopyOnWriteArraySet<>();

        Map<String, String> map = new HashMap<>();
        //Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 1; i <= 100; i++) {
            new Thread(() -> {
                //map.add(UUID.randomUUID().toString().substring(0, 8));
                map.put(UUID.randomUUID().toString().substring(0, 8), "b");
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
