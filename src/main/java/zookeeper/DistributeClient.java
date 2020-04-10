package zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 模拟客户端
 * 需求: 负责一直监听并接收客户端上下线信息
 * create -e -s /servers/server "zkOS1"
 *
 * ZooKeeper监听原理:
 * 1. main线程中创建ZK客户端, 会创建两个线程(connect负责网络连接通信, listener负责监听)
 * 2. 通过connect线程将注册的监听事件发送给ZK, 如: getChildren("/", true)
 * 3. ZK将监听事件注册到ZK监听器列表中, 并监听.
 * 4. ZK监听到有数据或路径变化, 会将这个消息发送给listener线程.
 * 5. listener线程内部调用了process方法.
 *
 * 常见监听:
 * 1) 监听节点数据变化 get path[watch]
 * 2) 监听子节点增减的变化(也叫监听路径) ls path[watch]
 */
public class DistributeClient {

    private String connectString="zkOS1:2181,zkOS2:2181,zkOS3:2181";
    private int sessionTimeout = 2000;  // Session超时时间2s
    private ZooKeeper zkClient;

    public static void main(String[] args) throws Exception {

        DistributeClient client = new DistributeClient();

        // 1. 获取zookeeper集群连接
        client.getConnect();
        // 2. 注册监听
        client.getChildren();
        // 3. 业务逻辑处理
        client.business();
    }

    // 防止线程提前结束
    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }

    private void getChildren() throws KeeperException, InterruptedException {

        // 获取所有/servers下的路径, 并进行监听
        List<String> children = zkClient.getChildren("/servers", true);

        // 存储服务器节点主机名称集合
        ArrayList<String> hosts = new ArrayList<>();

        // 尝试获取已上线的服务器名称(也就是/servers/server01的值, 拼接)
        for (String child : children) {
            byte[] data = zkClient.getData("/servers/" + child, false, null);

            // 添加已经上线的服务器名称至已上线ArrayList
            hosts.add(new String(data));
        }

        // 将所有在线主机名称打印到控制台
        System.out.println(hosts);
    }

    // 获取zookeper集群连接
    private void getConnect() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {

            /**
             * 为什么要再写到Watcher里一次?
             * 因为watch监听操作只是**一次性**的.
             * 在getChildren()方法第一次开启后, 随后监听回调process, 继续调用getChildren开启监听, 死循环(不断监听上下线)
             * @param event
             */
            @Override
            public void process(WatchedEvent event) {
                try {
                    getChildren();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
