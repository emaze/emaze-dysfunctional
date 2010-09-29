package net.emaze.dysfunctional.reflection;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Resolves via reflection a reference to a Method
 * @author rferranti
 */
public class MethodReflector {

    public <T> Method fetch(final Class<?> clazz, final String methodName, Class<T>... params) {
        dbc.precondition(clazz != null, "trying to fetch a method from a null class");
        dbc.precondition(methodName != null, "trying to fetch a method with a null methodName");
        try {
            return clazz.getMethod(methodName, params);
        } catch (NoSuchMethodException ex) {
            throw new IllegalStateException("method not found", ex);
        } catch (SecurityException ex) {
            throw new IllegalStateException("security exception while fetching method", ex);
        }
    }

    public Method fetchByName(final Class<?> clazz, final String methodName) {
        dbc.precondition(clazz != null, "trying to fetch a method from a null class");
        dbc.precondition(methodName != null, "trying to fetch a method with a null methodName");
        final List<Method> methods = Arrays.asList(clazz.getMethods());
        final List<Method> matchingMethods = new ArrayList<Method>();
        for(Method m : methods){
            if(m.getName().equals(methodName)){
                matchingMethods.add(m);
            }
        }
        dbc.stateprecondition(!matchingMethods.isEmpty(), "method %s not found", methodName);
        dbc.stateprecondition(matchingMethods.size() == 1, "method is ambigouous (method params must be specified)");
        return matchingMethods.get(0);
    }
}
