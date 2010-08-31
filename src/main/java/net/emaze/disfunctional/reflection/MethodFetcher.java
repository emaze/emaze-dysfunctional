package net.emaze.disfunctional.reflection;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import net.emaze.disfunctional.delegates.Predicate;
import net.emaze.disfunctional.iterations.Iterations;

/**
 *
 * @author rferranti
 */
public class MethodFetcher {


    
    public <T> Method fetch(final Class clazz, final String methodName, Class<T>... params){
        try {
            return clazz.getMethod(methodName, params);
        } catch (NoSuchMethodException ex) {
            throw new IllegalStateException("method not found", ex);
        } catch (SecurityException ex) {
            throw new IllegalStateException("security exception while fetching method", ex);
        }
    }
    
    public Method fetchAmbiguous(final Class clazz, final String methodName){
        final List<Method> methods = Arrays.asList(clazz.getMethods());
        final List<Method> matchingMethods = Iterations.some(methods, new Predicate<Method>() {

            public boolean test(Method method) {
                return method.getName().equals(methodName);
            }
        });
        if (matchingMethods.isEmpty()) {
            throw new IllegalStateException("method not found");
        }
        if (matchingMethods.size() > 1) {
            throw new IllegalStateException("method is ambigouous (Class<T> params must be specified)");
        }
        return matchingMethods.get(0);
    }
}
