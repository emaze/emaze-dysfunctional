package net.emaze.dysfunctional.tuples;

import java.util.function.Function;
import net.emaze.dysfunctional.contracts.dbc;

public class TripleRotateLeft<T1, T2, T3> implements Function<Triple<T1, T2, T3>, Triple<T2, T3, T1>> {

    @Override
    public Triple<T2, T3, T1> apply(Triple<T1, T2, T3> triple) {
        dbc.precondition(triple != null, "cannot rotate left a null triple");
        return Triple.of(triple.second(), triple.third(), triple.first());
    }
}
