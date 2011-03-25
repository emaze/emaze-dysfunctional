package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Fetches the second element out of a triple.
 * @param <T1> the first element type
 * @param <T2> the second element type
 * @param <T3> the third element type
 * @author rferranti
 */
public class TripleSecond<T1, T2, T3> implements Delegate<T2, Triple<T1, T2, T3>> {

    @Override
    public T2 perform(Triple<T1, T2, T3> triple) {
        dbc.precondition(triple != null, "cannot fetch second from a null triple");
        return triple.second();
    }
}
