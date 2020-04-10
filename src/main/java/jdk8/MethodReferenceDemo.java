package jdk8;

/**
 * JDK8新特性 - 方法引用
 */
public class MethodReferenceDemo {
    public static void main(String[] args) {
        FunctionalInterface functionalInterface = System.out::println;
    }
}
