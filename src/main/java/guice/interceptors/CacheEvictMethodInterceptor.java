/**
 * 
 */
package guice.interceptors;

import guice.CacheMethodInterceptor;

import org.aopalliance.intercept.MethodInvocation;

import annotations.CacheEvict;

/**
 * @author zoza
 * 
 */
public class CacheEvictMethodInterceptor extends
        CacheMethodInterceptor<CacheEvict> {

    public CacheEvictMethodInterceptor() {
        super(CacheEvict.class);
    }

    @Override
    public Object handle(MethodInvocation invocation) {
        System.out.println("inside " + this.getClass().getSimpleName()
                + " for " + invocation.getMethod().getName());
        return null;
    }

}
