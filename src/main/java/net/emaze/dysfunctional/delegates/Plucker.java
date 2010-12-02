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
public class Plucker<R, T> implements Delegate<R, T> {

    private final String plucked;

    public Plucker(String plucked) {
        this.plucked = plucked;
    }

    @Override
    public R perform(T t) {
        final BeanInfo bi = beanInfoFor(t);
        for (PropertyDescriptor pd : bi.getPropertyDescriptors()) {
            if (plucked.equals(pd.getName())) {
                return (R) new MethodInvoker(pd.getReadMethod()).invoke(t);
            }
        }
        throw new IllegalStateException(String.format("bean class %s does not contain property %s", t.getClass(), plucked));
    }

    private static <T> BeanInfo beanInfoFor(T t) {
        try {
            return Introspector.getBeanInfo(t.getClass(), Object.class);
        } catch (IntrospectionException ex) {
            throw new IllegalStateException("introspection exception", ex);
        }
    }
}
