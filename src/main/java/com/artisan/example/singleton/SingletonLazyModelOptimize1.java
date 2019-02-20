package com.artisan.example.singleton;

import com.artisan.anno.ThreadSafe;

/**
 * 懒汉模式 单例的实例在第一次调用的时候创建
 * 
 * 将static getInstance方法修改为 同步方法 ----> 线程安全
 * 
 * 众所周知synchronized是线程阻塞的，效率较低
 * 
 * @author yangshangwei
 *
 */
@ThreadSafe
public class SingletonLazyModelOptimize1 {

	// 私有构造函数
	// 如果要保证一个类只能被初始化一次，首先要保证的是构造函数是私有的，不允许外部类直接调用new方法
	private SingletonLazyModelOptimize1() {
		// 可以初始化一些资源等
	}

	// static单例对象
	private static SingletonLazyModelOptimize1 instance = null;

	// 静态工厂方法
	// public方法外部通过getInstance获取
	public static synchronized SingletonLazyModelOptimize1 getInstance() {

		// 多线程情况下，假设线程A和线程B同时获取到instance为null, 这时候instance会被初始化两次
		if (instance == null) {
			instance = new SingletonLazyModelOptimize1();
		}
		return instance;
	}

}
