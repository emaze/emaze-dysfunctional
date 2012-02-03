package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

public class FmapPair<R, T1, T2> implements Delegate<R, Pair<T1, T2>> {

    private final BinaryDelegate<R, T1, T2> delegate;

    public FmapPair(BinaryDelegate<R, T1, T2> delegate) {
        dbc.precondition(delegate != null, "cannot create FmapPair with a null delegate");
        this.delegate = delegate;
    }

    @Override
    public R perform(Pair<T1, T2> pair) {
        dbc.precondition(pair != null, "cannot perform FmapPair on a null pair");
        return pair.fmap(delegate);
    }
}
