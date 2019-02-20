package com.artisan.example.publish;

import com.artisan.anno.NotRecommand;
import com.artisan.anno.NotThreadSafe;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 *  对象逸出示例，在对象构造完成之前，不可以将其发布
 * @author yangshangwei
 *
 */
@Slf4j
@NotThreadSafe
@NotRecommand
public class ObjectEscapeDemo {
	
	private int thisCanBeEscape = 0;

    public ObjectEscapeDemo() {
        new InnerClass();
    }

    private class InnerClass {
    	// this引用的逸出
    	// 内部类的构造器里包含了对封装实例的隐含引用，这样在对象没有被正确构造完成之前就会被发布，由此会导致不安全的因素在里面
        public InnerClass() {
            log.info("{}", ObjectEscapeDemo.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new ObjectEscapeDemo();
    }

}
