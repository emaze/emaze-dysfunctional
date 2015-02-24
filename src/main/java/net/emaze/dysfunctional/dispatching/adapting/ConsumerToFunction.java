package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Adapts an consumer to a function. Adapter result type is Void and always yields
 * null.
 *
 * @param <T> the adapted consumer parameter type
 * @author rferranti
 */
public class ConsumerToFunction<T> implements Function<T, Void> {

    private final Consumer<T> adapted;

    public ConsumerToFunction(Consumer<T> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null consumer to a function");
        this.adapted = adaptee;
    }

    @Override
    public Void apply(T value) {
        adapted.accept(value);
        return null;
    }
}
