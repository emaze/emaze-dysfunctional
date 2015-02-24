package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.BiConsumer;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.TriConsumer;

/**
 * Ternary to binary consumer adapter. Adapting is performed by currying the third
 * parameter of the adapted ternary consumer.
 *
 * @param <T1> the adapted consumer first element Type
 * @param <T2> the adapted consumer second element Type
 * @param <T3> the adapted consumer third element type
 * @author rferranti
 */
public class ConsumerBinderThird<T1, T2, T3> implements BiConsumer<T1, T2> {

    private final TriConsumer<T1, T2, T3> adapted;
    private final T3 third;

    public ConsumerBinderThird(TriConsumer<T1, T2, T3> adaptee, T3 third) {
        dbc.precondition(adaptee != null, "cannot bind third parameter of a null ternary consumer");
        this.adapted = adaptee;
        this.third = third;
    }

    @Override
    public void accept(T1 first, T2 second) {
        adapted.accept(first, second, third);
    }
}
