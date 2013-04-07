/**
 * 
 */
package guice;

import guice.interceptors.CacheEvictMethodInterceptor;
import guice.interceptors.CachePutMethodInterceptor;
import guice.interceptors.CacheableMethodInterceptor;

import java.lang.annotation.Annotation;

import cache.CacheManager;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.matcher.Matchers;

/**
 * @author zoza
 * 
 */
public abstract class CacheModule extends AbstractModule {

    /**
     * you must override this method and return some impl (memcache, ehcache,
     * hazelcast) or use SimpleCacheModule
     * 
     * @return CacheManager impl
     */
    protected abstract CacheManager getManager();

    /*
     * (non-Javadoc)
     * 
     * @see com.google.inject.AbstractModule#configure()
     */
    @Override
    protected void configure() {
        bind(CacheManager.class).toProvider(new Provider<CacheManager>() {

            public CacheManager get() {
                CacheManager cacheManager = getManager();
                try {
                    cacheManager.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return cacheManager;
            }
        }).asEagerSingleton();

        CacheableMethodInterceptor cacheableMethodInterceptor = new CacheableMethodInterceptor();
        requestInjection(cacheableMethodInterceptor);
        bindInterceptor(cacheableMethodInterceptor);
        
        CacheEvictMethodInterceptor cacheEvictMethodInterceptor = new CacheEvictMethodInterceptor();
        requestInjection(cacheEvictMethodInterceptor);
        bindInterceptor(cacheEvictMethodInterceptor);
        
        CachePutMethodInterceptor cachePutMethodInterceptor = new CachePutMethodInterceptor();
        requestInjection(cachePutMethodInterceptor);
        bindInterceptor(cachePutMethodInterceptor);

    }

    protected final void bindInterceptor(final CacheMethodInterceptor<? extends Annotation> methodInterceptor) {
        bindInterceptor(Matchers.any(),
                Matchers.annotatedWith(methodInterceptor.getAnnotationClass()),
                methodInterceptor);
    }
}
