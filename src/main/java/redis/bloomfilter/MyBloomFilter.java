package redis.bloomfilter;

import java.util.BitSet;

public class MyBloomFilter {

    public static class SimpleHash {
        private int cap;
        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        /**
         * 计算hash值
         * @param value
         * @return
         */
        public int hash(Object value) {
            int h;
            // 类似于HashMap中的扰动和寻址
            return (value == null) ? 0 : Math.abs(seed * (cap - 1) & ((h = value.hashCode()) ^ (h >>> 16)));
        }
    }

    /**
     * 位数组的大小
     */
    private static final int DEFAULT_SIZE = 2 << 24;

    /**
     * 通过这个数组可以创建6个不同的哈希函数
     */
    private static final int[] SEEDS = new int[]{3, 13, 46, 71, 91, 134};

    /**
     * 位数组, 采用BitSet 元素值只能是0或1
     */
    private BitSet bits = new BitSet(DEFAULT_SIZE);

    /**
     * 初始化多个包含hash函数的类的数组, 每个类中的hash函数都不一样
     */
    private SimpleHash[] func = new SimpleHash[SEEDS.length];

    /**
     * 初始化多个含hash函数的数组, 每个类中的hash函数都不一样
     */
    public MyBloomFilter() {
        for (int i = 0; i < SEEDS.length; i++) {
            func[i] = new SimpleHash(DEFAULT_SIZE, SEEDS[i]);
        }
    }

    /**
     * 添加元素到位数组
     */
    public void add(Object value) {
        // 使用每个hash算法进行计算, 并置1
        for (SimpleHash f : func) {
            bits.set(f.hash(value), true);
        }
    }

    /**
     * 判断指定元素是否存在于位数组
     */
    public boolean contains(Object value) {
        for (SimpleHash f : func) {
            if (!bits.get(f.hash(value))) return false;
        }
        return true;
    }

    /**
     * 测试类
     * @param args
     */
    public static void main(String[] args) {
        String value1 = "https://javaguide.cn/";
        String value2 = "https://github.com/Snailclimb";
        MyBloomFilter filter = new MyBloomFilter();
        System.out.println(filter.contains(value1));
        System.out.println(filter.contains(value2));
        filter.add(value1);
        filter.add(value2);
        System.out.println(filter.contains(value1));
        System.out.println(filter.contains(value2));
    }
}
