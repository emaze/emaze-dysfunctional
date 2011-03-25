package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Adapts a binary delegate to a delegate handling pairs.
 * @param <R> the result type parameter
 * @param <T1> the former type parameter
 * @param <T2> the latter type parameter
 * @author rferranti
 */
public class BinaryToUnaryDelegate<R, T1, T2> implements Delegate<R, Pair<T1, T2>> {

    private final BinaryDelegate<R, T1, T2> delegate;

    public BinaryToUnaryDelegate(BinaryDelegate<R, T1, T2> delegate) {
        dbc.precondition(delegate != null, "cannot create a BinaryToUnaryDelegate with a null BinaryDelegate");
        this.delegate = delegate;
    }

    @Override
    public R perform(Pair<T1, T2> pair) {
        return delegate.perform(pair.first(), pair.second());
    }
}
