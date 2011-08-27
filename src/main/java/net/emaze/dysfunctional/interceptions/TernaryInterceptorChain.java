package net.emaze.dysfunctional.interceptions;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;

/**
 *
 * @param <R>
 * @param <T1>
 * @param <T2>
 * @param <T3> 
 * @author rferranti
 */
public class TernaryInterceptorChain<R, T1, T2, T3> implements TernaryDelegate<R, T1, T2, T3> {

    private final Iterable<TernaryInterceptor<T1, T2, T3>> chain;
    private final TernaryDelegate<R, T1, T2, T3> innermost;

    public TernaryInterceptorChain(TernaryDelegate<R, T1, T2, T3> innermost, Iterable<TernaryInterceptor<T1, T2, T3>> chain) {
        dbc.precondition(innermost != null, "innermost delegate cannot be null");
        dbc.precondition(chain != null, "chain cannot be null");
        this.innermost = innermost;
        this.chain = chain;
    }

    @Override
    public R perform(T1 first, T2 second, T3 third) {
        TernaryDelegate<R, T1, T2, T3> current = innermost;
        for (TernaryInterceptor<T1, T2, T3> interceptor : chain) {
            current = new TernaryInterceptorAdapter<R, T1, T2, T3>(interceptor, current);
        }
        return current.perform(first, second, third);
    }
}
