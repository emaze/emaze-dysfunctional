package net.emaze.dysfunctional.filtering;

import java.util.Iterator;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.iterations.Destructive;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;

/**
 *
 * @param <E> the iterator element Type
 * @author rferranti
 */
@Destructive
public class TakeWhileIterator<E> extends ReadOnlyIterator<E> {

    private final Predicate<E> filter;
    private final Iterator<E> iterator;
    private E prefetched;
    private boolean hasPrefetched;

    public TakeWhileIterator(Iterator<E> iterator, Predicate<E> filter) {
        dbc.precondition(iterator != null, "trying to create a TakeWhileIterator from a null iterator");
        dbc.precondition(filter != null, "trying to create a TakeWhileIterator from a null filter");
        this.iterator = iterator;
        this.filter = filter;
    }

    @Override
    public boolean hasNext() {
        if (hasPrefetched) {
            return true;
        }
        if (!iterator.hasNext()) {
            return false;
        }
        final E element = iterator.next();
        if (filter.accept(element)) {
            prefetched = element;
            hasPrefetched = true;
            return true;
        }
        return false;
    }

    @Override
    public E next() {
        if (hasPrefetched) {
            hasPrefetched = false;
            return prefetched;
        }
        final E element = iterator.next();
        if (filter.accept(element)) {
            return element;
        }
        throw new NoSuchElementException();
    }
}
