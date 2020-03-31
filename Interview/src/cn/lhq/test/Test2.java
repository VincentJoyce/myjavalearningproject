package cn.lhq.test;

import java.util.stream.Stream;

public class Test2 {

    private final static Object lock = new Object();

    public static void main(String[] args) {
        Stream.of("线程1","线程2").forEach(n->new Thread(n){
            @Override
            public void run() {
                Test2.testWait();
            }
        }.start());
    }

    private static void testSleep(){

        try {
            Thread.sleep(3000);
            System.out.println("休眠结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testWait(){

        try {
            lock.wait(3000);
            System.out.println("wait结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
