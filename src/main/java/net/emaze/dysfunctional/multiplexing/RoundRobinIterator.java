package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
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
        this.iterators.addAll(Arrays.asList(iterators));
        this.currentIndex = new CircularCounter(this.iterators.size());
    }

    @Override
    public boolean hasNext() {
        return Iterations.any(iterators, new HasNext<Iterator<T>>());
    }

    @Override
    public T next() {
        return iterators.get(currentIndex.getAndIncrement()).next();
    }

    @Override
    public void remove() {
        iterators.get(currentIndex.get()).remove();
    }

}
