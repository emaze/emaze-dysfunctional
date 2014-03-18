package net.emaze.dysfunctional.dispatching.delegates;

import java.util.HashMap;
import java.util.Map;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * A proxy that returns the value provided by the given delegate, evaluated 
 * only once for each parameter.
 *
 * @param <R> the delegate result type
 * @param <T> the delegate parameter type
 */
public class MemoizingDelegate<R, T> implements Delegate<R, T> {

    private final Delegate<R, T> delegate;
    private final Map<T, R> cache;

    public MemoizingDelegate(Delegate<R, T> delegate) {
        this(delegate, new HashMap<T, R>());
    }

    public MemoizingDelegate(Delegate<R, T> delegate, Map<T, R> cache) {
        dbc.precondition(delegate != null, "Cannot create a memoizing delegate with a null delegate");
        dbc.precondition(cache != null, "Cannot create a memoizing delegate with a null cache");
        this.delegate = delegate;
        this.cache = cache;
    }

    @Override
    public R perform(T t) {
        if (cache.containsKey(t)) {
            return cache.get(t);
        }
        final R result = delegate.perform(t);
        cache.put(t, result);
        return result;
    }
}
