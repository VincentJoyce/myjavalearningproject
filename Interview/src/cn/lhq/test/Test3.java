package cn.lhq.test;

import java.util.stream.Stream;

public class Test3 {

    private final static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                Test3.testWait();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                Test3.notifyWait();
            }
        }.start();
    }

    private static void testWait(){

        synchronized (lock){
            try {
                System.out.println("我一直在等待");
                lock.wait();
                System.out.println("wait被唤醒了");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void notifyWait(){
        synchronized (lock){
            try {
                Thread.sleep(5000);
                lock.notify();
                System.out.println("休眠5秒钟唤醒wait");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
