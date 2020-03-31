package cn.lhq.test;
import java.util.concurrent.CountDownLatch;

class test {
	static CountDownLatch cdl = new CountDownLatch(2);   //①新建一个CountDwonLatch对象并传入计数器的值
	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("thread 1: 1 ");
					Thread.sleep(1000);
					System.out.println("thread 1 : 2");
					cdl.countDown();                   //②在被等待的线程或者步骤执行完毕后调用countDwon（）方法让计数器减1
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("thread 2 : 1");
					Thread.sleep(1000);
					System.out.println("thread 2 : 2");
					cdl.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		cdl.await();                                 //③在等待的主线程中调用await（）方法等待其他线程，知道计数器为0 再执行主线程接下来的逻辑
		System.out.println("thread : main");
	}
}