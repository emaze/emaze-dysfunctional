package net.emaze.dysfunctional.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author rferranti
 */
public class MethodInvoker {
    private Method method;

    public MethodInvoker(Method method){
        this.method = method;
    }

    public Object invoke(Object self, Object... params){
        try {
            return method.invoke(self, params);
        } catch (IllegalAccessException ex) {
            throw new IllegalStateException(ex);
        } catch (InvocationTargetException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
