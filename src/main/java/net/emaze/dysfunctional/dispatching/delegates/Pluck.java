package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.introspection.JavaIntrospector;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.emaze.dysfunctional.reflection.MethodType;

/**
 * plucks a property from a bean
 * i.e:
 *  > let Bean bean = { String key : 'value'};
 *  > new Pluck<String, Bean> pluck = new Pluck<String,Bean>(Bean.class, "key");
 *  > pluck.perform(bean);
 * yields "value".
 * @param <R> the return type parameter
 * @param <T> the element type parameter
 * @author rferranti
 */
public class Pluck<R, T> implements Delegate<R, T> {
    
    private final BinaryDelegate<PropertyDescriptor[], Class<?>, Class<?>> introspector = new JavaIntrospector();
    private final MethodType method;
    
    public Pluck(Class<T> klass, String property) {
        this.method = propertyNameToMethod(klass, property);
    }
    
    @Override
    public R perform(T t) {
        return (R) method.invoke(t);
    }
    
    private MethodType propertyNameToMethod(Class<?> klass, String property) {
        for (PropertyDescriptor pd : introspector.perform(klass, Object.class)) {
            if (property.equals(pd.getName())) {
                return new MethodType(pd.getReadMethod());
            }
        }
        throw new IllegalStateException(String.format("property %s is not defined in class %s", property, klass.getSimpleName()));
    }
}
