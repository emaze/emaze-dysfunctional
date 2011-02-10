package net.emaze.dysfunctional.delegates;

import net.emaze.dysfunctional.contracts.dbc;

/**
 * Binary to 0-arity delegate adapter
 * @param <R> the return Type
 * @param <T> the only element Type
 * @author rferranti
 */
public class Binder<R, T> implements Provider<R> {

    private final Delegate<R, T> delegate;
    private final T only;

    public Binder(Delegate<R, T> delegate, T only) {
        dbc.precondition(delegate != null, "cannot bind the first parameter of a null binary delegate");
        this.delegate = delegate;
        this.only = only;
    }

    @Override
    public R provide() {
        return delegate.perform(only);
    }
}
