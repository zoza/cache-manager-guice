/**
 * 
 */
package guice.interceptors;

import guice.CacheMethodInterceptor;

import org.aopalliance.intercept.MethodInvocation;

import annotations.Cacheable;

/**
 * @author zoza
 * 
 */
public class CacheableMethodInterceptor extends
        CacheMethodInterceptor<Cacheable> {

    public CacheableMethodInterceptor() {
        super(Cacheable.class);
    }

    @Override
    public Object handle(MethodInvocation invocation) throws Throwable {
        Object key = getKey(invocation);
        Cacheable annotation = getAnnotation();

        System.out.println("key is: " + key);
        Object result = cacheManager.get(annotation.region(), key);
        if (result == null) {
            System.out.println("cache empty");
            Object proceed = invocation.proceed();
            cacheManager.put(annotation.region(), key, proceed);
            result = proceed;
        }

        return result;
    }

}
