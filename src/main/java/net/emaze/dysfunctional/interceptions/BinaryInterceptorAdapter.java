package net.emaze.dysfunctional.interceptions;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;

/**
 * Adapts a binary interceptor to binary delegate.
 *
 * @param <R> the delegate result type
 * @param <T1> the delegate first parameter type
 * @param <T2> the delegate second parameter type
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

    /**
     * Executes a delegate in the nested interceptor context.
     *
     * @param first
     * @param second
     * @return
     */
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
