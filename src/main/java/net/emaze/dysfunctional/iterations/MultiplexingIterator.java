package net.emaze.dysfunctional.iterations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * shortest 
 * @author rferranti
 */
public class MultiplexingIterator<T> implements Iterator<T> {

    private final List<Iterator<T>> iterators = new ArrayList<Iterator<T>>();
    private int currentIndex = 0;

    public MultiplexingIterator(Iterator<T>... iterators) {
        this.iterators.addAll(Arrays.asList(iterators));
    }

    @Override
    public boolean hasNext() {
        //FIXME: Just wrong
        return iterators.isEmpty() ? false : Iterations.last(iterators).hasNext();
    }

    @Override
    public T next() {
        final T value = iterators.get(currentIndex).next();
        currentIndex = (currentIndex + 1) % iterators.size() ;
        return value;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
