package net.emaze.dysfunctional.interceptions;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.TernaryDelegate;

/**
 *
 * @param <R>
 * @param <T1>
 * @param <T2>
 * @param <T3>
 * @author rferranti
 */
public class TernaryInterceptorAdapter<R, T1, T2, T3> implements TernaryDelegate<R, T1, T2, T3> {

    private final TernaryInterceptor<T1, T2, T3> interceptor;
    private final TernaryDelegate<R, T1, T2, T3> inner;

    public TernaryInterceptorAdapter(TernaryInterceptor<T1, T2, T3> interceptor, TernaryDelegate<R, T1, T2, T3> inner) {
        dbc.precondition(interceptor != null, "cannot adapt a null interceptor");
        dbc.precondition(inner != null, "cannot adato with a null inner delegate");
        this.interceptor = interceptor;
        this.inner = inner;
    }

    @Override
    public R perform(T1 first, T2 second, T3 third) {
        interceptor.before(first, second, third);
        try {
            return inner.perform(first, second, third);
        } finally {
            interceptor.after(first, second, third);
        }
    }
}
