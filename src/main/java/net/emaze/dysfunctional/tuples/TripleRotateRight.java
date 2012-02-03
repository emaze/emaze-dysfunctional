package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

public class TripleRotateRight<T1, T2, T3> implements Delegate<Triple<T3, T1, T2>, Triple<T1, T2, T3>> {

    @Override
    public Triple<T3, T1, T2> perform(Triple<T1, T2, T3> triple) {
        dbc.precondition(triple != null, "cannot rotate right a null triple");
        return Triple.of(triple.third(), triple.first(), triple.second());
    }
}
