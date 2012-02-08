package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Binary to unary delegate adapter. Adapting is performed by currying the first
 * parameter of the adapted binary delegate.
 *
 * @param <R> the adapted delegate result type
 * @param <T> the adapted delegate former element type
 * @param <U> the adapted delegate latter element type
 * @author rferranti
 */
public class BinderFirst<R, T, U> implements Delegate<R, U> {

    private final BinaryDelegate<R, T, U> delegate;
    private final T first;

    public BinderFirst(BinaryDelegate<R, T, U> delegate, T first) {
        dbc.precondition(delegate != null, "cannot bind the first parameter of a null binary delegate");
        this.delegate = delegate;
        this.first = first;
    }

    @Override
    public R perform(U second) {
        return delegate.perform(first, second);
    }
}
