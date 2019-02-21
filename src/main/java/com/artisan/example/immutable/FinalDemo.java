package com.artisan.example.immutable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FinalDemo {

	// 基本数据类型 int 使用final修饰 验证被final修饰的基本数据类型无法改变
	private final static int num = 1;

	// String类型 使用final修饰 验证被final修饰的基本数据类型无法改变
	private final static String name = "小工匠";

	// 引用类型 初始化之后不能再修改其引用，但是可修改其中值
	private final static Map<String, Object> map = Maps.newHashMap();

	static {
		map.put("name", "artisan");
		map.put("age", 20);
		map.put("sex", "男");
	}

	public static void main(String[] args) {

		// 被final修饰的基本数据类型和String无法改变
		// 编译报错： The final field FinalDemo.num cannot be assigned
		// num = 2;
		// 编译报错： The final field FinalDemo.name cannot be assigned
		// name = "artisan";

		// 引用对象，此引用无法指向别的对象，但可修改该对象的值
		map.put("name", "小工匠");
		log.info("name:{}", map.get("name"));
		
		
		// 验证 方法参数被final修饰的情况
		List<String> list = new ArrayList<>();
		list.add("我是小工匠");
		test2(list);
		
	}

	// final修饰传递进来的变量基本类型,不可别改变
	private void test(final int a) {
		// 不能修改
		// 编译报错： The final local variable a cannot be assigned. It must be blank and not using a compound assignment
		// a = 2;
		log.info("a:{}", a);
	}

	// final修饰方法传递进来的变量 引用对象,无法指向别的对象，但可修改该对象的值
	private static void test2(final List<String> list) {
		// 添加数据
		list.add("我是artisan");
		list.forEach(str ->{
			log.info("数据:{}",str);
		});
	}

}
