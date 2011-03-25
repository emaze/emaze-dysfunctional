package net.emaze.dysfunctional.dispatching.delegates.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Binary to unary delegate adapter (rcurry)
 * @param <R> the return Type
 * @param <T> the former element Type
 * @param <U> the latter element Type
 * @author rferranti
 */
public class BinderSecond<R, T, U> implements Delegate<R, T> {

    private final BinaryDelegate<R, T, U> delegate;
    private final U second;

    public BinderSecond(BinaryDelegate<R, T, U> delegate, U second) {
        dbc.precondition(delegate != null, "cannot bind the second parameter of a null binary delegate");
        this.delegate = delegate;
        this.second = second;
    }

    @Override
    public R perform(T first) {
        return delegate.perform(first, second);
    }
}
