package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.Function;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Plucks class from an object.
 *
 * @author rferranti
 */
public class ClassPlucker<T> implements Function<T, Class<?>> {

    @Override
    public Class<?> apply(T obj) {
        dbc.precondition(obj != null, "cannot pluck class from a null object");
        return obj.getClass();
    }
}
