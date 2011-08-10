package net.emaze.dysfunctional.dispatching.delegates;

import java.util.Map;
import net.emaze.dysfunctional.contracts.dbc;

public class ConstantMapper<R, T> implements Delegate<R, T> {

    private final Map<T, R> mapping;

    public ConstantMapper(Map<T, R> mapping) {
        dbc.precondition(mapping != null, "Can not create constant mapping with null map");
        this.mapping = mapping;
    }

    @Override
    public R perform(T t) {
        dbc.precondition(mapping.containsKey(t), "can not map %s", t);
        return mapping.get(t);
    }
}
