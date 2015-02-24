package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;

/**
 * Ternary to binary delegate adapter. Adapting is performed by currying the
 * first parameter of the adapted ternary delegate.
 *
 * @param <T1> the adapted delegate first parameter type
 * @param <T2> the adapted delegate second parameter type
 * @param <T3> the adapted delegate third parameter type
 * @param <R> the result type
 * @author rferranti
 */
public class BinderFirstOfThree<T1, T2, T3, R> implements BiFunction<T2, T3, R> {

    private final TriFunction<T1, T2, T3, R> adapted;
    private final T1 first;

    public BinderFirstOfThree(TriFunction<T1, T2, T3, R> adaptee, T1 first) {
        dbc.precondition(adaptee != null, "cannot bind first parameter of a null ternary delegate");
        this.adapted = adaptee;
        this.first = first;
    }

    @Override
    public R apply(T2 second, T3 third) {
        return adapted.apply(first, second, third);
    }
}
