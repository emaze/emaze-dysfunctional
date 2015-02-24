package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;

/**
 * Ternary to binary function adapter. Adapting is performed by currying the
 * second parameter of the adapted ternary function.
 *
 * @param <T1> the adapted function first parameter type
 * @param <T2> the adapted function second parameter type
 * @param <T3> the adapted function third parameter type
 * @param <R> the adapted function result type
 * @author rferranti
 */
public class BinderSecondOfThree<T1, T2, T3, R> implements BiFunction<T1, T3, R> {

    private final TriFunction<T1, T2, T3, R> adapted;
    private final T2 second;

    public BinderSecondOfThree(TriFunction<T1, T2, T3, R> adaptee, T2 second) {
        dbc.precondition(adaptee != null, "cannot bind second parameter of a null ternary function");
        this.adapted = adaptee;
        this.second = second;
    }

    @Override
    public R apply(T1 first, T3 third) {
        return adapted.apply(first, second, third);
    }
}
