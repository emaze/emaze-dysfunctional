package net.emaze.dysfunctional.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Responsibility: invokes a method via reflection transforming checked exceptions involved in the invocation
 * to IllegalStateStateException s (unchecked)
 * @author rferranti
 * @deprecated use TypeMethod instead
 */
@Deprecated
public class MethodInvoker {
    private Method method;

    public MethodInvoker(Method method){
        dbc.precondition(method != null, "trying to create a MethodInvoker from a null method");
        this.method = method;
    }

    public Object invoke(Object self, Object... params){
        dbc.precondition(self != null, "trying to invoke a method with a null self");
        try {
            return method.invoke(self, params);
        } catch (IllegalAccessException ex) {
            throw new IllegalStateException(ex);
        } catch (InvocationTargetException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
