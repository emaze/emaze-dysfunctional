package net.emaze.dysfunctional.delegates;

import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.options.Memoized;
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
    private Maybe<Class<?>> clazz = Maybe.nothing();
    private Memoized<MethodType> methodCache = new Memoized<MethodType>();

    @Override
    public void perform(T message) {
        if (!methodCache.isMemoized()) {
            final ClassType calleeType = new ClassType(callee.getClass());
            final MethodType m = clazz.hasValue()
                    ? calleeType.getMethod(methodName, clazz.value())
                    : calleeType.getMethodByName(methodName);
            methodCache.memoize(m);
        }
        methodCache.value().invoke(callee, message);
    }

    public void setMessageClass(Class<?> clazz) {
        methodCache.invalidate();
        this.clazz = Maybe.<Class<?>>just(clazz);
    }

    public void setMethodName(String methodName) {
        methodCache.invalidate();
        this.methodName = methodName;
    }

    public void setCallee(Object callee) {
        methodCache.invalidate();
        this.callee = callee;
    }
}
