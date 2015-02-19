package net.emaze.dysfunctional.multiplexing;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;
import net.emaze.dysfunctional.options.Box;

/**
 * longest multiplexing
 * <code>
 * [1,2] [a,b,c] -> [1,a,2,b,c]
 * </code>
 *
 * @param <E> the iterator element type
 * @author rferranti
 */
public class RoundrobinIterator<E> extends ReadOnlyIterator<E> {

    private final Iterator<? extends Iterator<E>> iterators;
    private final Deque<Iterator<E>> memory = new LinkedList<Iterator<E>>();
    private final Box<Iterator<E>> prefetched = Box.empty();

    public <T extends Iterator<E>> RoundrobinIterator(Iterator<T> iterators) {
        dbc.precondition(iterators != null, "trying to create a RoundRobinIterator from a null iterator of iterators");
        this.iterators = iterators;
    }

    @Override
    public boolean hasNext() {
        prefetchedAndMemorizeNonEmpty();
        return prefetched.isPresent();
    }

    @Override
    public E next() {
        prefetchedAndMemorizeNonEmpty();
        if (!prefetched.isPresent()) {
            throw new NoSuchElementException();
        }
        return prefetched.unload().get().next();
    }

    private void prefetchedAndMemorizeNonEmpty() {
        if (prefetched.isPresent()) {
            return;
        }
        while (iterators.hasNext()) {
            final Iterator<E> candidate = iterators.next();
            if (candidate.hasNext()) {
                prefetched.setContent(candidate);
                memory.push(candidate);
                return;
            }
        }
        while (!memory.isEmpty()) {
            Iterator<E> candidate = memory.removeLast();
            if (candidate.hasNext()) {
                prefetched.setContent(candidate);
                memory.push(candidate);
                return;
            }
        }
    }
}
