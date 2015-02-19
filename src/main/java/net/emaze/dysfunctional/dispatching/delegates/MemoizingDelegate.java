package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.Function;
import java.util.HashMap;
import java.util.Map;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * A proxy that returns the value provided by the given delegate, evaluated 
 * only once for each parameter.
 *
 * @param <T> the delegate parameter type
 * @param <R> the delegate result type
 */
public class MemoizingDelegate<T, R> implements Function<T, R> {

    private final Function<T, R> delegate;
    private final Map<T, R> cache;

    public MemoizingDelegate(Function<T, R> delegate) {
        this(delegate, new HashMap<T, R>());
    }

    public MemoizingDelegate(Function<T, R> delegate, Map<T, R> cache) {
        dbc.precondition(delegate != null, "Cannot create a memoizing delegate with a null delegate");
        dbc.precondition(cache != null, "Cannot create a memoizing delegate with a null cache");
        this.delegate = delegate;
        this.cache = cache;
    }

    @Override
    public R apply(T t) {
        if (cache.containsKey(t)) {
            return cache.get(t);
        }
        final R result = delegate.apply(t);
        cache.put(t, result);
        return result;
    }
}
