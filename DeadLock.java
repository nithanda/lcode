package prep;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {
	private static Lock lock1 = new ReentrantLock();
	private static Lock lock2 = new ReentrantLock();

	public static void main(String[] a) {
		Thread t1 = new Thread(new myThread());
		Thread t2 = new Thread(new myThread2());
		t1.start();t2.start();
		
	}
	
	static class myThread implements Runnable {

		@Override
		public void run() {
			lock1.lock();
			System.out.println("t1 acquired lock1");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("t1 waiting for lock2");
			lock2.lock();
			System.out.println("t1 acquired lock2");
			lock1.unlock();
			lock2.unlock();
		}
		
	}
	
	static class myThread2 implements Runnable {

		@Override
		public void run() {
			lock2.lock();
			System.out.println("t2 acquired lock2");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("t2 waiting for lock1");
			lock1.lock();
			System.out.println("t2 acquired lock2");
			lock1.unlock();
			lock2.unlock();
		}
		
	}
	
}

