package com.artisan.example.singleton;

import lombok.Getter;

public class SingletonEum {

	/**
	 * 私有构造函数
	 */
	private SingletonEum() {
	}

	/**
	 * 静态工厂方法-获取实例
	 *
	 * @return instance
	 */
	public static SingletonEum getInstance() {
		return Singleton.INSTANCE.getInstance();
	}

	/**
	 * 由枚举类创建单例对象
	 */
	
	@Getter
	private enum Singleton {
		INSTANCE;

		/**
		 * 单例对象
		 */
		private SingletonEum instance;

		/**
		 * JVM保证这个方法绝对只调用一次
		 */
		Singleton() {
			instance = new SingletonEum();
		}
	}

}
