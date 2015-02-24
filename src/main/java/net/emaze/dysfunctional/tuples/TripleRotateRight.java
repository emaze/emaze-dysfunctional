package net.emaze.dysfunctional.tuples;

import java.util.function.Function;
import net.emaze.dysfunctional.contracts.dbc;

/**
 *
 * @author rferranti
 * @param <T1>
 * @param <T2>
 * @param <T3>
 */
public class TripleRotateRight<T1, T2, T3> implements Function<Triple<T1, T2, T3>, Triple<T3, T1, T2>> {

    @Override
    public Triple<T3, T1, T2> apply(Triple<T1, T2, T3> triple) {
        dbc.precondition(triple != null, "cannot rotate right a null triple");
        return Triple.of(triple.third(), triple.first(), triple.second());
    }
}
