package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.consumers.Consumers;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.HasNext;
import net.emaze.dysfunctional.iterations.Iterations;
import net.emaze.dysfunctional.numbers.CircularCounter;

/**
 * longest multiplexing
 * [1,2] [a,b,c] -> [1,a,2,b,c]
 * @param <E>
 * @author rferranti
 */
public class RoundRobinIterator<E> implements Iterator<E> {

    private final List<Iterator<E>> iterators = new ArrayList<Iterator<E>>();
    private final CircularCounter currentIndex;

    public <T extends Iterator<E>> RoundRobinIterator(Iterator<T> iterators) {
        dbc.precondition(iterators != null, "trying to create a RoundRobinIterator from a null iterator of iterators");
        this.iterators.addAll(Consumers.all(iterators));
        this.currentIndex = new CircularCounter(this.iterators.size());
    }

    @Override
    public boolean hasNext() {
        return !empty();
    }

    @Override
    public E next() {
        if (empty()) {
            throw new NoSuchElementException("iterator is consumed");
        }
        return firstNonEmpty().next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("cannot remove from a RoundRobinIterator");
    }

    private boolean empty() {
        return !Iterations.any(iterators, new HasNext<Iterator<E>>());
    }

    private Iterator<E> firstNonEmpty() {
        for (;; currentIndex.incrementAndGet()) {
            final Iterator<E> currentIter = iterators.get(currentIndex.get());
            if (currentIter.hasNext()) {
                return currentIter;
            }
        }
    }
}
