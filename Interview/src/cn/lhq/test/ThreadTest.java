package cn.lhq.test;

import java.util.stream.Stream;

public class ThreadTest {

    private final static Object lock = new Object();

    public static void main(String[] args) {

        Stream.of("线程1", "线程2").forEach(n -> new Thread(n) {

            @Override
            public void run() {
                ThreadTest.testWait();
            }
        }.start());
    }


    private static void testSleep() {

        synchronized (lock) {

            try {
                System.out.println(Thread.currentThread().getName() + "正在执行");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + "休眠结束");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private static void testWait() {

        synchronized (lock) {

            try {
                System.out.println(Thread.currentThread().getName() + "正在执行");
                lock.wait(3000);
                System.out.println(Thread.currentThread().getName() + "wait结束");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}