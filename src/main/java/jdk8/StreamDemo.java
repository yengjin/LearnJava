package jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * JDK1.8新特性 - Stream流
 */
public class StreamDemo {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("ddd2");
        stringList.add("aaa2");
        stringList.add("bbb1");
        stringList.add("aaa1");
        stringList.add("bbb3");
        stringList.add("ccc");
        stringList.add("bbb2");
        stringList.add("ddd1");

        // Filter过滤
        stringList.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);

        // Sorted排序, 创建一个排好序的Stream, 源数据不会被修改
        stringList.stream().sorted().filter((s) -> s.startsWith("a")).forEach(System.out::println);

        // Map映射, 根据指定的Function接口, 转换成其他类型
        stringList
                .stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> b.compareTo(a))
                .forEach(System.out::println);  // "DDD2", "DDD1", "CCC", "BBB3", "BBB2", "AAA2", "AAA1"

        // Match匹配, 检测指定的断言Predicate是否匹配整个Stream, 所有的匹配操作都是**最终操作**, 返回一个boolean
        boolean anyStartsWithA =
                stringList
                    .stream()
                    .anyMatch((s) -> s.startsWith("a"));
        System.out.println(anyStartsWithA);     // true

        boolean allStartsWithA =
                stringList
                        .stream()
                        .allMatch((s) -> s.startsWith("a"));
        System.out.println(allStartsWithA); // false

        boolean noneStartsWithZ =
                stringList
                        .stream()
                        .noneMatch((s) -> s.startsWith("a"));
        System.out.println(noneStartsWithZ);    // true


        // Count计数, 返回Stream中元素个数也是最终操作
        long startsWithB =
                stringList
                        .stream()
                        .filter((s) -> s.startsWith("b"))
                        .count();
        System.out.println(startsWithB);    // 3

        // Reduce规约, 将多个元素规约为一个元素, 规约后结果通过Optional接口表示, 最终操作
        /* 这个方法的主要作用是把 Stream 元素组合起来。它提供一个起始值（种子），
        然后依照运算规则（BinaryOperator），和前面 Stream 的第一个、第二个、第 n 个元素组合。
        从这个意义上说，字符串拼接、数值的 sum、min、max、average 都是特殊的 reduce。
        例如 Stream 的 sum 就相当于Integer sum = integers.reduce(0, (a, b) -> a+b);也有没有起始值的情况，
        这时会把 Stream 的前面两个元素组合起来，返回的是 Optional。
        * */
        Optional<String> reduced =
                stringList
                        .stream()
                        .sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println); //aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2

        // 更多Reduce的例子, 有起始值(第一个参数), 返回具体值, 无起始值会返回Optional对象

        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F").
                filter(x -> x.compareTo("Z") > 0).
                reduce("", String::concat);

    }
}
