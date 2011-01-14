package net.emaze.dysfunctional.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ripped from jmanner, please keep in sync
 * @author rferranti
 */
public class MethodType {

    private final Method method;

    public MethodType(Method method) {
        this.method = method;
    }

    public Object invoke(Object target, Object... params) {
        try {
            return method.invoke(target, params);
        } catch (IllegalAccessException ex) {
            throw new ReflectionException(ex);
        } catch (InvocationTargetException ex) {
            throw new InvocationException(ex.getCause());
        }
    }

    public Method getWrappedMethod() {
        return method;
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof MethodType) {
            final MethodType other = (MethodType) rhs;
            return this.method.equals(other.method);
        }
        if (rhs instanceof Method) {
            return this.method.equals((Method) rhs);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return method.hashCode();
    }

    @Override
    public String toString() {
        return String.format("MethodType<%s>", method.toString());
    }
}
