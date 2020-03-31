package cn.lhq.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 数据连接、增、删、改、查
 */
public class ConnectionDemo {

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            ZooKeeper zooKeeper = new ZooKeeper("192.168.246.131:2181,192.168.246.132:2181,192.168.246.133:2181",
                    4000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (Event.KeeperState.SyncConnected == event.getState()) {
                        //如果收到了服务端响应事件，连接成功
                        countDownLatch.countDown();
                    }
                }
            });
            countDownLatch.await();
            System.out.println(zooKeeper.getState());//CONNECTED表示连接成功

            //添加节点
            zooKeeper.create("/zk-persis-lhq", "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            Thread.sleep(1000);
            Stat stat = new Stat();

            //得到当前节点的值
            byte[] bytes = zooKeeper.getData("/zk-persis-lhq", null, stat);
            System.out.println(new String(bytes));

            //修改节点值
            zooKeeper.setData("/zk-persis-lhq", "1".getBytes(), stat.getVersion());

            //得到当前节点的值
            byte[] bytes1 = zooKeeper.getData("/zk-persis-lhq", null, stat);
            System.out.println(new String(bytes1));

            //删除节点
            zooKeeper.delete("/zk-persis-lhq", stat.getVersion());

            zooKeeper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
