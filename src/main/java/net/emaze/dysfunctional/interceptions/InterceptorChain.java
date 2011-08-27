package net.emaze.dysfunctional.interceptions;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 *
 * @param <R>
 * @param <T> 
 * @author rferranti
 */
public class InterceptorChain<R, T> implements Delegate<R, T> {

    private final Iterable<Interceptor<T>> chain;
    private final Delegate<R, T> innermost;

    public InterceptorChain(Delegate<R, T> innermost, Iterable<Interceptor<T>> chain) {
        dbc.precondition(innermost != null, "innermost delegate cannot be null");
        dbc.precondition(chain != null, "chain cannot be null");
        this.innermost = innermost;
        this.chain = chain;
    }

    @Override
    public R perform(T param) {
        Delegate<R, T> current = innermost;
        for (Interceptor<T> interceptor : chain) {
            current = new InterceptorAdapter<R, T>(interceptor, current);
        }
        return current.perform(param);
    }
}
