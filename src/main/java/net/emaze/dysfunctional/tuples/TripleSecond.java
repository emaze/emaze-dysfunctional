package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 * Fetches the second element out of a triple.
 * @param <T1> the first element type
 * @param <T2> the second element type
 * @param <T3> the third element type
 * @author rferranti
 */
public class TripleSecond<T1, T2, T3> implements Function<Triple<T1, T2, T3>, T2> {

    @Override
    public T2 apply(Triple<T1, T2, T3> triple) {
        dbc.precondition(triple != null, "cannot fetch second from a null triple");
        return triple.second();
    }
}
