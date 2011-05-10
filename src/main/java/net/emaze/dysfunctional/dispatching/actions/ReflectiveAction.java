package net.emaze.dysfunctional.dispatching.actions;

import net.emaze.dysfunctional.reflection.ClassType;
import net.emaze.dysfunctional.reflection.MethodType;

/**
 * A unary functor with no return value decorator calling the nested action via
 * reflection (used for decoupling via loose typing)
 * @param <T> 
 * @author rferranti
 */
public class ReflectiveAction<T> implements Action<T> {

    private String methodName;
    private Object callee;
    private Class<?> clazz;
    private MethodType methodCache;
    private boolean methodCacheIsMemoized;

    @Override
    public void perform(T message) {
        if (!methodCacheIsMemoized) {
            final ClassType calleeType = new ClassType(callee.getClass());
            final MethodType m = clazz != null
                    ? calleeType.getMethod(methodName, clazz)
                    : calleeType.getMethodByName(methodName);
            methodCache = m;
            methodCacheIsMemoized = true;
        }
        methodCache.invoke(callee, message);
    }

    public void setMessageClass(Class<?> clazz) {
        methodCacheIsMemoized = false;
        this.clazz = clazz;
    }

    public void setMethodName(String methodName) {
        methodCacheIsMemoized = false;
        this.methodName = methodName;
    }

    public void setCallee(Object callee) {
        methodCacheIsMemoized = false;
        this.callee = callee;
    }
}
