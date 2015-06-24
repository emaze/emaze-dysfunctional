package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.Function;
import java.util.HashMap;
import java.util.Map;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * A proxy that returns the value provided by the given function, evaluated only
 * once for each parameter.
 *
 * @param <T> the function parameter type
 * @param <R> the function result type
 */
public class MemoizingFunction<T, R> implements Function<T, R> {

    private final Function<T, R> function;
    private final Map<T, R> cache;

    public MemoizingFunction(Function<T, R> function) {
        this(function, new HashMap<>());
    }

    public MemoizingFunction(Function<T, R> function, Map<T, R> cache) {
        dbc.precondition(function != null, "Cannot create a memoizing function with a null function");
        dbc.precondition(cache != null, "Cannot create a memoizing function with a null cache");
        this.function = function;
        this.cache = cache;
    }

    @Override
    public R apply(T t) {
        if (cache.containsKey(t)) {
            return cache.get(t);
        }
        final R result = function.apply(t);
        cache.put(t, result);
        return result;
    }
}
