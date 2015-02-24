package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Adapts an consumer to a binary consumer. Adapting is performed by ignoring the
 * first parameter passed to the adapted consumer.
 *
 * @param <T1> the adapter first parameter type
 * @param <T2> the adapter second parameter type
 * @author rferranti
 */
public class ConsumerIgnoreFirst<T1, T2> implements BiConsumer<T1, T2> {

    private final Consumer<T2> adapted;

    public ConsumerIgnoreFirst(Consumer<T2> adaptee) {
        dbc.precondition(adaptee != null, "cannot ignore first parameter of a null consumer");
        this.adapted = adaptee;
    }

    @Override
    public void accept(T1 first, T2 second) {
        adapted.accept(second);
    }
}
