package net.emaze.dysfunctional.consumers;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * A unary delegate consuming an iterator and yielding last element contained in
 * it.
 *
 * @param <E> the iterator element type
 * @author rferranti
 */
public class LastElement<E> implements Delegate<E, Iterator<E>> {

    /**
     * Consumes the iterator and yields the last element contained in it.
     *
     * @param consumable the iterator to be consumed
     * @throws IllegalArgumentException if the source iterator is empty
     * @return the last element
     */
    @Override
    public E perform(Iterator<E> consumable) {
        dbc.precondition(consumable != null, "consuming a null iterator");
        dbc.precondition(consumable.hasNext(), "no element to consume");
        E value = consumable.next();
        while (consumable.hasNext()) {
            value = consumable.next();
        }
        return value;
    }
}
