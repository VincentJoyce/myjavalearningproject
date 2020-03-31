package cn.lhq.distributedlock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class DistributedLock implements Lock, Watcher {

    private ZooKeeper zk = null;
    private String ROOT_LOCK = "/locks";//定义根节点
    private String WAIT_LOCK;//等待前一个锁
    private String CURRENT_LOCK;//表示当前的锁

    private CountDownLatch countDownLatch;


    public DistributedLock() {
        try {
            zk = new ZooKeeper("192.168.246.132", 4000, this);
            //判断根节点是否存在
            Stat stat = zk.exists(ROOT_LOCK, false);
            if (stat == null) {
                //创建根节点
                zk.create(ROOT_LOCK, "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lock() {
        //如果获得锁成功
        if (this.tryLock()) {
            System.out.println(Thread.currentThread().getName() + "->" + CURRENT_LOCK + "->获得锁成功");
            return;
        }
        try {
            waitForLock(WAIT_LOCK);//没有获得锁，继续等待获得锁
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void waitForLock(String prev) {
        try {
            Stat stat = zk.exists(prev, true);
            if (stat != null) {
                System.out.println(Thread.currentThread().getName() + "->等待锁" + ROOT_LOCK + "/" + prev + "释放");
                countDownLatch = new CountDownLatch(1);
                countDownLatch.await();
                System.out.println(Thread.currentThread().getName() + "->获得锁成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {

        try {
            CURRENT_LOCK = zk.create(ROOT_LOCK + "/", "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(Thread.currentThread().getName() + "->" + CURRENT_LOCK + ",尝试竞争锁");
            //获取根节点下的所有的子节点
            List<String> childrens = zk.getChildren(ROOT_LOCK, false);
            //定义一个集合进行排序
            SortedSet<String> sortedSet = new TreeSet();
            for (String children : childrens) {
                sortedSet.add(ROOT_LOCK + "/" + children);
            }
            //获得当前所有子节点中最小的节点
            String firstNode = sortedSet.first();
            SortedSet<String> lessThenMe = sortedSet.headSet(CURRENT_LOCK);
            if (CURRENT_LOCK.equals(firstNode)) {//通过当前的节点和子节点最小的节点进行比较，如果相等，表示获取锁成功
                return true;
            }
            if (!lessThenMe.isEmpty()) {
                //获得比当前节点更小的最后一个节点，设置给WAIT_LOCK
                WAIT_LOCK = lessThenMe.last();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        System.out.println(Thread.currentThread().getName() + "->释放锁" + CURRENT_LOCK);
        try {
            zk.delete(CURRENT_LOCK, -1);
            CURRENT_LOCK = null;
            zk.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (this.countDownLatch!=null){
            this.countDownLatch.countDown();
        }
    }
}
