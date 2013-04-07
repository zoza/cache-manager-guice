package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation indicating that a method (or all the methods on a class) can be
 * cached.
 * 
 * @author zoza
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Cacheable {

    /**
     * Name of the cache region in which the update takes place.
     * <p>
     * Default is "defaultRegion"
     */
    String region() default "defaultRegion";
}