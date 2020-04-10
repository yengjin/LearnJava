package jdk8;

@java.lang.FunctionalInterface
interface FunctionalInterface {
    void myFunction();
}
/**
 * JDK8新特性 - 函数式接口
 * 只有一个抽象方法的接口(default可以很多个)
 */
public class FunctionalInterfaceDemo {
    public static void main(String[] args) {
        // 只有一个抽象方法就可以像下面这样方便的调用
        FunctionalInterface functionalInterface = () -> System.out.println("Hello");
        // 否则就要这样使用匿名类, 很麻烦
        FunctionalInterface functionalInterface1 = new FunctionalInterface() {
            @Override
            public void myFunction() {
                System.out.println("Hello1");
            }
        };
        functionalInterface.myFunction();
        functionalInterface1.myFunction();
    }
}
