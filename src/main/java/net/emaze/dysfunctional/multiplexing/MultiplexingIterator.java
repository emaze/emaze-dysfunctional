package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import net.emaze.dysfunctional.consumers.Consumers;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;
import net.emaze.dysfunctional.logic.HasNext;
import net.emaze.dysfunctional.iterations.Iterations;

/**
 * shortest 
 * @param <E>
 * @author rferranti
 */
public class MultiplexingIterator<E> implements Iterator<E> {

    private final List<Iterator<E>> iterators = new ArrayList<Iterator<E>>();
    private final Queue<E> prefetched = new LinkedList<E>();

    public <T extends Iterator<E>> MultiplexingIterator(Iterator<T> iterators) {
        dbc.precondition(iterators != null, "trying to create a MultiplexingIterator from a null iterator of iterators");
        this.iterators.addAll(Consumers.all(iterators));
        dbc.precondition(this.iterators.iterator().hasNext(), "trying to create a MultiplexingIterator from an empty iterator");
    }

    @Override
    public boolean hasNext() {
        if (prefetched.isEmpty()) {
            tryPrefetch();
        }
        return !prefetched.isEmpty();
    }

    @Override
    public E next() {
        if (prefetched.isEmpty()) {
            tryPrefetch();
        }
        return prefetched.remove();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Cannot remove from a MultiplexingIterator.");
    }

    private void tryPrefetch() {
        if (!Iterations.every(iterators, new HasNext<Iterator<E>>())) {
            return;
        }
        prefetched.addAll(Consumers.all(Iterations.transform(iterators, new Delegate<E, Iterator<E>>() {

            @Override
            public E perform(Iterator<E> iter) {
                return iter.next();
            }
        })));
    }
}
