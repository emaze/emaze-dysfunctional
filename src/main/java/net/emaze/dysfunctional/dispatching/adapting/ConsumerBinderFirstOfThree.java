package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.BiConsumer;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.TriConsumer;

/**
 * Ternary to binary consumer adapter. Adapting is performed by currying the first
 * parameter of the adapted ternary consumer.
 *
 * @param <T1> the adapted consumer first element Type
 * @param <T2> the adapted consumer second element Type
 * @param <T3> the adapted consumer third element type
 * @author rferranti
 */
public class ConsumerBinderFirstOfThree<T1, T2, T3> implements BiConsumer<T2, T3> {

    private final TriConsumer<T1, T2, T3> adapted;
    private final T1 first;

    public ConsumerBinderFirstOfThree(TriConsumer<T1, T2, T3> adaptee, T1 first) {
        dbc.precondition(adaptee != null, "cannot bind first parameter of a null ternary consumer");
        this.adapted = adaptee;
        this.first = first;
    }

    @Override
    public void accept(T2 second, T3 third) {
        adapted.accept(first, second, third);
    }
}
