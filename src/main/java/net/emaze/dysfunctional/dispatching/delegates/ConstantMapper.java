package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.BiFunction;
import java.util.Map;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Transforms a value using mapping contained in the passed map.
 *
 * @author rferranti, sbaruzza
 * @param <T> the map key type
 * @param <R> the map value type
 */
public class ConstantMapper<T, R> implements BiFunction<Map<T, R>, T, R> {

    @Override
    public R apply(Map<T, R> mapping, T value) {
        dbc.precondition(mapping != null, "Can not create constant mapping with null map");
        dbc.precondition(mapping.containsKey(value), "can not map %s", value);
        return mapping.get(value);
    }
}
