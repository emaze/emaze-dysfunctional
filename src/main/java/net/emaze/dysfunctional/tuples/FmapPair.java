package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 * Performs fmap on a pair.
 *
 * @author rferranti
 * @param <T1> the source pair first type
 * @param <T2> the source pair second type
 * @param <R1> the result pair first type
 * @param <R2>the result pair second type
 */
public class FmapPair<T1, T2, R1, R2> implements Function<Pair<T1, T2>, Pair<R1, R2>> {

    private final Function<T1, R1> first;
    private final Function<T2, R2> second;

    public FmapPair(Function<T1, R1> first, Function<T2, R2> second) {
        dbc.precondition(first != null, "cannot create FmapPair with a null first delegate");
        dbc.precondition(second != null, "cannot create FmapPair with a null second delegate");
        this.first = first;
        this.second = second;
    }

    @Override
    public Pair<R1, R2> apply(Pair<T1, T2> from) {
        dbc.precondition(from != null, "cannot fmap a null pair");
        return from.fmap(first, second);
    }
}
