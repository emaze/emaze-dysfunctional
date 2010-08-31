package net.emaze.disfunctional.delegates;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import net.emaze.disfunctional.Maybe;
import net.emaze.disfunctional.Memoized;
import net.emaze.disfunctional.iterations.Iterations;

/**
 *
 * @author rferranti
 */
public class ReflectiveAction<T> implements Action<T> {

    private String methodName;
    private Object callee;
    private Maybe<Class<T>> clazz = Maybe.nothing();
    private Memoized<Method> methodCache = new Memoized<Method>();

    private static <T> Method fetchMethod(Object callee, String methodName, Class<T> paramClass, T message)
            throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        return callee.getClass().getMethod(methodName, paramClass);
    }

    private static <T> Method fetchMethod(Object callee, final String methodName, T message)
            throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        final List<Method> methods = Arrays.asList(callee.getClass().getMethods());
        final List<Method> matchingMethods = Iterations.some(methods, new Predicate<Method>() {

            public boolean test(Method method) {
                return method.getName().equals(methodName);
            }
        });

        if (matchingMethods.isEmpty()) {
            throw new IllegalStateException("trying to call by reflection an inexistent method");
        }
        if (matchingMethods.size() > 1) {
            throw new IllegalStateException("trying to call by reflection on an ambigouous method (messageClass must be specified)");
        }
        return matchingMethods.get(0);
    }

    @Override
    public void perform(T message) {
        try {
            if (!methodCache.isMemoized()) {
                final Method m = clazz.hasValue()
                        ? fetchMethod(callee, methodName, clazz.value(), message)
                        : fetchMethod(callee, methodName, message);
                methodCache.memoize(m);
            }
            methodCache.value().invoke(callee, message);
        } catch (IllegalAccessException ex) {
            throw new IllegalStateException("", ex);
        } catch (IllegalArgumentException ex) {
            throw new IllegalStateException("", ex);
        } catch (InvocationTargetException ex) {
            throw new IllegalStateException("", ex);
        } catch (NoSuchMethodException ex) {
            throw new IllegalStateException("", ex);
        } catch (SecurityException ex) {
            throw new IllegalStateException("", ex);
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
