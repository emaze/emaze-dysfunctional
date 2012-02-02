package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

public class LiftDelegate<R, T> implements Delegate<Maybe<R>, Maybe<T>> {

    private final Delegate<R, T> nested;

    public LiftDelegate(Delegate<R, T> nested) {
        dbc.precondition(nested != null, "cannot create a LiftDelegate with a null delegate");
        this.nested = nested;
    }

    @Override
    public Maybe<R> perform(Maybe<T> maybe) {
        dbc.precondition(maybe != null, "cannot perform lift with a null maybe");
        return maybe.fmap(nested);
    }
}
