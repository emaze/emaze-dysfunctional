package net.emaze.dysfunctional.tuples;

import java.util.function.Consumer;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.TriConsumer;

/**
 * Adapts a unary consumer handling triples to a ternary consumer.
 *
 * @param <T1> the first type parameter
 * @param <T2> the second type parameter
 * @param <T3> the third type parameter
 * @author rferranti
 */
public class UnaryToTernaryConsumer<T1, T2, T3> implements TriConsumer<T1, T2, T3> {

    private final Consumer< Triple<T1, T2, T3>> consumer;

    public UnaryToTernaryConsumer(Consumer<Triple<T1, T2, T3>> consumer) {
        dbc.precondition(consumer != null, "cannot create a UnaryToTernaryConsumer with a null consumer");
        this.consumer = consumer;
    }

    @Override
    public void accept(T1 first, T2 second, T3 third) {
        consumer.accept(Triple.of(first, second, third));
    }
}
