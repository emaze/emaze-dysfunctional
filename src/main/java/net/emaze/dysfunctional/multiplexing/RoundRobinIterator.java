package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.HasNext;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;
import net.emaze.dysfunctional.order.NextIntegerSequencingPolicy;
import net.emaze.dysfunctional.order.PeriodicIterator;
import net.emaze.dysfunctional.order.PeriodicSequencingPolicy;
import net.emaze.dysfunctional.reductions.Any;

/**
 * longest multiplexing
 * <code>
 * [1,2] [a,b,c] -> [1,a,2,b,c]
 * </code>
 *
 * @param <E> the iterator element type
 * @author rferranti
 */
public class RoundRobinIterator<E> extends ReadOnlyIterator<E> {

    private final List<Iterator<E>> iterators = new ArrayList<Iterator<E>>();
    private final PeriodicIterator<Integer> indexSelector;

    public <T extends Iterator<E>> RoundRobinIterator(Iterator<T> iterators) {
        dbc.precondition(iterators != null, "trying to create a RoundRobinIterator from a null iterator of iterators");
        while (iterators.hasNext()) {
            this.iterators.add(iterators.next());
        }
        final PeriodicSequencingPolicy<Integer> period = new PeriodicSequencingPolicy<Integer>(new NextIntegerSequencingPolicy(), 0, this.iterators.size() - 1);
        this.indexSelector = new PeriodicIterator<Integer>(period, 0);
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

    private boolean empty() {
        return !new Any<Iterator<E>>(new HasNext<Iterator<E>>()).accept(iterators.iterator());
    }

    private Iterator<E> firstNonEmpty() {
        while (true) {
            final Iterator<E> currentIter = iterators.get(indexSelector.next());
            if (currentIter.hasNext()) {
                return currentIter;
            }
        }
    }
}
