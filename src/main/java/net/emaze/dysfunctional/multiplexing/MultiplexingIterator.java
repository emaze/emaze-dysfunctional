package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.filtering.Filtering;
import net.emaze.dysfunctional.numbers.CircularCounter;

/**
 * shortest 
 * @param <T> 
 * @author rferranti
 */
public class MultiplexingIterator<T> implements Iterator<T> {

    private final List<Iterator<T>> iterators = new ArrayList<Iterator<T>>();
    private CircularCounter currentIndex;

    public MultiplexingIterator(Iterator<T>... iterators) {
        this.iterators.addAll(Arrays.asList(iterators));
        this.currentIndex = new CircularCounter(iterators.length);
    }

    @Override
    public boolean hasNext() {
        //FIXME: Just wrong
        return iterators.isEmpty() ? false : Filtering.last(1, iterators).hasNext();
    }

    @Override
    public T next() {
        return iterators.get(currentIndex.getAndIncrement()).next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
