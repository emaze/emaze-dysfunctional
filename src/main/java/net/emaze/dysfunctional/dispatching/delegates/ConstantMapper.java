package net.emaze.dysfunctional.dispatching.delegates;

import java.util.Map;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Transforms a value using mapping contained in the passed map.
 *
 * @author rferranti, sbaruzza
 * @param <R> the map value type
 * @param <T> the map key type
 */
public class ConstantMapper<R, T> implements BinaryDelegate<R, Map<T, R>, T> {

    @Override
    public R perform(Map<T, R> mapping, T value) {
        dbc.precondition(mapping != null, "Can not create constant mapping with null map");
        dbc.precondition(mapping.containsKey(value), "can not map %s", value);
        return mapping.get(value);
    }
}
