/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation indicating that a method (or all methods on a class) trigger(s) a
 * {@link Cache#put(Object, Object)} operation. As opposed to {@link Cacheable}
 * annotation, this annotation does not cause the target method to be skipped -
 * rather it always causes the method to be invoked and its result to be placed
 * into the cache.
 * 
 * @author Costin Leau
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

    /**
     * OGNL attribute for computing the key
     * dynamically.
     * <p>
     * Default is "", meaning all method parameters are considered as a key.
     */
    String key() default "";

    /**
     * OGNL attribute used for conditioning the
     * cache update.
     * <p>
     * Default is "", meaning the method result is always cached.
     */
    String condition() default "";
}