package net.emaze.dysfunctional.dispatching.delegates.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;

/**
 * Ternary to binary delegate adapter (lcurry)
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
