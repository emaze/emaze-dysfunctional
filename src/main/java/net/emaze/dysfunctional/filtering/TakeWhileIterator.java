package net.emaze.dysfunctional.filtering;

import java.util.Iterator;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Predicate;
import net.emaze.dysfunctional.options.Maybe;

/**
 *
 * @param <E> the iterator element Type
 * @author rferranti
 */
public class TakeWhileIterator<E> implements Iterator<E> {

    private final Predicate<E> filter;
    private final Iterator<E> iterator;
    private Maybe<E> prefetched = Maybe.nothing();

    public TakeWhileIterator(Iterator<E> iterator, Predicate<E> filter) {
        dbc.precondition(iterator != null, "trying to create a TakeWhileIterator from a null iterator");
        dbc.precondition(filter != null, "trying to create a TakeWhileIterator from a null filter");
        this.iterator = iterator;
        this.filter = filter;
    }

    @Override
    public boolean hasNext() {
        if (prefetched.hasValue()) {
            return true;
        }
        if (!iterator.hasNext()) {
            return false;
        }
        final E element = iterator.next();
        if (filter.test(element)) {
            prefetched = Maybe.just(element);
            return true;
        }
        return false;
    }

    @Override
    public E next() {
        if (prefetched.hasValue()) {
            final E element = prefetched.value();
            prefetched = Maybe.nothing();
            return element;
        }
        final E element = iterator.next();
        if (filter.test(element)) {
            return element;
        }
        throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
