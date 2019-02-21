package com.artisan.example.immutable;

import com.artisan.anno.ThreadSafe;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;


@ThreadSafe
public class GuavaImmutableSetDemo {

	// 使用Guava中提供的类来定义不可变对象的集合
	
	// 不可变list
	private final static ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);
	// 不可变的set
	private final static ImmutableSet<Integer> set = ImmutableSet.copyOf(list);
	// 不可变的map，需要以k/v的形式传入数据，即奇数位参数为key，偶数位参数为value
	private final static ImmutableMap<Integer, Integer> map = ImmutableMap.of(1, 2, 3, 4);
	// 通过builder调用链的方式构造不可变的map
	private final static ImmutableMap<Integer, Integer> map2 = ImmutableMap.<Integer, Integer>builder()
																			.put(1, 2)
																			.put(3, 4)
																			.put(5, 6)
																			.build();

	public static void main(String[] args) {
		
		// 修改对象内的数据就会抛出UnsupportedOperationException异常
		
		// 不能添加新的元素 ，运行将抛出 java.lang.UnsupportedOperationException
		list.add(4);

		// 不能添加新的元素 ，运行将抛出 java.lang.UnsupportedOperationException
		set.add(4);

		// 不能添加新的元素 ，运行将抛出 java.lang.UnsupportedOperationException
		map.put(5, 6);

		// 不能添加新的元素 ，运行将抛出 java.lang.UnsupportedOperationException
		map2.put(1, 3);
	}
}
