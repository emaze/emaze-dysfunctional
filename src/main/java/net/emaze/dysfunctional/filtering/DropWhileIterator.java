package net.emaze.dysfunctional.filtering;

import java.util.Iterator;
import net.emaze.dysfunctional.delegates.Predicate;

/**
 *
 * @param <T> 
 * @author rferranti
 */
public class DropWhileIterator<T> implements Iterator<T> {

    private final Iterator<T> iterator;

    public DropWhileIterator(Iterator<T> iterator, final Predicate<T> dropWhile) {
        this.iterator = new FilteringIterator<T>(iterator, new DropWhile<T>(dropWhile));
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
