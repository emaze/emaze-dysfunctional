package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Fetches the first element out of a pair.
 * @param <T1> the first element type
 * @param <T2> the second element type
 * @author rferranti
 */
public class PairFirst<T1,T2> implements Delegate<T1, Pair<T1,T2>> {

    @Override
    public T1 perform(Pair<T1, T2> pair) {
        dbc.precondition(pair != null, "cannot fetch first from a null pair");
        return pair.first();
    }

}
