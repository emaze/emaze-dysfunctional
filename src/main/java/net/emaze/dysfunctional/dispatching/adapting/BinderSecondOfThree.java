package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;

/**
 * Ternary to binary delegate adapter. Adapting is performed by currying the
 * second parameter of the adapted ternary delegate.
 *
 * @param <R> the adapted delegate result type
 * @param <T1> the adapted delegate first parameter type
 * @param <T2> the adapted delegate second parameter type
 * @param <T3> the adapted delegate third parameter type
 * @author rferranti
 */
public class BinderSecondOfThree<R, T1, T2, T3> implements BinaryDelegate<R, T1, T3> {

    private final TernaryDelegate<R, T1, T2, T3> delegate;
    private final T2 second;

    public BinderSecondOfThree(TernaryDelegate<R, T1, T2, T3> delegate, T2 second) {
        dbc.precondition(delegate != null, "cannot bind the second parameter of a null ternary delegate");
        this.delegate = delegate;
        this.second = second;
    }

    @Override
    public R perform(T1 first, T3 third) {
        return delegate.perform(first, second, third);
    }
}
