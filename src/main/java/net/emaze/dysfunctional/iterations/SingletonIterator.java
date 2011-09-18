package net.emaze.dysfunctional.iterations;

import java.util.NoSuchElementException;

/**
 *
 * @author rferranti
 */
public class SingletonIterator<T> extends ReadOnlyIterator<T> {

    private final T element;
    private boolean isConsumed;

    public SingletonIterator(T element) {
        this.element = element;
    }

    @Override
    public boolean hasNext() {
        return !isConsumed;
    }

    @Override
    public T next() {
        if (isConsumed) {
            throw new NoSuchElementException("singleton iterator is already consumed");
        }
        isConsumed = true;
        return element;
    }
}
