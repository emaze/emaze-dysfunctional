package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Adapts a binary function to a binary consumer. Adapting is performed by
 * ignoring result of the adapted function.
 *
 * @author rferranti
 * @param <T1> the adapted function first parameter type
 * @param <T2> the adapted function second parameter type
 * @param <R> the adapted function result type
 */
public class BinaryFunctionToConsumer<T1, T2, R> implements BiConsumer<T1, T2> {

    private final BiFunction<T1, T2, R> adapted;

    public BinaryFunctionToConsumer(BiFunction<T1, T2, R> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null binary function to binary consumer");
        this.adapted = adaptee;
    }

    @Override
    public void accept(T1 first, T2 second) {
        adapted.apply(first, second);
    }
}
