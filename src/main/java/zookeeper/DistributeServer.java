package zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * 模拟服务器
 * 需求: 模拟进行上下线, 注册临时节点至集群
 */
public class DistributeServer {
    private String connectString="zkOS1:2181,zkOS2:2181,zkOS3:2181";
    private int sessionTimeout = 2000;  // Session超时时间2s
    private ZooKeeper zkClient;

    public static void main(String[] args) throws Exception {

        DistributeServer server = new DistributeServer();
        // 1 连接zookeeper集群
        server.getConnect();

        // 2 注册(创建)节点, 从参数中获取主机名
        server.regist(args[0]);

        // 3 业务逻辑处理
        server.business();
    }

    // 需要用**带编号的临时节点**创建 EPHEMERAL_SEQUENTIAL, 服务器下线时会自动把创建的节点删除, 代表下线
    private void regist(String hostname) throws KeeperException, InterruptedException {

        String path = zkClient.create("/servers/server", hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        // 向集群创建成功(一共就一次), 就代表已经伤心啊
        System.out.println(hostname + "上线了...");
    }

    private void getConnect() throws IOException {

        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {

            }
        });
    }






    // 防止进程提前结束
    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }


}
