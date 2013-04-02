/**
 * 
 */
package guice;

import guice.interceptors.CacheEvictMethodInterceptor;
import guice.interceptors.CacheableMethodInterceptor;

import java.lang.annotation.Annotation;

import aop.TestClass;
import cache.CacheManager;
import cache.memcache.MemcacheCacheManager;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.matcher.Matchers;

/**
 * @author zoza
 * 
 */
public class CacheModule extends AbstractModule {

    /*
     * (non-Javadoc)
     * 
     * @see com.google.inject.AbstractModule#configure()
     */
    @Override
    protected void configure() {
        bind(CacheManager.class).toProvider(new Provider<CacheManager>() {

            public CacheManager get() {
                MemcacheCacheManager memcacheCacheManager = new MemcacheCacheManager();
                try {
                    memcacheCacheManager.init();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return memcacheCacheManager;
            }
        }).asEagerSingleton();

        CacheableMethodInterceptor cacheableMethodInterceptor = new CacheableMethodInterceptor();
        CacheEvictMethodInterceptor cacheEvictMethodInterceptor = new CacheEvictMethodInterceptor();
        requestInjection(cacheableMethodInterceptor);
        requestInjection(cacheEvictMethodInterceptor);

        bindInterceptor(cacheableMethodInterceptor);
        bindInterceptor(cacheEvictMethodInterceptor);

        bind(TestClass.class);

    }

    protected final void bindInterceptor(final CacheMethodInterceptor<? extends Annotation> methodInterceptor) {
        bindInterceptor(Matchers.any(),
                Matchers.annotatedWith(methodInterceptor.getAnnotationClass()),
                methodInterceptor);
    }
}
