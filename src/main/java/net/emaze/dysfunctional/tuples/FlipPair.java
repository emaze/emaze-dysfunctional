package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

public class FlipPair<T1, T2> implements Function<Pair<T1, T2>, Pair<T2, T1>> {

    @Override
    public Pair<T2, T1> apply(Pair<T1, T2> source) {
        dbc.precondition(source != null, "cannot flip a null pair");
        return Pair.of(source.second(), source.first());
    }

}
