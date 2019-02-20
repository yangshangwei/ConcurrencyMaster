package com.artisan.example.publish;


import com.artisan.anno.NotThreadSafe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
public class UnSafePublishObjectDemo {

	// 私有变量
	private String name = "artisan";
	
	// 通过public访问级别的方法getName发布了类的域，在类的外部，任何线程都可以访问这个域
	// 这样发布的对象是不安全的，因为我们无法得知其他线程是否会修改这个域导致该类里数据的错误
	public String  getName() {
		return name;
	}
	
	
	public static void main(String[] args) {
		
		// 通过new实例化UnSafePublishObjectDemo
		UnSafePublishObjectDemo unSafePublishObjectDemo = new UnSafePublishObjectDemo();
		// 调用getName()方法得到私有属性的引用
		String name = unSafePublishObjectDemo.getName();
		log.info("name:{}",name);
		
		// 假设有第二个线程去修改name属性的值
		String name2 = unSafePublishObjectDemo.getName();
		name2 = "小工匠";
		log.info("name:{}",name2);
		
	}
	
}
