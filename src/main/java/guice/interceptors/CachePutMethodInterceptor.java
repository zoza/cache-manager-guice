/**
 * 
 */
package guice.interceptors;

import guice.CacheMethodInterceptor;

import org.aopalliance.intercept.MethodInvocation;

import annotations.CachePut;

/**
 * @author zoza
 * 
 */
public class CachePutMethodInterceptor extends CacheMethodInterceptor<CachePut> {

    public CachePutMethodInterceptor() {
        super(CachePut.class);
    }

    @Override
    public Object handle(MethodInvocation invocation) {
        // TODO Auto-generated method stub
        return null;
    }

}
