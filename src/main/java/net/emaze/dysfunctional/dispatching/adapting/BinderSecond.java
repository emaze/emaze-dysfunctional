package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import java.util.function.Function;

/**
 * Binary to unary delegate adapter. Adapting is performed by currying the
 * second parameter of the adapted binary delegate.
 *
 * @param <T> the adapted delegate former parameter type
 * @param <U> the adapted delegate latter parameter type
 * @param <R> the adapted delegate result type
 * @author rferranti
 */
public class BinderSecond<T, U, R> implements Function<T, R> {

    private final BinaryDelegate<R, T, U> adapted;
    private final U second;

    public BinderSecond(BinaryDelegate<R, T, U> adaptee, U second) {
        dbc.precondition(adaptee != null, "cannot bind second parameter of a null binary delegate");
        this.adapted = adaptee;
        this.second = second;
    }

    @Override
    public R apply(T first) {
        return adapted.perform(first, second);
    }
}
