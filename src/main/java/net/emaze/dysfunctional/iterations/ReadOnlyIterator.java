package net.emaze.dysfunctional.iterations;

import java.util.Iterator;

/**
 *
 * @author rferranti
 */
public abstract class ReadOnlyIterator<T> implements Iterator<T> {

    @Override
    public void remove() {
        throw new UnsupportedOperationException("cannot remove from a read-only iterator");
    }
}
