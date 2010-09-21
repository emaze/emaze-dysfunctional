package net.emaze.dysfunctional.delegates;

import java.lang.reflect.Method;
import net.emaze.dysfunctional.Maybe;
import net.emaze.dysfunctional.Memoized;
import net.emaze.dysfunctional.reflection.MethodInvoker;
import net.emaze.dysfunctional.reflection.MethodReflector;

/**
 * A unary functor with no return value decorator calling the nested action via
 * reflection (used for decoupling via loose typing)
 * @author rferranti
 */
public class ReflectiveAction<T> implements Action<T> {

    private String methodName;
    private Object callee;
    private Maybe<Class<T>> clazz = Maybe.nothing();
    private Memoized<Method> methodCache = new Memoized<Method>();

    @Override
    public void perform(T message) {
        if (!methodCache.isMemoized()) {
            final Method m = clazz.hasValue()
                    ? new MethodReflector().fetch(callee.getClass(), methodName, clazz.value())
                    : new MethodReflector().fetchByName(callee.getClass(), methodName);
            methodCache.memoize(m);
        }
        new MethodInvoker(methodCache.value()).invoke(callee, message);
    }

    public void setMessageClass(Class<T> clazz) {
        methodCache.invalidate();
        this.clazz = Maybe.just(clazz);
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
