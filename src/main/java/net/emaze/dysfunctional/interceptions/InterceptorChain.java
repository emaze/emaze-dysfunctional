package net.emaze.dysfunctional.interceptions;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Composes a delegate with an iterator of interceptors.
 *
 * (delegate ° interceptor1 ° interceptor2)
 *
 * @param <R> the delegate result type
 * @param <T> the delegate parameter type
 * @author rferranti
 */
public class InterceptorChain<R, T> implements Delegate<R, T> {

    private final Delegate<R, T> composed;

    public <I extends Interceptor<T>> InterceptorChain(Delegate<R, T> innermost, Iterator<I> chain) {
        dbc.precondition(innermost != null, "innermost delegate cannot be null");
        dbc.precondition(chain != null, "chain cannot be null");
        Delegate<R, T> current = innermost;
        while (chain.hasNext()) {
            current = new InterceptorAdapter<R, T>(chain.next(), current);
        }
        this.composed = current;
    }

    @Override
    public R perform(T param) {
        return composed.perform(param);
    }
}
