package com.artisan.example.synContainer;

import java.util.Iterator;
import java.util.Vector;

/**
 * 
 * @author yangshangwei
 * 
 *         当我们使用foreach循环或迭代器去遍历元素的同时又执行删除操作的话，即便在单线程下也会报并发修改异常.
 * 
 *         建议使用for循环
 *
 */
public class VectorExample3 {
	// 使用foreach
	// 在遍历的同时进行了删除的操作，会抛出java.util.ConcurrentModificationException并发修改异常
	private static void test1(Vector<Integer> v1) {
		for (Integer i : v1) {
			if (i.equals(3)) {
				v1.remove(i);
			}
		}
	}

	// 使用iterator
	// 在遍历的同时进行了删除的操作，会抛出java.util.ConcurrentModificationException并发修改异常
	private static void test2(Vector<Integer> v1) {
		Iterator<Integer> iterator = v1.iterator();
		while (iterator.hasNext()) {
			Integer i = iterator.next();
			if (i.equals(3)) {
				v1.remove(i);
			}
		}
	}

	// 使用for
	// success
	private static void test3(Vector<Integer> v1) {
		for (int i = 0; i < v1.size(); i++) {
			if (v1.get(i).equals(3)) {
				v1.remove(i);
			}
		}
	}
	
	
	// 所以在foreach循环或迭代器遍历的过程中不能做删除操作，若需遍历的同时进行删除操作的话尽量使用for循环。
	// 实在要使用foreach循环或迭代器的话应该先标记要删除元素的下标，然后最后再统一删除
	private static void test4(Vector<Integer> v1) {
	    int delIndex = 0;
	    for (Integer integer : v1) {
	        if (integer.equals(5)) {
	            delIndex = v1.indexOf(integer);
	        }
	    }
	    v1.remove(delIndex);
	}
	
	// 最方便的方式就是使用jdk1.8提供的函数式编程接口
	private static void test5(Vector<Integer> v1){
	    v1.removeIf((i) -> i.equals(5));
	}

	public static void main(String[] args) {

		Vector<Integer> vector = new Vector<>();
		for (int i = 1; i <= 10; i++) {
			vector.add(i);
		}
		test1(vector);
		test2(vector);
		test3(vector);
		test4(vector);
		test5(vector);
	}
}
