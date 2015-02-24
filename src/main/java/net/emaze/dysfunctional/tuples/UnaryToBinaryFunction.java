package net.emaze.dysfunctional.tuples;

import java.util.function.BiFunction;
import java.util.function.Function;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Adapts a unary function handling pairs to a binary function.
 *
 * @param <T1> the former type parameter
 * @param <T2> the latter type parameter
 * @param <R> the result type parameter
 * @author rferranti
 */
public class UnaryToBinaryFunction<T1, T2, R> implements BiFunction<T1, T2, R> {

    private final Function<Pair<T1, T2>, R> function;

    public UnaryToBinaryFunction(Function<Pair<T1, T2>, R> function) {
        dbc.precondition(function != null, "cannot create a UnaryToBinaryFunction with a null function");
        this.function = function;
    }

    @Override
    public R apply(T1 first, T2 second) {
        return function.apply(Pair.of(first, second));
    }
}
