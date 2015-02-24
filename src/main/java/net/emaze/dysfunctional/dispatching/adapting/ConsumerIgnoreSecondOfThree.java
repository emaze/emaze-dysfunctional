package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.BiConsumer;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.TriConsumer;

/**
 * Adapts a binary consumer to a ternary consumer. Adapting is performed by ignoring
 * the second parameter passed to the adapted consumer.
 *
 * @param <T1> the adapter first parameter type
 * @param <T2> the adapter second parameter type
 * @param <T3> the adapter third parameter type
 * @author rferranti
 */
public class ConsumerIgnoreSecondOfThree<T1, T2, T3> implements TriConsumer<T1, T2, T3> {

    private final BiConsumer<T1, T3> adapted;

    public ConsumerIgnoreSecondOfThree(BiConsumer<T1, T3> adaptee) {
        dbc.precondition(adaptee != null, "cannot ignore second parameter of a null consumer");
        this.adapted = adaptee;
    }

    @Override
    public void accept(T1 first, T2 second, T3 third) {
        adapted.accept(first, third);
    }
}
