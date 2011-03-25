package net.emaze.dysfunctional.interceptions;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 *
 * @param <R>
 * @param <T>
 * @author rferranti
 */
public class InterceptorAdapter<R, T> implements Delegate<R, T> {

    private final Interceptor<T> interceptor;
    private final Delegate<R, T> inner;

    public InterceptorAdapter(Interceptor<T> interceptor, Delegate<R, T> inner) {
        dbc.precondition(interceptor != null, "cannot adapt a null interceptor");
        dbc.precondition(inner != null, "cannot adato with a null inner delegate");
        this.interceptor = interceptor;
        this.inner = inner;
    }

    @Override
    public R perform(T value) {
        interceptor.before(value);
        try {
            return inner.perform(value);
        } finally {
            interceptor.after(value);
        }
    }
}
