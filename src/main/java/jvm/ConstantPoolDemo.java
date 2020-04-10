package jvm;
// 运行时常量池
public class ConstantPoolDemo {

    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "abc";
        System.out.println(s1 == s2);

        ConstantPoolDemo constantPoolDemo = new ConstantPoolDemo();
        constantPoolDemo.getClass().getName();
    }
}
