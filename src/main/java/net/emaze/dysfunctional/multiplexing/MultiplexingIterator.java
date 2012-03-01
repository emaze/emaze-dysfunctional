package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import net.emaze.dysfunctional.consumers.FirstElement;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.HasNext;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;
import net.emaze.dysfunctional.iterations.TransformingIterator;
import net.emaze.dysfunctional.reductions.Every;

/**
 * shortest
 *
 * @param <E>
 * @author rferranti
 */
public class MultiplexingIterator<E> extends ReadOnlyIterator<E> {

    private final List<Iterator<E>> iterators = new ArrayList<Iterator<E>>();
    private final Queue<E> prefetched = new LinkedList<E>();

    public <T extends Iterator<E>> MultiplexingIterator(Iterator<T> iterators) {
        dbc.precondition(iterators != null, "trying to create a MultiplexingIterator from a null iterator of iterators");
        while(iterators.hasNext()){
            this.iterators.add(iterators.next());
        }
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
        if (!new Every<Iterator<E>>(new HasNext<Iterator<E>>()).accept(iterators.iterator())) {
            return;
        }
        final Iterator<E> values = new TransformingIterator<E, Iterator<E>>(iterators.iterator(), new FirstElement<E>());
        while(values.hasNext()){
            prefetched.add(values.next());
        }
    }
}
