package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import java.util.function.Function;

/**
 * Binary to unary delegate adapter. Adapting is performed by currying the first
 * parameter of the adapted binary delegate.
 *
 * @param <T> the adapted delegate former element type
 * @param <U> the adapted delegate latter element type
 * @param <R> the adapted delegate result type
 * @author rferranti
 */
public class BinderFirst<T, U, R> implements Function<U, R> {

    private final BinaryDelegate<R, T, U> adapted;
    private final T first;

    public BinderFirst(BinaryDelegate<R, T, U> adaptee, T first) {
        dbc.precondition(adaptee != null, "cannot bind first parameter of a null binary delegate");
        this.adapted = adaptee;
        this.first = first;
    }

    @Override
    public R apply(U second) {
        return adapted.perform(first, second);
    }
}
