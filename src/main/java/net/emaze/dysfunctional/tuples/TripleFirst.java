package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;

/**
 *
 * @param <T1>
 * @param <T2> 
 * @param <T3> 
 * @author rferranti
 */
public class TripleFirst<T1, T2, T3> implements Delegate<T1, Triple<T1, T2, T3>> {

    @Override
    public T1 perform(Triple<T1, T2, T3> triple) {
        dbc.precondition(triple != null, "cannot fetch first from a null triple");
        return triple.first();
    }
}
