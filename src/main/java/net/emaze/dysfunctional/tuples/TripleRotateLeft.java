package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

public class TripleRotateLeft<T1, T2, T3> implements Delegate<Triple<T2, T3, T1>, Triple<T1, T2, T3>> {

    @Override
    public Triple<T2, T3, T1> perform(Triple<T1, T2, T3> triple) {
        dbc.precondition(triple != null, "cannot rotate left a null triple");
        return Triple.of(triple.second(), triple.third(), triple.first());
    }
}
