package net.emaze.dysfunctional.iterations;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author rferranti
 */
public class EmptyIterator<T> implements Iterator<T> {

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        throw new NoSuchElementException("empty iterator has no elements");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
