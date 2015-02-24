package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.Consumer;
import java.util.function.Function;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Adapts a function to an consumer. Adapting is performed by ignoring result of
 * the adapted function.
 *
 * @param <T> the adapted function parameter type
 * @param <R> the adapted function result type
 * @author rferranti
 */
public class FunctionToConsumer<T, R> implements Consumer<T> {

    private final Function<T, R> adapted;

    public FunctionToConsumer(Function<T, R> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null function to consumer");
        this.adapted = adaptee;
    }

    @Override
    public void accept(T value) {
        adapted.apply(value);
    }
}
