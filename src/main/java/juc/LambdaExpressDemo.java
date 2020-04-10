package juc;

interface Foo {
    public void sayHello();
}
public class LambdaExpressDemo {

    public static void main(String[] args) {
        Foo foo = new Foo() {
            @Override
            public void sayHello() {

            }
        };
    }
}
