package cn.lhq.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class CuratorDemo {

    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("192.168.246.131:2181,192.168.246.132:2181,192.168.246.133:2181")
                .sessionTimeoutMs(4000).retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace("curator").build();
        curatorFramework.start();

        //结果：/curator/lhq/node1
        //原生api中，必须是逐层创建，也就是父节点必须存在，子节点才能创建
        //新增
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/lhq/node1", "1".getBytes());
        Stat stat = new Stat();
        //查询
        curatorFramework.getData().storingStatIn(stat).forPath("/lhq/node1");
        //修改
        curatorFramework.setData().withVersion(stat.getVersion()).forPath("/lhq/node1","xx".getBytes());
        //删除
        curatorFramework.delete().deletingChildrenIfNeeded().forPath("/lhq/node1");


    }
}
