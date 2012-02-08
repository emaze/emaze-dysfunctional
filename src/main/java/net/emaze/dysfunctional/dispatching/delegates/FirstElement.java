package net.emaze.dysfunctional.dispatching.delegates;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * A unary delegate consuming the first element from an iterator.
 *
 * @param <E> the iterator element type
 * @author rferranti
 */
public class FirstElement<E> implements Delegate<E, Iterator<E>> {

    /**
     * Consumes the first element from the passed iterator.
     *
     * @param consumable the iterator to be consumed
     * @throws IllegalArgumentException if the passed iterator is empty
     * @return the consumed value
     */
    @Override
    public E perform(Iterator<E> consumable) {
        dbc.precondition(consumable != null, "consuming a null iterator");
        dbc.precondition(consumable.hasNext(), "no element to consume");
        return consumable.next();
    }
}
