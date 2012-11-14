package net.emaze.dysfunctional.multiplexing;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.options.Maybe;

/**
 * squared: [1,2] [a,b,c] -> just(1),just(a),just(2),just(b),nothing,just(c)
 *
 * @param <E> the iterator element type
 * @author rferranti
 */
public class RoundRobinLongestIterator<E> extends ReadOnlyIterator<Maybe<E>> {

    private final Iterator<? extends Iterator<E>> iterators;
    private final Deque<Iterator<E>> memory = new LinkedList<Iterator<E>>();
    private final Box<Iterator<E>> prefetched = Box.empty();
    private int fetchedCounter;

    public <T extends Iterator<E>> RoundRobinLongestIterator(Iterator<T> iterators) {
        dbc.precondition(iterators != null, "trying to create a RoundRobinLongestIterator from a null iterator of iterators");
        this.iterators = iterators;
    }

    @Override
    public boolean hasNext() {
        prefetchAndMemorize();
        return !empty();
    }

    private boolean empty() {
        return !prefetched.hasContent() || (!prefetched.getContent().hasNext() && fetchedCounter % memory.size() == 0);
    }

    @Override
    public Maybe<E> next() {
        prefetchAndMemorize();
        if (empty()) {
            throw new NoSuchElementException();
        }
        ++fetchedCounter;
        final Iterator<E> current = prefetched.unload().value();
        return current.hasNext() ? Maybe.just(current.next()) : Maybe.<E>nothing();
    }

    private void prefetchAndMemorize() {
        if (prefetched.hasContent()) {
            return;
        }
        if (iterators.hasNext()) {
            final Iterator<E> iterator = iterators.next();
            prefetched.setContent(iterator);
            memory.push(iterator);
            return;
        }
        if (memory.isEmpty()) {
            return;
        }
        fetchedCounter = fetchedCounter % memory.size();
        final Iterator<E> iter = memory.removeLast();
        prefetched.setContent(iter);
        memory.push(iter);
    }
}
