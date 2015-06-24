package net.emaze.dysfunctional.consumers;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import net.emaze.dysfunctional.output.OutputIterator;

/**
 * A consumer pushing elements consumed into an OutputIterator.
 *
 * @param <E> the iterator type parameter
 * @author rferranti
 */
public class ConsumeIntoOutputIterator<E> implements Function<Iterator<E>, OutputIterator<E>> {

    private final OutputIterator<E> output;

    public ConsumeIntoOutputIterator(OutputIterator<E> output) {
        dbc.precondition(output != null, "OutputIterator cannot be null");
        this.output = output;
    }

    @Override
    public OutputIterator<E> apply(Iterator<E> consumable) {
        dbc.precondition(consumable != null, "consuming a null iterator");
        while (consumable.hasNext()) {
            dbc.state(output.hasNext(), "outputIterator is smaller than the inputIterator ");
            output.next(consumable.next());
        }
        return output;
    }
}
