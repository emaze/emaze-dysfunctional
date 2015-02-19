package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Unary to nullary delegate adapter. Adapting is performed by currying the
 * parameter of the adapted delegate.
 *
 * @param <T> the adapted delegate only element type
 * @param <R> the adapted delegate result type
 * @author rferranti
 */
public class Binder<T, R> implements Supplier<R> {

    private final Function<T, R> adapted;
    private final T only;

    public Binder(Function<T, R> adaptee, T only) {
        dbc.precondition(adaptee != null, "cannot bind parameter of a null delegate");
        this.adapted = adaptee;
        this.only = only;
    }

    @Override
    public R get() {
        return adapted.apply(only);
    }
}
