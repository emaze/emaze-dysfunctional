package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;

/**
 *
 * @param <T1>
 * @param <T2> 
 * @author rferranti
 */
public class PairFirst<T1,T2> implements Delegate<T1, Pair<T1,T2>> {

    @Override
    public T1 perform(Pair<T1, T2> pair) {
        dbc.precondition(pair != null, "cannot fetch first from a null pair");
        return pair.first();
    }

}
