package net.emaze.dysfunctional.multiplexing;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;

/**
 * A cyclic iterator. The given iterator is iterated indefinitely.
 *
 * @param <E> the element type
 * @author mcodella, asturman, rferranti
 */
public class CyclicIterator<E> extends ReadOnlyIterator<E> {

    private final Deque<E> memory = new LinkedList<E>();
    private Iterator<E> source;

    public CyclicIterator(Iterator<E> source) {
        dbc.precondition(source != null, "source iterator cannot be null");
        this.source = source;
    }

    @Override
    public boolean hasNext() {
        return source.hasNext() || !memory.isEmpty();
    }

    @Override
    public E next() {
        final E value = source.hasNext() ? source.next() : memory.removeLast();
        memory.push(value);
        return value;
    }
}
