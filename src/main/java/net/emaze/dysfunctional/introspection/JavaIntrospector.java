package net.emaze.dysfunctional.introspection;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;

/**
 *
 * @author rferranti
 */
public class JavaIntrospector implements BinaryDelegate<PropertyDescriptor[], Class<?>, Class<?>> {

    @Override
    public PropertyDescriptor[] perform(Class<?> klass, Class<?> stopKlass) {
        try {
            return Introspector.getBeanInfo(klass, stopKlass).getPropertyDescriptors();
        } catch (IntrospectionException ex) {
            throw new IllegalStateException("introspection exception", ex);
        }
    }
}
