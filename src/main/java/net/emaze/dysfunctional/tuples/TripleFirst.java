package net.emaze.dysfunctional.tuples;

import java.util.function.Function;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Fetches the first element out of a triple.
 *
 * @param <T1> the first element type
 * @param <T2> the second element type
 * @param <T3> the third element type
 * @author rferranti
 */
public class TripleFirst<T1, T2, T3> implements Function<Triple<T1, T2, T3>, T1> {

    @Override
    public T1 apply(Triple<T1, T2, T3> triple) {
        dbc.precondition(triple != null, "cannot fetch first from a null triple");
        return triple.first();
    }
}
