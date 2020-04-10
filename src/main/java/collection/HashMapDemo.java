package collection;

import java.util.HashMap;
import java.util.Hashtable;

public class HashMapDemo {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();  // capacity = 16
        // table数组 = null
        map.put("A", "123");    // 第一次put
        map.put("A", "456");    // 第一次put


        System.out.println("vsdvdsvs".hashCode());
    }
}