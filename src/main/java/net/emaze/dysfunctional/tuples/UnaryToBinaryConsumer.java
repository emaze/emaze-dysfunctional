package net.emaze.dysfunctional.tuples;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Adapts a unary consumer handling pairs to a binary consumer.
 *
 * @param <T1> the former type parameter
 * @param <T2> the latter type parameter
 * @author rferranti
 */
public class UnaryToBinaryConsumer<T1, T2> implements BiConsumer<T1, T2> {

    private final Consumer<Pair<T1, T2>> consumer;

    public UnaryToBinaryConsumer(Consumer<Pair<T1, T2>> consumer) {
        dbc.precondition(consumer != null, "cannot create a UnaryToBinaryConsumer with a null consumer");
        this.consumer = consumer;
    }

    @Override
    public void accept(T1 first, T2 second) {
        consumer.accept(Pair.of(first, second));
    }
}
