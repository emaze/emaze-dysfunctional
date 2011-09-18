package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 *
 * @author rferranti
 */
public class WithJust<R, T> implements Delegate<Maybe<R>, Maybe<T>> {

    private final Delegate<R, T> delegate;

    public WithJust(Delegate<R, T> delegate) {
        dbc.precondition(delegate != null, "cannot create WithJust with a null delegate");
        this.delegate = delegate;
    }

    @Override
    public Maybe<R> perform(Maybe<T> from) {
        return from.withValue(delegate);
    }
}
