package net.emaze.dysfunctional.dispatching.composing;

import java.util.function.Consumer;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * A composite unary functor with no return value. On call every composed
 * consumer is called.
 *
 * @param <E> the type parameter
 * @author rferranti
 */
public class PipelinedConsumer<E> implements Consumer<E> {

    private final Iterable<Consumer<E>> consumers;

    public PipelinedConsumer(Iterable<Consumer<E>> consumers) {
        dbc.precondition(consumers != null, "cannot create a pipeline from a null iterable of consumers");
        this.consumers = consumers;
    }

    /**
     * performs every composed consumer
     *
     * @param value
     */
    @Override
    public void accept(E value) {
        for (Consumer<E> consumer : consumers) {
            consumer.accept(value);
        }
    }
}
