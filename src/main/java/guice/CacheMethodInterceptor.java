/**
 * 
 */
package guice;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import ognl.Ognl;
import ognl.OgnlException;

import org.aopalliance.intercept.MethodInvocation;

import annotations.CacheKeyParam;
import cache.CacheManager;

import com.google.inject.Inject;

/**
 * @author zoza
 * 
 */
public abstract class CacheMethodInterceptor<T extends Annotation> implements
        org.aopalliance.intercept.MethodInterceptor {
    private static final int NO_PARAM_KEY = 0;
    private static final int NULL_PARAM_KEY = 53;
    private Class<? extends Annotation> annotationClass;
    private T annotation;

    @Inject
    protected CacheManager cacheManager;

    public CacheMethodInterceptor(Class<? extends Annotation> annotationClass) {
        super();
        this.annotationClass = annotationClass;
    }

    public Class<? extends Annotation> getAnnotationClass() {
        return annotationClass;
    }

    public T getAnnotation() {
        return annotation;
    }

    @SuppressWarnings("unchecked")
    public Object invoke(MethodInvocation invocation) throws Throwable {
        annotation = (T) invocation.getMethod().getAnnotation(annotationClass);
        return handle(invocation);
    }

    protected Object getKey(MethodInvocation invocation) {
        StringBuilder sb = new StringBuilder(invocation.getMethod()
                .getDeclaringClass().getSimpleName());
        sb.append(".");
        sb.append(invocation.getMethod().getName());

        Object[] arguments = invocation.getArguments();

        Annotation[][] parameterAnnotations = invocation.getMethod()
                .getParameterAnnotations();
        List<Object> params = new ArrayList<Object>();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (int j = 0; j < parameterAnnotations[i].length; j++) {
                if (parameterAnnotations[i][j].annotationType().equals(
                        CacheKeyParam.class)) {
                    CacheKeyParam param = (CacheKeyParam) parameterAnnotations[i][j];
                    if (param.part().equals("")) {
                        params.add(arguments[i]);
                    } else {
                        try {
                            params.add(Ognl.getValue(param.part(), arguments[i]));
                        } catch (OgnlException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
        sb.append(".");
        if (params.isEmpty()) {
            return sb.append(generateKey(invocation.getArguments()));
        } else {
            return sb.append(generateKey(params.toArray()));
        }

    }

    protected Integer generateKey(Object[] params) {
        if (params.length == 1) {
            return (params[0] == null ? NULL_PARAM_KEY : params[0].hashCode());
        }
        if (params.length == 0) {
            return NO_PARAM_KEY;
        }
        int hashCode = 17;
        for (Object object : params) {
            hashCode = 31 * hashCode
                    + (object == null ? NULL_PARAM_KEY : object.hashCode());
        }
        return Math.abs(Integer.valueOf(hashCode));
    }

    public abstract Object handle(MethodInvocation invocation) throws Throwable;

}
