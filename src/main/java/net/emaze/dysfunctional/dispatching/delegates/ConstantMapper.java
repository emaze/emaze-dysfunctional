package net.emaze.dysfunctional.dispatching.delegates;

import java.util.Map;
import net.emaze.dysfunctional.contracts.dbc;

public class ConstantMapper<R, T> implements BinaryDelegate<R, Map<T, R>, T> {

    @Override
    public R perform(Map<T, R> mapping, T t) {
        dbc.precondition(mapping != null, "Can not create constant mapping with null map");
        dbc.precondition(mapping.containsKey(t), "can not map %s", t);
        return mapping.get(t);
    }
}
