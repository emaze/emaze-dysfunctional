package net.emaze.dysfunctional.interceptions;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;

/**
 * A ternary interceptor to ternary function adapter.
 *
 * @param <R> the function result type
 * @param <T1> the function first parameter type
 * @param <T2> the function second parameter type
 * @param <T3> the function third parameter type
 * @author rferranti
 */
public class TernaryInterceptorAdapter<T1, T2, T3, R> implements TriFunction<T1, T2, T3, R> {

    private final TernaryInterceptor<T1, T2, T3> interceptor;
    private final TriFunction<T1, T2, T3, R> inner;

    public TernaryInterceptorAdapter(TernaryInterceptor<T1, T2, T3> interceptor, TriFunction<T1, T2, T3, R> inner) {
        dbc.precondition(interceptor != null, "cannot adapt a null interceptor");
        dbc.precondition(inner != null, "cannot adato with a null inner function");
        this.interceptor = interceptor;
        this.inner = inner;
    }

    @Override
    public R apply(T1 first, T2 second, T3 third) {
        interceptor.before(first, second, third);
        try {
            return inner.apply(first, second, third);
        } finally {
            interceptor.after(first, second, third);
        }
    }
}
