package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 * Fetches the first element out of a pair.
 *
 * @param <T1> the first element type
 * @param <T2> the second element type
 * @author rferranti
 */
public class PairFirst<T1, T2> implements Function<Pair<T1, T2>, T1> {

    @Override
    public T1 apply(Pair<T1, T2> pair) {
        dbc.precondition(pair != null, "cannot fetch first from a null pair");
        return pair.first();
    }
}
