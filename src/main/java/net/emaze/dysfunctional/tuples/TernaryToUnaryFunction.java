package net.emaze.dysfunctional.tuples;

import java.util.function.Function;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;

/**
 * Adapts a ternary function handling triples to a function handling triples.
 *
 * @param <T1> the first type parameter
 * @param <T2> the second type parameter
 * @param <T3> the third type parameter
 * @param <R> the result type parameter
 * @author rferranti
 */
public class TernaryToUnaryFunction<T1, T2, T3, R> implements Function<Triple<T1, T2, T3>, R> {

    private final TriFunction<T1, T2, T3, R> function;

    public TernaryToUnaryFunction(TriFunction<T1, T2, T3, R> function) {
        dbc.precondition(function != null, "cannot create a TernaryToUnaryFunction with a null function");
        this.function = function;
    }

    @Override
    public R apply(Triple<T1, T2, T3> triple) {
        return function.apply(triple.first(), triple.second(), triple.third());
    }
}
