package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation indicating that a method trigger a
 * {@link Cache#put(Object, Object)} operation. As opposed to {@link Cacheable}
 * annotation, this annotation does not cause the target method to be skipped -
 * rather it always causes the method to be invoked and its result to be placed
 * into the cache.
 * 
 * @author zoza
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CachePut {

    /**
     * Name of the cache region in which the update takes place.
     * <p>
     * Default is "defaultRegion"
     */
    String region() default "defaultRegion";
}