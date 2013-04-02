/**
 * 
 */
package aop;

import guice.CacheModule;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * @author zoza
 * 
 */
public class TestAop {
    @Test
    public void annotations() {
        List<Module> list = new ArrayList<Module>();
        list.add(new CacheModule());
        Injector injector = Guice.createInjector(list);
        TestClass testClass = injector.getInstance(TestClass.class);
        // testClass.testMethod(5, 5);
        // testClass.testMethod(7, 1);
        testClass.testSimpleMethod(5L, true);
        testClass.testSimpleMethod(5L, false);
        testClass.testSimpleMethod(new TestObject("zoza",
                Integer.MAX_VALUE + 10000000000L));
    }
}
