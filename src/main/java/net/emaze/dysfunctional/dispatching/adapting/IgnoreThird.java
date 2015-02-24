package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;

/**
 * Adapts a binary delegate to a Ternary delegate. Adapting is performed by
 * ignoring the third parameter passed to the adapted delegate.
 *
 * @param <R> the adapter result type
 * @param <T1> the adapter first parameter type
 * @param <T2> the adapter second parameter type
 * @param <T3> the adapter third parameter type
 * @author rferranti
 */
public class IgnoreThird<T1, T2, T3, R> implements TriFunction<T1, T2, T3, R> {

    private final BiFunction<T1, T2, R> adapted;

    public IgnoreThird(BiFunction<T1, T2, R> adaptee) {
        dbc.precondition(adaptee != null, "cannot ignore third parameter of a null binary delegate");
        this.adapted = adaptee;
    }

    @Override
    public R apply(T1 first, T2 second, T3 third) {
        return adapted.apply(first, second);
    }
}
