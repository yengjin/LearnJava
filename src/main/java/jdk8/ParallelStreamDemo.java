package jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 并行流, 多线程完成Stream相关操作
 */
public class ParallelStreamDemo {
    public static void main(String[] args) {
        // 创建一个没有重复元素的列表
        int max = 1000000;
        List<String> values = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        // 串行排序 - Stream
        // 709ms
        long count = values.stream().sorted().count();
        System.out.println(count);

        // 并行排序 - Parallel Sort
        // 475ms
        long count1 = values.parallelStream().sorted().count();
        System.out.println(count);
    }
}
