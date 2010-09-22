package net.emaze.dysfunctional.iterations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.numbers.OverflowingInteger;

/**
 * longest multiplexing
 * [1,2] [a,b,c] -> [1,a,2,b,c]
 * @author rferranti
 */
public class RoundRobinIterator<T> implements Iterator<T> {

    private final List<Iterator<T>> iterators = new ArrayList<Iterator<T>>();
    private final OverflowingInteger currentIndex;
    
    public RoundRobinIterator(Iterator<T>... iterators) {
        this.iterators.addAll(Arrays.asList(iterators));
        this.currentIndex = new OverflowingInteger(this.iterators.size());
    }

    @Override
    public boolean hasNext() {
        return Iterations.any(iterators, new HasNext());
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
