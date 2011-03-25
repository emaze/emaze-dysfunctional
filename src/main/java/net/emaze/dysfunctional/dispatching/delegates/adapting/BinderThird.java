package net.emaze.dysfunctional.dispatching.delegates.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;

/**
 * Ternary to binary delegate adapter (rcurry)
 * @author rferranti
 */
public class BinderThird<R, T1, T2, T3> implements BinaryDelegate<R, T1, T2> {

    private final TernaryDelegate<R, T1, T2, T3> delegate;
    private final T3 third;

    public BinderThird(TernaryDelegate<R, T1, T2, T3> delegate, T3 third) {
        dbc.precondition(delegate != null, "cannot bind the third parameter of a null ternary delegate");
        this.delegate = delegate;
        this.third = third;
    }

    @Override
    public R perform(T1 first, T2 second) {
        return delegate.perform(first, second, third);
    }
}
