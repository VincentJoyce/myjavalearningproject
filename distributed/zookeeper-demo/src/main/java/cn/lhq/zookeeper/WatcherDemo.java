package cn.lhq.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class WatcherDemo {

    public static void main(String[] args) {

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            ZooKeeper zooKeeper = new ZooKeeper("192.168.246.131:2181,192.168.246.132:2181,192.168.246.133:2181",
                    4000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println("默认事件：" + event.getType());
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
            //如果zooKeeper.exists(path:"/zk-persis-lhq",watch:true)中watch为true,则说明会调用new ZooKeeper(,,watch)中的watch方法
            //如果要自定义watcher则如下：
            //通过exists绑定watcher事件
            Stat stat = zooKeeper.exists("/zk-persis-lhq", new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println(event.getType() + "->" + event.getPath());
                    try {
                        //watch:true仍然是调用new ZooKeeper()参数的watch事件
                        zooKeeper.exists("/zk-persis-lhq",true);
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            //通过修改的事务类型操作来触发监听事件
            stat = zooKeeper.setData("/zk-persis-lhq", "2".getBytes(), stat.getVersion());
            Thread.sleep(1000);
            zooKeeper.delete("/zk-persis-lhq", stat.getVersion());

            //zooKeeper.close();
            System.in.read();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
