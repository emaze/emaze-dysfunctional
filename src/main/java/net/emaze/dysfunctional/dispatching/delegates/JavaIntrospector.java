package net.emaze.dysfunctional.dispatching.delegates;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * A binary delegate yielding {@literal PropertyDescriptor}s of a class.
 *
 * @author rferranti
 */
public class JavaIntrospector implements BinaryDelegate<PropertyDescriptor[], Class<?>, Class<?>> {

    @Override
    public PropertyDescriptor[] perform(Class<?> klass, Class<?> stopKlass) {
        dbc.precondition(klass != null, "cannot introspect a null class");
        try {
            return Introspector.getBeanInfo(klass, stopKlass).getPropertyDescriptors();
        } catch (IntrospectionException ex) {
            throw new IllegalStateException("introspection exception", ex);
        }
    }
}
