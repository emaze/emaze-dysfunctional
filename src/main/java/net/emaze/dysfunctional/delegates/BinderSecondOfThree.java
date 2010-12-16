package net.emaze.dysfunctional.delegates;

import net.emaze.dysfunctional.contracts.dbc;

/**
 * Ternary to binary delegate adapter (binds the second parameter in construction)
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
