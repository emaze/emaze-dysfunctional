package net.emaze.dysfunctional.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * ripped from jmanner, please keep in sync
 * @param <T> 
 * @author rferranti
 */
public class ConstructorType {
    private final Constructor<?> ctor;

    public ConstructorType(Constructor<?> ctor) {
        this.ctor = ctor;
    }

    public Object newInstance(Object... initargs){
        try {
            return ctor.newInstance(initargs);
        } catch (InstantiationException ex) {
            throw new ReflectionException(ex);
        } catch (IllegalAccessException ex) {
            throw new ReflectionException(ex);
        } catch (InvocationTargetException ex) {
            throw new InvocationException(ex.getCause());
        }
    }
}
