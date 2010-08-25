package net.emaze.disfunctional;

import java.util.Iterator;

/**
 *
 * @author rferranti
 */
public class FilteringIterator<E> implements Iterator<E> {

    private final Predicate<E> filter;
    private final Iterator<E> iterator;
    private Maybe<E> prefetched = Maybe.nothing();

    public FilteringIterator(Iterator<E> iterator, Predicate<E> filter) {
        this.iterator = iterator;
        this.filter = filter;
    }

    public boolean hasNext() {
        if (prefetched.hasValue()) {
            return true;
        }
        while (true) {
            if (!iterator.hasNext()) {
                return false;
            }
            final E element = iterator.next();
            if (!filter.match(element)) {
                prefetched = Maybe.just(element);
                return true;
            }
        }
    }

    public E next() {
        if (prefetched.hasValue()) {
            final E element = prefetched.value();
            prefetched = Maybe.nothing();
            return element;
        }
        while (true) {
            final E element = iterator.next();
            if (!filter.match(element)) {
                return element;
            }
        }
    }

    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
