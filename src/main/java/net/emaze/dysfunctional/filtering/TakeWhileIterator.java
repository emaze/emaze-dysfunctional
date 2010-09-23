package net.emaze.dysfunctional.filtering;

import java.util.Iterator;
import net.emaze.dysfunctional.delegates.Predicate;

/**
 *
 * @author rferranti
 */
public class TakeWhileIterator<T> implements Iterator<T> {

    private final Iterator<T> iterator;

    public TakeWhileIterator(Iterator<T> iterator, final Predicate<T> takeWhile) {
        this.iterator = new FilteringIterator<T>(iterator, new TakeWhileFilter(takeWhile));
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public T next() {
        return iterator.next();
    }

    @Override
    public void remove() {
        iterator.remove();
    }
}
