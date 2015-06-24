package net.emaze.dysfunctional.interceptions;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;

/**
 * Adapts a binary interceptor to binary function.
 *
 * @param <T1> the function first parameter type
 * @param <T2> the function second parameter type
 * @param <R> the function result type
 * @author rferranti
 */
public class BinaryInterceptorAdapter<T1, T2, R> implements BiFunction<T1, T2, R> {

    private final BinaryInterceptor<T1, T2> interceptor;
    private final BiFunction<T1, T2, R> inner;

    public BinaryInterceptorAdapter(BinaryInterceptor<T1, T2> interceptor, BiFunction<T1, T2, R> inner) {
        dbc.precondition(interceptor != null, "cannot adapt a null interceptor");
        dbc.precondition(inner != null, "cannot adato with a null inner function");
        this.interceptor = interceptor;
        this.inner = inner;
    }

    /**
     * Executes a function in the nested interceptor context.
     *
     * @param first
     * @param second
     * @return
     */
    @Override
    public R apply(T1 first, T2 second) {
        interceptor.before(first, second);
        try {
            return inner.apply(first, second);
        } finally {
            interceptor.after(first, second);
        }
    }
}
