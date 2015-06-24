package net.emaze.dysfunctional.dispatching.composing;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.TriConsumer;

/**
 * A composite ternary functor with no return value. On call every composed
 * consumer is called.
 *
 * @param <E1> the first type parameter
 * @param <E2> the second type parameter
 * @param <E3> the third type parameter
 * @author rferranti
 */
public class PipelinedTernaryConsumer<E1, E2, E3> implements TriConsumer<E1, E2, E3> {

    private final Iterable<TriConsumer<E1, E2, E3>> consumers;

    public PipelinedTernaryConsumer(Iterable<TriConsumer<E1, E2, E3>> consumers) {
        dbc.precondition(consumers != null, "cannot create a pipeline from a null iterable of consumers");
        this.consumers = consumers;
    }

    /**
     * Performs every composed consumer.
     *
     * @param first the first element
     * @param second the second element
     * @param third the third element
     */
    @Override
    public void accept(E1 first, E2 second, E3 third) {
        for (TriConsumer<E1, E2, E3> consumer : consumers) {
            consumer.accept(first, second, third);
        }
    }
}
