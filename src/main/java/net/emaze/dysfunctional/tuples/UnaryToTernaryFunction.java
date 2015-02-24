package net.emaze.dysfunctional.tuples;

import java.util.function.Function;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;

/**
 * Adapts a unary function handling triples to a ternary function.
 *
 * @param <R> the result type parameter
 * @param <T1> the first type parameter
 * @param <T2> the second type parameter
 * @param <T3> the third type parameter
 * @author rferranti
 */
public class UnaryToTernaryFunction<T1, T2, T3, R> implements TriFunction<T1, T2, T3, R> {

    private final Function<Triple<T1, T2, T3>, R> function;

    public UnaryToTernaryFunction(Function<Triple<T1, T2, T3>, R> function) {
        dbc.precondition(function != null, "cannot create a UnaryToTernaryFunction with a null function");
        this.function = function;
    }

    @Override
    public R apply(T1 first, T2 second, T3 third) {
        return function.apply(Triple.of(first, second, third));
    }
}
