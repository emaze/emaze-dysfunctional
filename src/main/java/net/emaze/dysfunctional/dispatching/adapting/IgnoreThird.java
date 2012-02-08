package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;

/**
 * Adapts a binary delegate to a Ternary delegate. Adapting is performed by
 * ignoring the third parameter passed to the adapted delegate.
 *
 * @param <R> the adapter result type
 * @param <T1> the adapter first parameter type
 * @param <T2> the adapter second parameter type
 * @param <T3> the adapter third parameter type
 * @author rferranti
 */
public class IgnoreThird<R, T1, T2, T3> implements TernaryDelegate<R, T1, T2, T3> {

    private final BinaryDelegate<R, T1, T2> delegate;

    public IgnoreThird(BinaryDelegate<R, T1, T2> delegate) {
        dbc.precondition(delegate != null, "cannot ignore the third parameter of a null delegate");
        this.delegate = delegate;
    }

    @Override
    public R perform(T1 first, T2 second, T3 third) {
        return delegate.perform(first, second);
    }
}
