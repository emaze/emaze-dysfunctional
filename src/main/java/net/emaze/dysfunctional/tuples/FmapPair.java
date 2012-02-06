package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Performs fmap on a pair.
 *
 * @author rferranti
 * @param <R1> the result pair first type
 * @param <R2>the result pair second type
 * @param <T1> the source pair first type
 * @param <T2> the source pair second type
 */
public class FmapPair<R1, R2, T1, T2> implements Delegate<Pair<R1, R2>, Pair<T1, T2>> {

    private final Delegate<R1, T1> first;
    private final Delegate<R2, T2> second;

    public FmapPair(Delegate<R1, T1> first, Delegate<R2, T2> second) {
        dbc.precondition(first != null, "cannot create FmapPair with a null first delegate");
        dbc.precondition(second != null, "cannot create FmapPair with a null second delegate");
        this.first = first;
        this.second = second;
    }

    @Override
    public Pair<R1, R2> perform(Pair<T1, T2> from) {
        dbc.precondition(from != null, "cannot fmap a null pair");
        return from.fmap(first, second);
    }
}
