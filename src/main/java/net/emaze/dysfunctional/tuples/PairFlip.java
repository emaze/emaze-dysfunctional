package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

public class PairFlip<T1, T2> implements Delegate<Pair<T2, T1>, Pair<T1, T2>>{

    @Override
    public Pair<T2, T1> perform(Pair<T1, T2> source) {
        dbc.precondition(source != null, "cannot flip a null pair");
        return Pair.of(source.second(), source.first());
    }

}
