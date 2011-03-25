package net.emaze.dysfunctional.interceptions;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;

/**
 *
 * @param <R>
 * @param <T1>
 * @param <T2> 
 * @author rferranti
 */
public class BinaryInterceptorAdapter<R, T1, T2> implements BinaryDelegate<R, T1, T2> {

    private final BinaryInterceptor<T1, T2> interceptor;
    private final BinaryDelegate<R, T1, T2> inner;

    public BinaryInterceptorAdapter(BinaryInterceptor<T1, T2> interceptor, BinaryDelegate<R, T1, T2> inner) {
        dbc.precondition(interceptor != null, "cannot adapt a null interceptor");
        dbc.precondition(inner != null, "cannot adato with a null inner delegate");
        this.interceptor = interceptor;
        this.inner = inner;
    }

    @Override
    public R perform(T1 first, T2 second) {
        interceptor.before(first, second);
        try {
            return inner.perform(first, second);
        } finally {
            interceptor.after(first, second);
        }
    }
}
