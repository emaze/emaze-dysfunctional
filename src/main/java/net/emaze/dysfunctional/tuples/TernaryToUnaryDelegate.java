package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;
import net.emaze.dysfunctional.delegates.TernaryDelegate;

/**
 * Adapts a ternary delegate handling triples to a delegate handling triples.
 * @param <R> the result type parameter
 * @param <T1> the first type parameter
 * @param <T2> the second type parameter
 * @param <T3> the third type parameter
 * @author rferranti
 */
public class TernaryToUnaryDelegate<R, T1, T2, T3> implements Delegate<R, Triple<T1, T2, T3>> {

    private final TernaryDelegate<R, T1, T2, T3> delegate;

    public TernaryToUnaryDelegate(TernaryDelegate<R, T1, T2, T3> delegate) {
        dbc.precondition(delegate != null, "cannot create a TernaryToUnaryDelegate with a null TernaryDelegate");
        this.delegate = delegate;
    }

    @Override
    public R perform(Triple<T1, T2, T3> triple) {
        return delegate.perform(triple.first(), triple.second(), triple.third());
    }
}
