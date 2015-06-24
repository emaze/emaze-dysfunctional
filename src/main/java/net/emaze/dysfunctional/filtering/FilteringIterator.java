package net.emaze.dysfunctional.filtering;

import java.util.Iterator;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Predicate;


/**
 * Iterates on the iterator elements which the predicate matches
 * @param <E> 
 * @author rferranti
 */
public class FilteringIterator<E> implements Iterator<E> {

    private final Predicate<E> filter;
    private final Iterator<E> iterator;
    private boolean currentHasValue;
    private E current;

    public FilteringIterator(Iterator<E> iterator, Predicate<E> filter) {
        dbc.precondition(iterator != null, "trying to create a FilteringIterator from a null iterator");
        dbc.precondition(filter != null, "trying to create a FilteringIterator from a null filter");
        this.iterator = iterator;
        this.filter = filter;
    }

    @Override
    public boolean hasNext() {
        while (!currentHasValue && iterator.hasNext()) {
            final E val = iterator.next();
            currentHasValue = filter.test(val);
            current = val;
        }
        return currentHasValue;
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        try {
            return current;
        } finally {
            currentHasValue = false;
        }
    }

    @Override
    public void remove() {
        iterator.remove();
    }

}
