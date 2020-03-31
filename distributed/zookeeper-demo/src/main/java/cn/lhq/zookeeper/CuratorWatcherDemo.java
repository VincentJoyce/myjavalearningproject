package cn.lhq.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorWatcherDemo {

    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("192.168.246.131:2181,192.168.246.132:2181,192.168.246.133:2181")
                .sessionTimeoutMs(4000).retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace("curator").build();
        curatorFramework.start();

        addListenerWithNodeCache(curatorFramework, "/lhq");
        System.in.read();
    }


    /**
     * PathChildrenCache：监听一个节点下子节点的创建、删除、更新
     * @param curatorFramework
     * @param path
     * @throws Exception
     */
    public static void addListenerWithPathChildCache(CuratorFramework curatorFramework, String path) throws Exception {
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, path, false);
        PathChildrenCacheListener nodeCacheListener = new PathChildrenCacheListener() {

            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                System.out.println("Receive Event:" + pathChildrenCacheEvent.getType());
            }
        };
        pathChildrenCache.getListenable().addListener(nodeCacheListener);
        pathChildrenCache.start();
    }

    /**
     * NodeCache：监听一个节点的更新和创建事件
     * @param curatorFramework
     * @param path
     * @throws Exception
     */
    public static void addListenerWithNodeCache(CuratorFramework curatorFramework, String path) throws Exception {
        NodeCache nodeCache = new NodeCache(curatorFramework, path, false);
        NodeCacheListener nodeCacheListener = new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("Receive Event:" + nodeCache.getCurrentData().getPath());
            }
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start();
    }

    /**
     * TreeCache：综合PathChildrenCache和NodeCache的特性
     * @param curatorFramework
     * @param path
     * @throws Exception
     */
    public static void addListenerWithTreeCache(CuratorFramework curatorFramework, String path) throws Exception {
        TreeCache treeCache = new TreeCache(curatorFramework, path);
        TreeCacheListener nodeCacheListener = new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
                System.out.println(treeCacheEvent.getType() + "->" + treeCacheEvent.getData().getPath());
            }
        };
        treeCache.getListenable().addListener(nodeCacheListener);
        treeCache.start();
    }
}
