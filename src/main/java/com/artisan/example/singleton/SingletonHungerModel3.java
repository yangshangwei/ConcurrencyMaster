package com.artisan.example.singleton;

import com.artisan.anno.ThreadSafe;

/**
 * 饿汉模式 单例的实例在类装载的时候进行创建
 * 
 * 使用静态内部类实现的单例模式-线程安全
 * 
 * 
 * 饿汉模式需要注意的地方： 1.私有构造函数中不要有太多的逻辑，否则初始化会慢 2.确保初始化的对象能够被使用，否则造成资源浪费
 * 
 * @author yangshangwei
 *
 */
@ThreadSafe
public class SingletonHungerModel3 {

	// 私有构造函数
	// 如果要保证一个类只能被初始化一次，首先要保证的是构造函数是私有的，不允许外部类直接调用new方法
	private SingletonHungerModel3() {
		// 可以初始化一些资源等
	}

	// 静态工厂方法-获取实例
	public static SingletonHungerModel3 getInstance() {
		// 直接返回实例化后的对象
		return InstanceHolder.INSTANCE;
	}

	// 用静态内部类创建单例对象   private 修饰
	private static class InstanceHolder {
		private static final SingletonHungerModel3 INSTANCE = new SingletonHungerModel3();
	}

}
