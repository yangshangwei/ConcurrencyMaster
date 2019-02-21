package com.artisan.example.immutable;

import java.util.Collections;
import java.util.Map;

import com.artisan.anno.ThreadSafe;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
public class JdkCollectionsunmodifiableXXX {

	
	// 普通的map ,没有使用final修饰
	private static Map<String, Object> map = Maps.newHashMap();

	static {
		map.put("name", "artisan");
		map.put("age", 20);
		map.put("sex", "男");
		//这样处理后的map的值不可以修改
        map = Collections.unmodifiableMap(map);
	}

	public static void main(String[] args) {
		// 尝试修改 将抛出 java.lang.UnsupportedOperationException
		map.put("name", "小工匠");
		log.info("name:{}", map.get("name"));
	}


}
