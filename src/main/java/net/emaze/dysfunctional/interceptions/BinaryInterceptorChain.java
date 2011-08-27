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
public class BinaryInterceptorChain<R, T1, T2> implements BinaryDelegate<R, T1, T2> {

    private final Iterable<BinaryInterceptor<T1, T2>> chain;
    private final BinaryDelegate<R, T1, T2> innermost;

    public BinaryInterceptorChain(BinaryDelegate<R, T1, T2> innermost, Iterable<BinaryInterceptor<T1, T2>> chain) {
        dbc.precondition(innermost != null, "innermost delegate cannot be null");
        dbc.precondition(chain != null, "chain cannot be null");
        this.innermost = innermost;
        this.chain = chain;
    }

    @Override
    public R perform(T1 first, T2 second) {
        BinaryDelegate<R, T1, T2> current = innermost;
        for (BinaryInterceptor<T1, T2> interceptor : chain) {
            current = new BinaryInterceptorAdapter<R, T1, T2>(interceptor, current);
        }
        return current.perform(first, second);
    }
}
