package net.emaze.dysfunctional.consumers;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 *
 * @param <E>
 * @author rferranti
 */
public class PipingConsumer<E> implements Consumer<OutputIterator<E>, Iterator<E>> {

    private final OutputIterator<E> output;

    public PipingConsumer(OutputIterator<E> output) {
        dbc.precondition(output != null, "OutputIterator cannot be null");
        this.output = output;
    }

    @Override
    public OutputIterator<E> consume(Iterator<E> consumable) {
        dbc.precondition(consumable != null, "consuming a null iterator");
        while(consumable.hasNext()){
            dbc.stateprecondition(output.hasNext(), "outputIterator is smaller than the inputIterator ");
            output.next(consumable.next());
        }
        return output;
    }
}
