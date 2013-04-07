package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation indicating that a method trigger a cache invalidate operation.
 * 
 * @author zoza
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CacheEvict {

    /**
     * Name of the cache region in which the update takes place.
     * <p>
     * Default is "defaultRegion"
     */
    String region() default "defaultRegion";

}