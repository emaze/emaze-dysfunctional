package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Binary to unary consumer adapter. Adapting is performed by currying the
 * parameter passed in construction to the adapted consumer.
 *
 * @param <T1> the adapted consumer former element type
 * @param <T2> the adapted consumer latter element type
 * @author rferranti
 */
public class ConsumerBinderSecond<T1, T2> implements Consumer<T1> {

    private final BiConsumer<T1, T2> adapted;
    private final T2 second;

    public ConsumerBinderSecond(BiConsumer<T1, T2> adaptee, T2 second) {
        dbc.precondition(adaptee != null, "cannot bind second parameter of a null binary consumer");
        this.adapted = adaptee;
        this.second = second;
    }

    @Override
    public void accept(T1 first) {
        adapted.accept(first, second);
    }
}
