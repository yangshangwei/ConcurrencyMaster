package com.artisan.example.singleton;

import com.artisan.anno.NotThreadSafe;

/**
 * 懒汉模式 单例的实例在第一次调用的时候创建
 * 
 * 对static getInstance方法 进行 双重检测
 * 
 * @author yangshangwei
 *
 */
@NotThreadSafe
public class SingletonLazyModelOptimize2 {

	// 私有构造函数
	// 如果要保证一个类只能被初始化一次，首先要保证的是构造函数是私有的，不允许外部类直接调用new方法
	private SingletonLazyModelOptimize2() {
		// 可以初始化一些资源等
	}

	// static单例对象
	private static SingletonLazyModelOptimize2 instance = null;

	// 静态工厂方法
	// public方法外部通过getInstance获取
	public static  SingletonLazyModelOptimize2 getInstance() {
		// 多线程情况下，假设线程A和线程B同时获取到instance为null, 这时候instance会被初始化两次，所以在判断中加入synchronized
		if (instance == null) {
			// synchronize修饰类 ，修饰范围是synchronized括号括起来的部分，作用于所有对象
			synchronized(SingletonLazyModelOptimize2.class) {
				if (instance == null) {
					instance = new SingletonLazyModelOptimize2();
				}
			}
			
		}
		return instance;
	}

}
