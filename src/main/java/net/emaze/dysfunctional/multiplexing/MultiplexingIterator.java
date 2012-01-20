package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import net.emaze.dysfunctional.Consumers;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.Transforming;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.logic.HasNext;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;
import net.emaze.dysfunctional.Reductions;

/**
 * shortest 
 * @param <E>
 * @author rferranti
 */
public class MultiplexingIterator<E> extends ReadOnlyIterator<E> {

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

    private void tryPrefetch() {
        if (!Reductions.every(iterators, new HasNext<Iterator<E>>())) {
            return;
        }
        prefetched.addAll(Consumers.all(Transforming.transform(iterators, new Delegate<E, Iterator<E>>() {

            @Override
            public E perform(Iterator<E> iter) {
                return iter.next();
            }
        })));
    }
}
