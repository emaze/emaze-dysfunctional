package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;

/**
 * Adapts a binary function to a ternary function. Adapting is performed by
 * ignoring the second parameter passed to the adapted function.
 *
 * @param <R> the adapter result type
 * @param <T1> the adapter first parameter type
 * @param <T2> the adapter second parameter type
 * @param <T3> the adapter third parameter type
 * @author rferranti
 */
public class IgnoreSecondOfThree<T1, T2, T3, R> implements TriFunction<T1, T2, T3, R> {

    private final BiFunction<T1, T3, R> adapted;

    public IgnoreSecondOfThree(BiFunction<T1, T3, R> adaptee) {
        dbc.precondition(adaptee != null, "cannot ignore second parameter of a null binary function");
        this.adapted = adaptee;
    }

    @Override
    public R apply(T1 first, T2 second, T3 third) {
        return adapted.apply(first, third);
    }
}
