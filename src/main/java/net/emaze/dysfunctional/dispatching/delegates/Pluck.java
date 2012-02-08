package net.emaze.dysfunctional.dispatching.delegates;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Plucks a property from a bean. i.e: > let Bean bean = { String key :
 * 'value'}; > new Pluck<String, Bean> pluck = new
 * Pluck<String,Bean>(Bean.class, "key"); > pluck.perform(bean); yields "value".
 *
 * @param <R> the return type parameter
 * @param <T> the element type parameter
 * @author rferranti
 */
public class Pluck<R, T> implements Delegate<R, T> {

    private final BinaryDelegate<PropertyDescriptor[], Class<?>, Class<?>> introspector = new JavaIntrospector();
    private final Method method;

    public Pluck(Class<T> klass, String property) {
        dbc.precondition(klass != null, "cannot pluck from a null class");
        dbc.precondition(property != null, "cannot pluck a null property from an object");
        this.method = propertyNameToMethod(klass, property);
    }

    @Override
    public R perform(T t) {
        try {
            return (R) method.invoke(t);
        } catch (IllegalAccessException ex) {
            throw new IllegalStateException(ex);
        } catch (InvocationTargetException ex) {
            throw new IllegalStateException(ex.getCause());
        }
    }

    private Method propertyNameToMethod(Class<?> klass, String property) {
        for (PropertyDescriptor pd : introspector.perform(klass, Object.class)) {
            if (property.equals(pd.getName())) {
                return pd.getReadMethod();
            }
        }
        throw new IllegalStateException(String.format("property %s is not defined in class %s", property, klass.getSimpleName()));
    }
}
