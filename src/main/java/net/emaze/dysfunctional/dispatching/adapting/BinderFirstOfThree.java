package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;

/**
 * Ternary to binary delegate adapter. Adapting is performed by currying the
 * first parameter of the adapted ternary delegate.
 *
 * @param <R> the result type
 * @param <T1> the adapted delegate first parameter type
 * @param <T2> the adapted delegate second parameter type
 * @param <T3> the adapted delegate third parameter type
 * @author rferranti
 */
public class BinderFirstOfThree<R, T1, T2, T3> implements BinaryDelegate<R, T2, T3> {

    private final TernaryDelegate<R, T1, T2, T3> delegate;
    private final T1 first;

    public BinderFirstOfThree(TernaryDelegate<R, T1, T2, T3> delegate, T1 first) {
        dbc.precondition(delegate != null, "cannot bind the first parameter of a null ternary delegate");
        this.delegate = delegate;
        this.first = first;
    }

    @Override
    public R perform(T2 second, T3 third) {
        return delegate.perform(first, second, third);
    }
}
