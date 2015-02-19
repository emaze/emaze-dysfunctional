package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 * Fetches the second element out of a pair.
 *
 * @param <T1> the first element type
 * @param <T2> the second element type
 * @author rferranti
 */
public class PairSecond<T1, T2> implements Function<Pair<T1, T2>, T2> {

    @Override
    public T2 apply(Pair<T1, T2> pair) {
        dbc.precondition(pair != null, "cannot fetch second from a null pair");
        return pair.second();
    }
}
