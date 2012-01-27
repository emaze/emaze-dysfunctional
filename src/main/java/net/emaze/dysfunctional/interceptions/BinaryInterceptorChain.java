package net.emaze.dysfunctional.interceptions;

import java.util.Iterator;
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

    private final BinaryDelegate<R, T1, T2> composed;

    public <I extends BinaryInterceptor<T1, T2>> BinaryInterceptorChain(BinaryDelegate<R, T1, T2> innermost, Iterator<I> chain) {
        dbc.precondition(innermost != null, "innermost delegate cannot be null");
        dbc.precondition(chain != null, "chain cannot be null");
        BinaryDelegate<R, T1, T2> current = innermost;
        while (chain.hasNext()) {
            current = new BinaryInterceptorAdapter<R, T1, T2>(chain.next(), current);
        }
        this.composed = current;
    }

    @Override
    public R perform(T1 first, T2 second) {
        return composed.perform(first, second);
    }
}
