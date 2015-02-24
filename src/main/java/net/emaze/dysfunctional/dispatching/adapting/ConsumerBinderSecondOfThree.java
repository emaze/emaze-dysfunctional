package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiConsumer;
import net.emaze.dysfunctional.dispatching.actions.TriConsumer;

/**
 * Ternary to binary consumer adapter. Adapting is performed by currying the
 * second parameter of the adapted ternary consumer.
 *
 * @param <T1> the adapted consumer first element Type
 * @param <T2> the adapted consumer second element Type
 * @param <T3> the adapted consumer third element type
 * @author rferranti
 */
public class ConsumerBinderSecondOfThree<T1, T2, T3> implements BiConsumer<T1, T3> {

    private final TriConsumer<T1, T2, T3> adapted;
    private final T2 second;

    public ConsumerBinderSecondOfThree(TriConsumer<T1, T2, T3> adaptee, T2 second) {
        dbc.precondition(adaptee != null, "cannot bind second parameter of a null ternary consumer");
        this.adapted = adaptee;
        this.second = second;
    }

    @Override
    public void accept(T1 first, T3 third) {
        adapted.accept(first, second, third);
    }
}
