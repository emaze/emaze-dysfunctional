package net.emaze.dysfunctional.adapting;

import java.util.Iterator;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.contracts.dbc;

/**
 *
 * @param <T>
 * @author rferranti
 */
public class ArrayIterator<T> implements Iterator<T> {
    
    private final T[] values;
    private int currentIndex = 0;
    
    public ArrayIterator(T[] values) {
        dbc.precondition(values != null, "trying to create an ArrayIterator<T> from a null array");
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return !isOutOfBounds();
    }

    private boolean isOutOfBounds(){
        return currentIndex == values.length;
    }

    @Override
    public T next() {
        if(isOutOfBounds()){
            throw new NoSuchElementException();
        }
        final T element = values[currentIndex];
        ++currentIndex;
        return element;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Cannot remove from an ArrayIterator");
    }

}
