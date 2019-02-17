package com.artisan.example.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SynchronizedDemo {

	// 修饰方法 谁调用该方法synchronized就对谁起作用 即作用于调用的对象 。 如果是不同的对象，则互不影响
	public synchronized void test2(String flag) {
		// 修饰代码块 ，
		for (int i = 0; i < 10; i++) {
			log.info("{} 调用 修饰方法 i = {} ",flag, i);
		}
	}

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newCachedThreadPool();

		// 同一个对象 synchronizedDemo
		SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
		
		
		// 开启一个线程
		executorService.execute(() ->{
			synchronizedDemo.test2("同一个对象synchronizedDemo");
		});
		
		// 开启第二个线程
		executorService.execute(() ->{
			synchronizedDemo.test2("同一个对象synchronizedDemo");
		});

		// 最后 关闭线程池
		executorService.shutdown();
	}
}