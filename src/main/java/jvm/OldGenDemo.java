package jvm;

import java.util.concurrent.Executors;

public class OldGenDemo {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        Executors.newFixedThreadPool(5);
        // 小对象
        byte[] allocation1 = new byte[_1MB / 4];
        // 什么时候进入老年代取决于-XX:MaxTenuringThreshold参数
        byte[] allocation2 = new byte[_1MB * 4];
        byte[] allocation3 = null;
        byte[] allocation4 = new byte[_1MB * 4];
        //byte[] allocation5 = new byte[_1MB * 4];
    }
}
