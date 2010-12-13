package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.HasNext;
import net.emaze.dysfunctional.iterations.Iterations;
import net.emaze.dysfunctional.numbers.CircularCounter;

/**
 * longest multiplexing
 * [1,2] [a,b,c] -> [1,a,2,b,c]
 * @param <T> 
 * @author rferranti
 */
public class RoundRobinIterator<T> implements Iterator<T> {

    private final List<Iterator<T>> iterators = new ArrayList<Iterator<T>>();
    private final CircularCounter currentIndex;

    public RoundRobinIterator(Iterator<T>... iterators) {
        dbc.precondition(iterators != null, "trying to create a ChainIterator from a null array of iterators");
        this.iterators.addAll(Arrays.asList(iterators));
        this.currentIndex = new CircularCounter(this.iterators.size());
    }

    @Override
    public boolean hasNext() {
        return !empty();
    }

    @Override
    public T next() {
        if (empty()) {
            throw new NoSuchElementException("iterator is consumed");
        }
        return firstNonEmpty().next();
    }

    @Override
    public void remove() {
        iterators.get(currentIndex.get()).remove();
    }

    private boolean empty() {
        return !Iterations.any(iterators, new HasNext<Iterator<T>>());
    }

    private Iterator<T> firstNonEmpty() {
        for (;; currentIndex.incrementAndGet()) {
            final Iterator<T> currentIter = iterators.get(currentIndex.get());
            if (currentIter.hasNext()) {
                return currentIter;
            }
        }
    }
}
