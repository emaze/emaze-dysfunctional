package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Adapts a delegate to an action. Adapting is performed by ignoring result of
 * the adapted delegate.
 *
 * @param <T> the adapted delegate parameter type
 * @param <R> the adapted delegate result type
 * @author rferranti
 */
public class DelegateToAction<T, R> implements Consumer<T> {

    private final Function<T, R> adapted;

    public DelegateToAction(Function<T, R> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null delegate to action");
        this.adapted = adaptee;
    }

    @Override
    public void accept(T value) {
        adapted.apply(value);
    }
}
