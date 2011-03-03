package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;

/**
 * Fetches the third element out of a triple.
 * @param <T1> the first element type
 * @param <T2> the second element type
 * @param <T3> the third element type
 * @author rferranti
 */
public class TripleThird<T1, T2, T3> implements Delegate<T3, Triple<T1, T2, T3>> {

    @Override
    public T3 perform(Triple<T1, T2, T3> triple) {
        dbc.precondition(triple != null, "cannot fetch third from a null triple");
        return triple.third();
    }
}
