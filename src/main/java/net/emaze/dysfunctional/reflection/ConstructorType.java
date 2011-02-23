package net.emaze.dysfunctional.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * ripped from jmanner, please keep in sync
 * @author rferranti
 */
public class ConstructorType {
    private final Constructor<?> ctor;

    public ConstructorType(Constructor<?> ctor) {
        dbc.precondition(ctor != null, "cannot create a ConstructorType with a null Constructor");
        this.ctor = ctor;
    }

    public Object newInstance(Object... initargs){
        dbc.precondition(initargs != null, "cannot call a new instance with null initargs array");
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
