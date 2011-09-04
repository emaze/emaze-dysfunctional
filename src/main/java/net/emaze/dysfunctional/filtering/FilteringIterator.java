package net.emaze.dysfunctional.filtering;

import net.emaze.dysfunctional.dispatching.logic.Predicate;
import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Iterates on the iterator elements which the predicate matches
 * @param <E> 
 * @author rferranti
 */
public class FilteringIterator<E> implements Iterator<E> {

    private final Predicate<E> filter;
    private final Iterator<E> iterator;
    private E prefetched;
    private boolean hasPrefetched;

    public FilteringIterator(Iterator<E> iterator, Predicate<E> filter) {
        dbc.precondition(iterator != null, "trying to create a FilteringIterator from a null iterator");
        dbc.precondition(filter != null, "trying to create a FilteringIterator from a null filter");
        this.iterator = iterator;
        this.filter = filter;
    }

    @Override
    public boolean hasNext() {
        if (hasPrefetched) {
            return true;
        }
        while (true) {
            if (!iterator.hasNext()) {
                return false;
            }
            final E element = iterator.next();
            if (filter.accept(element)) {
                prefetched = element;
                hasPrefetched = true;
                return true;
            }
        }
    }

    @Override
    public E next() {
        if (hasPrefetched) {
            hasPrefetched = false;
            return prefetched;
        }
        while (true) {
            final E element = iterator.next();
            if (filter.accept(element)) {
                return element;
            }
        }
    }

    @Override
    public void remove() {
        iterator.remove();
    }

}
