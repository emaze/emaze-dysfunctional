package net.emaze.dysfunctional.iterations;

import java.util.Iterator;

/**
 * An infinite Iterator always yields the same value
 * @param <T> 
 * @author rferranti
 */
public class ConstantIterator<T> extends ReadOnlyIterator<T> {

    private final T value;

    public ConstantIterator(T value) {
        this.value = value;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public T next() {
        return value;
    }
}
