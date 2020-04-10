package jdk8;

interface MyInterface {
    void handle();
    default void defaultMethod() {
        System.out.println("default method...");
    }
}
// 只需要实现非default
public class DefaultInterfaceDemo implements MyInterface {
    @Override
    public void handle() {
        System.out.println("Hello");
    }

}
