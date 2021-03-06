package net.emaze.dysfunctional.multiplexing;

import java.util.Iterator;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;

/**
 * A composite iterator. Composed iterators are consumed in order.
 *
 * @param <E> the element type
 * @author rferranti
 */
public class ChainIterator<E> extends ReadOnlyIterator<E> {

    private final Iterator<? extends Iterator<E>> iterators;
    private Iterator<E> current;

    public <T extends Iterator<E>> ChainIterator(Iterator<T> iterators) {
        dbc.precondition(iterators != null, "trying to create a ChainIterator from a null iterator of iterators");
        this.iterators = iterators;
    }

    @Override
    public boolean hasNext() {
        while ((current == null || !current.hasNext()) && iterators.hasNext()) {
            current = iterators.next();
        }
        return current != null && current.hasNext();
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return current.next();
    }
}
