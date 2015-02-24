package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Unary to nullary function adapter. Adapting is performed by currying the
 * parameter of the adapted function.
 *
 * @param <T> the adapted function only element type
 * @param <R> the adapted function result type
 * @author rferranti
 */
public class Binder<T, R> implements Supplier<R> {

    private final Function<T, R> adapted;
    private final T only;

    public Binder(Function<T, R> adaptee, T only) {
        dbc.precondition(adaptee != null, "cannot bind parameter of a null function");
        this.adapted = adaptee;
        this.only = only;
    }

    @Override
    public R get() {
        return adapted.apply(only);
    }
}
