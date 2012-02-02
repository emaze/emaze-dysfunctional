package net.emaze.dysfunctional.dispatching.delegates;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Searches for one element in the iterator, throws is zero or more than one element is found.
 * @param <E> the iterator element type
 * @author rferranti
 */
public class OneElement<E> implements Delegate<E, Iterator<E>> {

    @Override
    public E perform(Iterator<E> consumable) {
        dbc.precondition(consumable != null, "consuming a null iterator");
        dbc.precondition(consumable.hasNext(), "no element to consume");
        final E found = consumable.next();
        dbc.state(!consumable.hasNext(), "found more than one element consuming the iterator");
        return found;
    }
}
