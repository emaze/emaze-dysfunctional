package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Consumer;
import java.util.function.BiConsumer;

/**
 * Binary to unary consumer adapter. Adapting is performed by currying the first
 * parameter of the adapted binary consumer.
 *
 * @param <T1> the adapted consumer former element Type
 * @param <T2> the adapted consumer latter element Type
 * @author rferranti
 */
public class ConsumerBinderFirst<T1, T2> implements Consumer<T2> {

    private final BiConsumer<T1, T2> adapted;
    private final T1 first;

    public ConsumerBinderFirst(BiConsumer<T1, T2> adaptee, T1 first) {
        dbc.precondition(adaptee != null, "cannot bind the first parameter of a null binary consumer");
        this.adapted = adaptee;
        this.first = first;
    }

    @Override
    public void accept(T2 second) {
        adapted.accept(first, second);
    }
}
