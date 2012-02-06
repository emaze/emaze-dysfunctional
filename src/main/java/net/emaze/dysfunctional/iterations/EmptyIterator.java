package net.emaze.dysfunctional.iterations;

import java.util.NoSuchElementException;

/**
 * An empty iterator.
 *
 * @param <T> the element type
 * @author rferranti
 */
public class EmptyIterator<T> extends ReadOnlyIterator<T> {

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        throw new NoSuchElementException("empty iterator has no elements");
    }
}
