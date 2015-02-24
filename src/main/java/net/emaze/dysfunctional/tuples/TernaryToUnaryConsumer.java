package net.emaze.dysfunctional.tuples;

import java.util.function.Consumer;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.TriConsumer;

/**
 * Adapts a ternary consumer handling triples to an consumer handling triples.
 *
 * @param <T1> the first type parameter
 * @param <T2> the second type parameter
 * @param <T3> the third type parameter
 * @author rferranti
 */
public class TernaryToUnaryConsumer<T1, T2, T3> implements Consumer<Triple<T1, T2, T3>> {

    private final TriConsumer<T1, T2, T3> consumer;

    public TernaryToUnaryConsumer(TriConsumer<T1, T2, T3> consumer) {
        dbc.precondition(consumer != null, "cannot create a TernaryToUnaryConsumer with a null consumer");
        this.consumer = consumer;
    }

    @Override
    public void accept(Triple<T1, T2, T3> triple) {
        consumer.accept(triple.first(), triple.second(), triple.third());
    }
}
