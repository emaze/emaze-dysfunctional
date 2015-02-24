package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Adapts a binary consumer to a binary function. Adapter result type is Void and
 * always yields null.
 *
 * @param <T1> the adapted consumer first parameter type
 * @param <T2> the adapted consumer second parameter type
 * @author rferranti
 */
public class BinaryConsumerToFunction<T1, T2> implements BiFunction<T1, T2, Void> {

    private final BiConsumer<T1, T2> adapted;

    public BinaryConsumerToFunction(BiConsumer<T1, T2> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null binary consumer to a binary function");
        this.adapted = adaptee;
    }

    @Override
    public Void apply(T1 first, T2 second) {
        adapted.accept(first, second);
        return null;
    }
}
