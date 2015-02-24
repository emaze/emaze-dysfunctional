package net.emaze.dysfunctional.tuples;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Adapts a binary consumer to an consumer handling pairs.
 *
 * @param <T1> the former type parameter
 * @param <T2> the latter type parameter
 * @author rferranti
 */
public class BinaryToUnaryConsumer<T1, T2> implements Consumer<Pair<T1, T2>> {

    private final BiConsumer<T1, T2> consumer;

    public BinaryToUnaryConsumer(BiConsumer<T1, T2> consumer) {
        dbc.precondition(consumer != null, "cannot create a BinaryToUnaryConsumer with a null consumer");
        this.consumer = consumer;
    }

    @Override
    public void accept(Pair<T1, T2> pair) {
        consumer.accept(pair.first(), pair.second());
    }
}
