package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 * Performs fmap on a triple.
 *
 * @author rferranti
 * @param <T1> the triple first type
 * @param <T2> the triple second type
 * @param <T3> the triple third type
 * @param <R1> the mapped triple first type
 * @param <R2> the mapped triple second type
 * @param <R3> the mapped triple third type
 */
public class FmapTriple<T1, T2, T3, R1, R2, R3> implements Function<Triple<T1, T2, T3>, Triple<R1, R2, R3>> {

    private final Function<T1, R1> first;
    private final Function<T2, R2> second;
    private final Function<T3, R3> third;

    public FmapTriple(Function<T1, R1> first, Function<T2, R2> second, Function<T3, R3> third) {
        dbc.precondition(first != null, "cannot create FmapTriple with a null first delegate");
        dbc.precondition(second != null, "cannot create FmapTriple with a null second delegate");
        dbc.precondition(third != null, "cannot create FmapTriple with a null third delegate");
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public Triple<R1, R2, R3> apply(Triple<T1, T2, T3> from) {
        dbc.precondition(from != null, "cannot fmap a null triple");
        return from.fmap(first, second, third);
    }
}
