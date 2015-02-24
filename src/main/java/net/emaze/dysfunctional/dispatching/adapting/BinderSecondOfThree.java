package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;

/**
 * Ternary to binary delegate adapter. Adapting is performed by currying the
 * second parameter of the adapted ternary delegate.
 *
 * @param <T1> the adapted delegate first parameter type
 * @param <T2> the adapted delegate second parameter type
 * @param <T3> the adapted delegate third parameter type
 * @param <R> the adapted delegate result type
 * @author rferranti
 */
public class BinderSecondOfThree<T1, T2, T3, R> implements BiFunction<T1, T3, R> {

    private final TriFunction<T1, T2, T3, R> adapted;
    private final T2 second;

    public BinderSecondOfThree(TriFunction<T1, T2, T3, R> adaptee, T2 second) {
        dbc.precondition(adaptee != null, "cannot bind second parameter of a null ternary delegate");
        this.adapted = adaptee;
        this.second = second;
    }

    @Override
    public R apply(T1 first, T3 third) {
        return adapted.apply(first, second, third);
    }
}
