package net.emaze.disfunctional.delegates;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.emaze.disfunctional.Maybe;
import net.emaze.disfunctional.Memoized;
import net.emaze.disfunctional.reflection.MethodFetcher;

/**
 *
 * @author rferranti
 */
public class ReflectiveAction<T> implements Action<T> {

    private String methodName;
    private Object callee;
    private Maybe<Class<T>> clazz = Maybe.nothing();
    private Memoized<Method> methodCache = new Memoized<Method>();

    @Override
    public void perform(T message) {
        try {
            if (!methodCache.isMemoized()) {
                final Method m = clazz.hasValue()
                        ? new MethodFetcher().fetch(callee.getClass(), methodName, clazz.value())
                        : new MethodFetcher().fetchAmbiguous(callee.getClass(), methodName);
                methodCache.memoize(m);
            }
            methodCache.value().invoke(callee, message);
        } catch (IllegalAccessException ex) {
            throw new IllegalStateException(ex);
        } catch (IllegalArgumentException ex) {
            throw new IllegalStateException(ex);
        } catch (InvocationTargetException ex) {
            throw new IllegalStateException(ex);
        } catch (SecurityException ex) {
            throw new IllegalStateException(ex);
        }
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
