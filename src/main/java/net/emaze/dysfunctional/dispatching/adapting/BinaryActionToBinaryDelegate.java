package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * Adapts a binary action to a binary delegate. Adapter result type is Void and
 * always yields null.
 *
 * @param <T1> the adapted action first parameter type
 * @param <T2> the adapted action second parameter type
 * @author rferranti
 */
public class BinaryActionToBinaryDelegate<T1, T2> implements BiFunction<T1, T2, Void> {

    private final BiConsumer<T1, T2> adapted;

    public BinaryActionToBinaryDelegate(BiConsumer<T1, T2> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null binary action to a binary delegate");
        this.adapted = adaptee;
    }

    @Override
    public Void apply(T1 first, T2 second) {
        adapted.accept(first, second);
        return null;
    }
}
