
package guice;

import java.lang.annotation.Annotation;

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

    private Class<? extends Annotation> annotationClass;
    private T annotation;

    @Inject
    protected CacheManager cacheManager;

    public CacheMethodInterceptor(Class<? extends Annotation> annotationClass) {      
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
        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (int j = 0; j < parameterAnnotations[i].length; j++) {
                if (parameterAnnotations[i][j].annotationType().equals(
                        CacheKeyParam.class)) {
                    CacheKeyParam param = (CacheKeyParam) parameterAnnotations[i][j];
                    Object key = null;
                    if (!param.part().equals("")) {
                        try {
                            key = Ognl.getValue(param.part(), arguments[i]);
                        } catch (OgnlException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    } else {
                        if (arguments[i] != null) {
                            key = arguments[i].toString();
                        }
                    }
                    sb.append(".");
                    if (key == null || key.equals("")) {
                        sb.append("NULL");
                    } else {
                        sb.append(key);
                    }
                }
            }

        }

        return sb.toString();
    }

    public abstract Object handle(MethodInvocation invocation) throws Throwable;

}
