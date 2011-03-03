package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;

/**
 * Fetches the second element out of a pair.
 * @param <T1> the first element type
 * @param <T2> the second element type
 * @author rferranti
 */
public class PairSecond<T1, T2> implements Delegate<T2, Pair<T1, T2>> {

    @Override
    public T2 perform(Pair<T1, T2> pair) {
        dbc.precondition(pair != null, "cannot fetch second from a null pair");
        return pair.second();
    }
}
