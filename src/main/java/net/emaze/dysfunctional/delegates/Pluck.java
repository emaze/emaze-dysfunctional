package net.emaze.dysfunctional.delegates;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import net.emaze.dysfunctional.reflection.MethodInvoker;

/**
 *
 * @param <R>
 * @param <T> 
 * @author rferranti
 */
public class Pluck<R, T> implements Delegate<R, T> {

    private final MethodInvoker method;

    public Pluck(Class<T> klass, String property) {
        this.method = propertyNameToMethodInvoker(klass, property);
    }

    @Override
    public R perform(T t) {
        return (R) method.invoke(t);
    }

    private static <T> BeanInfo beanInfoFor(Class<T> klass) {
        try {
            return Introspector.getBeanInfo(klass, Object.class);
        } catch (IntrospectionException ex) {
            throw new IllegalStateException("introspection exception", ex);
        }
    }

    private static MethodInvoker propertyNameToMethodInvoker(Class<?> klass, String property) {
        for (PropertyDescriptor pd : beanInfoFor(klass).getPropertyDescriptors()) {
            if (property.equals(pd.getName())) {
                return new MethodInvoker(pd.getReadMethod());
            }
        }
        throw new IllegalStateException(String.format("property %s is not defined in class %s", property, klass.getSimpleName()));
    }
}
