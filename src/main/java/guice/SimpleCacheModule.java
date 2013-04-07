/**
 * 
 */
package guice;

import aop.TestClass;
import cache.CacheManager;

/**
 * @author zoza
 * 
 */
public class SimpleCacheModule extends CacheModule {
    private CacheManager cacheManager;

    @Override
    protected void configure() {
        // TODO Auto-generated method stub
        super.configure();
        bind(TestClass.class);
    }

    public SimpleCacheModule(CacheManager cacheManager) {
        super();
        if (cacheManager == null) {
            throw new NullPointerException("cacheManager cant be null");
        }
        this.cacheManager = cacheManager;
    }

    /*
     * (non-Javadoc)
     * 
     * @see guice.CacheModule#getManager()
     */
    @Override
    protected CacheManager getManager() {
        return cacheManager;
    }

}
