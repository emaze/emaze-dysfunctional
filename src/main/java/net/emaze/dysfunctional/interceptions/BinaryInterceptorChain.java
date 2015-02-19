package net.emaze.dysfunctional.interceptions;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;

/**
 *
 * @param <T1>
 * @param <T2>
 * @param <R>
 * @author rferranti
 */
public class BinaryInterceptorChain<T1, T2, R> implements BiFunction<T1, T2, R> {

    private final BiFunction<T1, T2, R> composed;

    public <I extends BinaryInterceptor<T1, T2>> BinaryInterceptorChain(BiFunction<T1, T2, R> innermost, Iterator<I> chain) {
        dbc.precondition(innermost != null, "innermost delegate cannot be null");
        dbc.precondition(chain != null, "chain cannot be null");
        BiFunction<T1, T2, R> current = innermost;
        while (chain.hasNext()) {
            current = new BinaryInterceptorAdapter<>(chain.next(), current);
        }
        this.composed = current;
    }

    @Override
    public R apply(T1 first, T2 second) {
        return composed.apply(first, second);
    }
}
