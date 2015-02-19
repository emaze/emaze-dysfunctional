package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

public class FlipTriple<T1, T2, T3> implements Function<Triple<T1, T2, T3>, Triple<T3, T2, T1>> {

    @Override
    public Triple<T3, T2, T1> apply(Triple<T1, T2, T3> triple) {
        dbc.precondition(triple != null, "cannot flip a null triple");
        return Triple.of(triple.third(), triple.second(), triple.first());
    }
}
