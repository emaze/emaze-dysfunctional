package net.emaze.dysfunctional.iterations;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Adapts an iterator to the iterable interface enabling the iterator to be
 * consumed where an iterable is expected. A OneTimeIterable can be consumed
 * only once.
 *
 * @param <T> the element type
 * @author rferranti
 */
public class OneTimeIterable<T> implements Iterable<T> {

    private Iterator<T> iterator;
    private boolean consumed = false;

    public OneTimeIterable(Iterator<T> iterator) {
        dbc.precondition(iterator != null, "cannot create a OneTimeIterable with a null Iterator");
        this.iterator = iterator;
    }

    @Override
    public Iterator<T> iterator() {
        dbc.state(!consumed, "consuming an iterator two times");
        consumed = true;
        return iterator;
    }
}
