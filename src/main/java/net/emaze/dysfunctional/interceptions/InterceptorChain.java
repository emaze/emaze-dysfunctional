package net.emaze.dysfunctional.interceptions;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 * Composes a function with an iterator of interceptors.
 *
 * (function ° interceptor1 ° interceptor2)
 *
 * @param <T> the function parameter type
 * @param <R> the function result type
 * @author rferranti
 */
public class InterceptorChain<T, R> implements Function<T, R> {

    private final Function<T, R> composed;

    public <I extends Interceptor<T>> InterceptorChain(Function<T, R> innermost, Iterator<I> chain) {
        dbc.precondition(innermost != null, "innermost function cannot be null");
        dbc.precondition(chain != null, "chain cannot be null");
        Function<T, R> current = innermost;
        while (chain.hasNext()) {
            current = new InterceptorAdapter<>(chain.next(), current);
        }
        this.composed = current;
    }

    @Override
    public R apply(T param) {
        return composed.apply(param);
    }
}
