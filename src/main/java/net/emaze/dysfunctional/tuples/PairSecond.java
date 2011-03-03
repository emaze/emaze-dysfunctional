package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;

/**
 *
 * @param <T1>
 * @param <T2> 
 * @author rferranti
 */
public class PairSecond<T1, T2> implements Delegate<T2, Pair<T1, T2>> {

    @Override
    public T2 perform(Pair<T1, T2> pair) {
        dbc.precondition(pair != null, "cannot fetch second from a null pair");
        return pair.second();
    }
}
