package com.artisan.example.aqs;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JoinTest {

	public static void main(String[] args) throws InterruptedException {
		
		// 线程1 处理sheet A

		Thread t1 = new Thread(() -> {
			try {
				log.info(Thread.currentThread().getName() + " parse sheetA start");
				// 休眠1S，模拟业务耗时
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log.info(Thread.currentThread().getName() + " parse sheetA finish");
		}, "t1");

		
		// 线程2 处理sheet B
		Thread t2 = new Thread(() -> {
			try {
				log.info(Thread.currentThread().getName() + " parse sheetB start");
				// 休眠1S，模拟业务耗时
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log.info(Thread.currentThread().getName() + " parse sheetB finish");
		}, "t2");
		
		
		
		
		// 开启线程
		t1.start();
		t2.start();
		
		// join用于让当前执行线程等待join线程执行结束。其实现原理是不停检查join线程是否存
		// 活，如果join线程存活则让当前线程永远等待。
		t1.join();
		t2.join();
		
		// 直到join线程中止后，线程的this.notifyAll()方法会被调用.
		// 调用notifyAll()方法是在JVM里实现的，JDK里看不到

		log.info("t1 t2 完成，继续其他操作");

	}

}
