package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;

public class FmapTriple<R, T1, T2, T3> implements Delegate<R, Triple<T1, T2, T3>> {

    private final TernaryDelegate<R, T1, T2, T3> delegate;

    public FmapTriple(TernaryDelegate<R, T1, T2, T3> delegate) {
        dbc.precondition(delegate != null, "cannot create FmapTriple with a null delegate");
        this.delegate = delegate;
    }

    @Override
    public R perform(Triple<T1, T2, T3> triple) {
        dbc.precondition(triple != null, "cannot perform FmapTriple on a null triple");
        return triple.fmap(delegate);
    }
}
