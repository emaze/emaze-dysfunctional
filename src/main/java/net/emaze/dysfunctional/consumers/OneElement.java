package net.emaze.dysfunctional.consumers;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 * A unary delegate consuming an iterator yielding the only element contained in
 * it.
 *
 * @param <E> the iterator element type
 * @author rferranti
 */
public class OneElement<E> implements Function<Iterator<E>, E> {

    /**
     * Consumes the iterator and yields the only element contained in it.
     *
     * @param consumable the iterator to be consumed
     * @throws IllegalArgumentException if the source iterator is empty
     * @throws IllegalStateException if the source iterator contains more than
     * one element
     * @return the only element contained in the iterator
     */
    @Override
    public E apply(Iterator<E> consumable) {
        dbc.precondition(consumable != null, "consuming a null iterator");
        dbc.precondition(consumable.hasNext(), "no element to consume");
        final E found = consumable.next();
        dbc.state(!consumable.hasNext(), "found more than one element consuming the iterator");
        return found;
    }
}
