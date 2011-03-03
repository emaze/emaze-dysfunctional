package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;

/**
 * Fetches the first element out of a triple.
 * @param <T1> the first element type
 * @param <T2> the second element type
 * @param <T3> the third element type
 * @author rferranti
 */
public class TripleFirst<T1, T2, T3> implements Delegate<T1, Triple<T1, T2, T3>> {

    @Override
    public T1 perform(Triple<T1, T2, T3> triple) {
        dbc.precondition(triple != null, "cannot fetch first from a null triple");
        return triple.first();
    }
}
