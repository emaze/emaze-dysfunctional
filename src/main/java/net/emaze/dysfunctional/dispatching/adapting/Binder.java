package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Provider;

/**
 * Unary to nullary delegate adapter. Adapting is performed by currying the
 * parameter of the adapted delegate.
 *
 * @param <R> the adapted delegate result type
 * @param <T> the adapted delegate only element type
 * @author rferranti
 */
public class Binder<R, T> implements Provider<R> {

    private final Delegate<R, T> delegate;
    private final T only;

    public Binder(Delegate<R, T> delegate, T only) {
        dbc.precondition(delegate != null, "cannot bind the parameter of a null delegate");
        this.delegate = delegate;
        this.only = only;
    }

    @Override
    public R provide() {
        return delegate.perform(only);
    }
}
