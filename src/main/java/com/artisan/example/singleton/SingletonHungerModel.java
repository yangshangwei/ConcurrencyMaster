package com.artisan.example.singleton;

import com.artisan.anno.ThreadSafe;

/**
  *  饿汉模式 单例的实例在类装载的时候进行创建
 * 
  *  因为是在类装载的时候进行创建，可以确保线程安全
 * 
 * 
 * 饿汉模式需要注意的地方： 1.私有构造函数中不要有太多的逻辑，否则初始化会慢   2.确保初始化的对象能够被使用，否则造成资源浪费
 * 
 * @author yangshangwei
 *
 */
@ThreadSafe
public class SingletonHungerModel {

	// 私有构造函数
	// 如果要保证一个类只能被初始化一次，首先要保证的是构造函数是私有的，不允许外部类直接调用new方法
	private SingletonHungerModel() {
		// 可以初始化一些资源等
	}

	// static单例对象  静态域
	private static SingletonHungerModel instance = new SingletonHungerModel();

	// public方法外部通过getInstance获取
	public static SingletonHungerModel getInstance() {
		// 直接返回实例化后的对象
		return instance;
	}

}
