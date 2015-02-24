package net.emaze.dysfunctional.tuples;

import java.util.function.BiFunction;
import java.util.function.Function;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Adapts a binary function to a function handling pairs.
 *
 * @param <T1> the former type parameter
 * @param <T2> the latter type parameter
 * @param <R> the result type parameter
 * @author rferranti
 */
public class BinaryToUnaryFunction<T1, T2, R> implements Function<Pair<T1, T2>, R> {

    private final BiFunction<T1, T2, R> function;

    public BinaryToUnaryFunction(BiFunction<T1, T2, R> function) {
        dbc.precondition(function != null, "cannot create a BinaryToUnaryFunction with a null function");
        this.function = function;
    }

    @Override
    public R apply(Pair<T1, T2> pair) {
        return function.apply(pair.first(), pair.second());
    }
}
