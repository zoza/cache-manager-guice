/**
 * 
 */
package aop;

import annotations.CacheKeyParam;
import annotations.Cacheable;

/**
 * @author zoza
 * 
 */
public class TestClass {
    @Cacheable
    public Object testMethod(long num, int count) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "inside testMethod";
    }

    @Cacheable
    public Object testSimpleMethod(@SuppressWarnings(value = "dd") @Deprecated @CacheKeyParam long num,
                                   boolean bool) {
        System.out.println("inside method testSimpleMethod: long:" + num + " bool:" + bool);
        return "long";
    }

    @Cacheable
    public Object testSimpleMethod(@CacheKeyParam(part = "name") @Deprecated  TestObject obj) {
        System.out.println("inside method testSimpleMethod: TestObject:" + obj.getName());
        return "int";
    }
}
