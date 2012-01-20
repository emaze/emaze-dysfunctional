package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.output.OutputIterator;
import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * A consumer pushing elements consumed into an OutputIterator.
 * @param <E> the iterator type parameter
 * @author rferranti
 */
public class ConsumeIntoOutputIterator<E> implements Delegate<OutputIterator<E>, Iterator<E>> {

    private final OutputIterator<E> output;

    public ConsumeIntoOutputIterator(OutputIterator<E> output) {
        dbc.precondition(output != null, "OutputIterator cannot be null");
        this.output = output;
    }

    @Override
    public OutputIterator<E> perform(Iterator<E> consumable) {
        dbc.precondition(consumable != null, "consuming a null iterator");
        while(consumable.hasNext()){
            dbc.stateprecondition(output.hasNext(), "outputIterator is smaller than the inputIterator ");
            output.next(consumable.next());
        }
        return output;
    }
}
