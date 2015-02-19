package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import java.util.function.Function;

/**
 * Adapts a binary delegate to a delegate handling pairs.
 *
 * @param <T1> the former type parameter
 * @param <T2> the latter type parameter
 * @param <R> the result type parameter
 * @author rferranti
 */
public class BinaryToUnaryDelegate<T1, T2, R> implements Function<Pair<T1, T2>, R> {

    private final BinaryDelegate<R, T1, T2> delegate;

    public BinaryToUnaryDelegate(BinaryDelegate<R, T1, T2> delegate) {
        dbc.precondition(delegate != null, "cannot create a BinaryToUnaryDelegate with a null BinaryDelegate");
        this.delegate = delegate;
    }

    @Override
    public R apply(Pair<T1, T2> pair) {
        return delegate.perform(pair.first(), pair.second());
    }
}
