package net.emaze.dysfunctional.interceptions;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;

/**
 * Composes a function with an iterator of interceptors.
 *
 * (function ° interceptor1 ° interceptor2)
 *
 * @param <R> the function result type
 * @param <T1> the function first parameter type
 * @param <T2> the function second parameter type
 * @param <T3> the function third parameter type
 * @author rferranti
 */
public class TernaryInterceptorChain<T1, T2, T3, R> implements TriFunction<T1, T2, T3, R> {

    private final TriFunction<T1, T2, T3, R> composed;

    public <I extends TernaryInterceptor<T1, T2, T3>> TernaryInterceptorChain(TriFunction<T1, T2, T3, R> innermost, Iterator<I> chain) {
        dbc.precondition(innermost != null, "innermost function cannot be null");
        dbc.precondition(chain != null, "chain cannot be null");
        TriFunction<T1, T2, T3, R> current = innermost;
        while (chain.hasNext()) {
            current = new TernaryInterceptorAdapter<T1, T2, T3, R>(chain.next(), current);
        }
        this.composed = current;
    }

    @Override
    public R apply(T1 first, T2 second, T3 third) {
        return composed.apply(first, second, third);
    }
}
