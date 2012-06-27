package net.emaze.dysfunctional.filtering;

import java.util.Iterator;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.options.Maybe;


/**
 * Iterates on the iterator elements which the predicate matches
 * @param <E> 
 * @author rferranti
 */
public class FilteringIterator<E> implements Iterator<E> {

    private final Predicate<E> filter;
    private final Iterator<E> iterator;
    private Maybe<E> current = Maybe.nothing();

    public FilteringIterator(Iterator<E> iterator, Predicate<E> filter) {
        dbc.precondition(iterator != null, "trying to create a FilteringIterator from a null iterator");
        dbc.precondition(filter != null, "trying to create a FilteringIterator from a null filter");
        this.iterator = iterator;
        this.filter = filter;
    }

    @Override
    public boolean hasNext() {
        while (!current.hasValue() && iterator.hasNext()) {
            E val = iterator.next();
            current = filter.accept(val) ? Maybe.just(val) : Maybe.<E>nothing();
        }
        return current.hasValue();
    }

    @Override
    public E next() {
        // TODO: test for NoSuchElementException
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        try {
            return current.value();
        } finally {
            current = Maybe.nothing();
        }
    }

    @Override
    public void remove() {
        iterator.remove();
    }

}
