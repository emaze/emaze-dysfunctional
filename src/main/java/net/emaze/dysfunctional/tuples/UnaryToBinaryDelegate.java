package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Adapts a unary delegate handling pairs to a binary delegate.
 *
 * @param <T1> the former type parameter
 * @param <T2> the latter type parameter
 * @param <R> the result type parameter
 * @author rferranti
 */
public class UnaryToBinaryDelegate<T1, T2, R> implements BiFunction<T1, T2, R> {

    private final Function<Pair<T1, T2>, R> delegate;

    public UnaryToBinaryDelegate(Function<Pair<T1, T2>, R> delegate) {
        dbc.precondition(delegate != null, "cannot create a UnaryToBinaryDelegate with a null Delegate");
        this.delegate = delegate;
    }

    @Override
    public R apply(T1 first, T2 second) {
        return delegate.apply(Pair.of(first, second));
    }
}
