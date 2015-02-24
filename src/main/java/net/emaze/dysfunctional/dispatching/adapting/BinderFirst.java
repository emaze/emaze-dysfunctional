package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Binary to unary function adapter. Adapting is performed by currying the first
 * parameter of the adapted binary function.
 *
 * @param <T> the adapted function former element type
 * @param <U> the adapted function latter element type
 * @param <R> the adapted function result type
 * @author rferranti
 */
public class BinderFirst<T, U, R> implements Function<U, R> {

    private final BiFunction<T, U, R> adapted;
    private final T first;

    public BinderFirst(BiFunction<T, U, R> adaptee, T first) {
        dbc.precondition(adaptee != null, "cannot bind first parameter of a null binary function");
        this.adapted = adaptee;
        this.first = first;
    }

    @Override
    public R apply(U second) {
        return adapted.apply(first, second);
    }
}
