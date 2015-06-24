package net.emaze.dysfunctional.interceptions;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 * Adapts a unary interceptor to a unary function.
 *
 * @param <T> the function parameter type
 * @param <R> the function result type
 * @author rferranti
 */
public class InterceptorAdapter<T, R> implements Function<T, R> {

    private final Interceptor<T> interceptor;
    private final Function<T, R> inner;

    public InterceptorAdapter(Interceptor<T> interceptor, Function<T, R> inner) {
        dbc.precondition(interceptor != null, "cannot adapt a null interceptor");
        dbc.precondition(inner != null, "cannot adapt with a null inner function");
        this.interceptor = interceptor;
        this.inner = inner;
    }

    @Override
    public R apply(T value) {
        interceptor.before(value);
        try {
            return inner.apply(value);
        } finally {
            interceptor.after(value);
        }
    }
}
