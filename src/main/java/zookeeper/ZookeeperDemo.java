package zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

// 测试Zookeeper客户端连接
public class ZookeeperDemo {
    private String connectString="zkOS1:2181,zkOS2:2181,zkOS3:2181";
    private int sessionTimeout = 2000;  // Session超时时间2s
    private ZooKeeper zkClient;
    @Before
    public void init() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {

            // ZooKeeper监听器检测到变化后回调客户端的process方法进行处理
            @Override
            public void process(WatchedEvent event) {
                /*System.out.println("-----------start------------");
                List<String> children = null;
                try {
                    children = zkClient.getChildren("/", false);
                    for (String child : children) {
                        System.out.println(child);
                    }

                    System.out.println("-----------end------------");
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        });
    }

    // 1. 创建节点
    @Test
    public void createNode() throws KeeperException, InterruptedException {

        // 直接执行会报错, 需要把连接函数设置为@Before
        // 节点路径  节点值  权限  是否为持久节点
        String path = zkClient.create("/geek51", "BruceYan".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        System.out.println(path);
    }

    // 2. 获取子节点
    @Test
    public void getData() throws KeeperException, InterruptedException {

        List<String> children = zkClient.getChildren("/", false);

        for (String child : children) {
            System.out.println(child);
        }
    }

    // 3. 获取子节点并监控节点的变化
    @Test
    public void getDataAndWatch() throws KeeperException, InterruptedException {

        // 设置为true, 表示开启监听
        List<String> children = zkClient.getChildren("/", true);

        // ======GET获取到的数据=======
        System.out.println("======GET获取到的数据=======");
        for (String child : children) {
            System.out.println(child);
        }
        System.out.println("======GET获取到的数据=======");
        // ======GET获取到的数据=======
        // 永久睡眠, 防止主线程提前结束
        Thread.sleep(Long.MAX_VALUE);
    }

    // 3. 判断节点是否存在
    @Test
    public void exist() throws KeeperException, InterruptedException {
        // 执行后返回节点状态数据Stat
        Stat stat = zkClient.exists("/geek51", false);

        System.out.println(stat == null ? "not exist" : "exist");
    }
}
