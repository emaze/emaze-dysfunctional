package net.emaze.dysfunctional.dispatching.composing;

import java.util.function.BiConsumer;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * A composite binary functor with no return value. On call every composed
 * consumer is called.
 *
 * @param <E1> the former type parameter
 * @param <E2> the latter type parameter
 * @author dangelocola
 */
public class PipelinedBinaryConsumer<E1, E2> implements BiConsumer<E1, E2> {

    private final Iterable<BiConsumer<E1, E2>> consumers;

    public PipelinedBinaryConsumer(Iterable<BiConsumer<E1, E2>> consumers) {
        dbc.precondition(consumers != null, "cannot create a pipeline from a null iterable of binary consumers");
        this.consumers = consumers;
    }

    /**
     * Performs every composed consumer.
     *
     * @param former the former value
     * @param latter the latter value
     */
    @Override
    public void accept(E1 former, E2 latter) {
        for (BiConsumer<E1, E2> consumer : consumers) {
            consumer.accept(former, latter);
        }
    }
}
