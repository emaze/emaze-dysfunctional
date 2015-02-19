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
public class RoundrobinShortestIterator<E> extends ReadOnlyIterator<E> {

    private final Iterator<? extends Iterator<E>> iterators;
    private final List<Iterator<E>> memory = new ArrayList<Iterator<E>>();
    private final Queue<E> prefetchedValues = new LinkedList<E>();

    public <T extends Iterator<E>> RoundrobinShortestIterator(Iterator<T> iterators) {
        dbc.precondition(iterators != null, "trying to create a MultiplexingIterator from a null iterator of iterators");
        this.iterators = iterators;
    }

    @Override
    public boolean hasNext() {
        enforceMemoryIsFilled();
        if (prefetchedValues.isEmpty()) {
            prefetchValues();
        }
        return !prefetchedValues.isEmpty();
    }

    @Override
    public E next() {
        enforceMemoryIsFilled();
        if (prefetchedValues.isEmpty()) {
            prefetchValues();
        }
        return prefetchedValues.remove();
    }

    private void enforceMemoryIsFilled() {
        if (!memory.isEmpty()) {
            return;
        }
        while (iterators.hasNext()) {
            memory.add(iterators.next());
        }
    }

    private void prefetchValues() {
        if (!new Every<Iterator<E>>(new HasNext<Iterator<E>>()).test(memory.iterator())) {
            return;
        }
        final Iterator<E> values = new TransformingIterator<>(memory.iterator(), new FirstElement<>());
        while (values.hasNext()) {
            prefetchedValues.add(values.next());
        }
    }
}
