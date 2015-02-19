package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Adapts a provider to a delegate. Adapting is performed by ignoring the
 * parameter passed to the adapted provider.
 *
 * @param <T> the adapter parameter type
 * @param <R> the adapter result type
 * @author rferranti
 */
public class IgnoreParameter<T, R> implements Function<T, R> {

    private final Supplier<R> adapted;

    public IgnoreParameter(Supplier<R> adaptee) {
        dbc.precondition(adaptee != null, "cannot ignore parameter of a null provider");
        this.adapted = adaptee;
    }

    @Override
    public R apply(T parameter) {
        return adapted.get();
    }
}
