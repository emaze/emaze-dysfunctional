package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Performs fmap on a triple.
 *
 * @author rferranti
 * @param <R1> the mapped triple first type
 * @param <R2> the mapped triple second type
 * @param <R3> the mapped triple third type
 * @param <T1> the triple first type
 * @param <T2> the triple second type
 * @param <T3> the triple third type
 */
public class FmapTriple<R1, R2, R3, T1, T2, T3> implements Delegate<Triple<R1, R2, R3>, Triple<T1, T2, T3>> {

    private final Delegate<R1, T1> first;
    private final Delegate<R2, T2> second;
    private final Delegate<R3, T3> third;

    public FmapTriple(Delegate<R1, T1> first, Delegate<R2, T2> second, Delegate<R3, T3> third) {
        dbc.precondition(first != null, "cannot create FmapTriple with a null first delegate");
        dbc.precondition(second != null, "cannot create FmapTriple with a null second delegate");
        dbc.precondition(third != null, "cannot create FmapTriple with a null third delegate");
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public Triple<R1, R2, R3> perform(Triple<T1, T2, T3> from) {
        dbc.precondition(from != null, "cannot fmap a null triple");
        return from.fmap(first, second, third);
    }
}
