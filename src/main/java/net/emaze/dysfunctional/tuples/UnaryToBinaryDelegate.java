package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import java.util.function.Function;

/**
 * Adapts a unary delegate handling pairs to a binary delegate.
 *
 * @param <R> the result type parameter
 * @param <T1> the former type parameter
 * @param <T2> the latter type parameter
 * @author rferranti
 */
public class UnaryToBinaryDelegate<R, T1, T2> implements BinaryDelegate<R, T1, T2> {

    private final Function<Pair<T1, T2>, R> delegate;

    public UnaryToBinaryDelegate(Function<Pair<T1, T2>, R> delegate) {
        dbc.precondition(delegate != null, "cannot create a UnaryToBinaryDelegate with a null Delegate");
        this.delegate = delegate;
    }

    @Override
    public R perform(T1 first, T2 second) {
        return delegate.apply(Pair.of(first, second));
    }
}
