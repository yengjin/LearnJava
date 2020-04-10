package algorithm.datastructure;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 实现LRU算法, 继承LinkedHashMap
 * @param <K>
 * @param <V>
 */
public class LRUCache<K,V> extends LinkedHashMap<K,V> {
    private int CACHE_SIZE;

    public LRUCache(int cacheSize) {
        // 调用父类LinkedHashMap的构造器, 需要向上取整并倒推capacity, 防止触发扩容
        // LinkedHashMap(int initialCapacity,float loadFactor,boolean accessOrder)
        super((int)Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        this.CACHE_SIZE = cacheSize;
    }

    // 每次添加都会检查, 所以等size超过了我们指定容量后, 再删除
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return (size() > CACHE_SIZE);
    }
}
