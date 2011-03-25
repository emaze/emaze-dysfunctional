package net.emaze.dysfunctional.multiplexing;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.consumers.Consumers;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.HasNext;
import net.emaze.dysfunctional.iterations.Iterations;

/**
 * A composite iterator (iterators are consumed in order)
 * @param <E>
 * @author rferranti
 */
public class ChainIterator<E> implements Iterator<E> {

    private final List<Iterator<E>> iterators = new ArrayList<Iterator<E>>();

    public ChainIterator(Iterator<E>... iterators) {
        dbc.precondition(iterators != null, "trying to create a ChainIterator from a null array of iterators");
        this.iterators.addAll(Arrays.asList(iterators));
    }

    public <T extends Iterator<E>> ChainIterator(Iterator<T> iterators) {
        dbc.precondition(iterators != null, "trying to create a ChainIterator from a null list of iterators");
        this.iterators.addAll(Consumers.all(iterators));
    }

    @Override
    public boolean hasNext() {
        return iterators.isEmpty() ? false : Iterations.any(iterators, new HasNext<Iterator<E>>());
    }

    private static <E> Maybe<Iterator<E>> currentElement(List<Iterator<E>> iterators) {
        final Iterator<Iterator<E>> iteratorOfIterators = iterators.iterator();
        while (iteratorOfIterators.hasNext()) {
            final Iterator<E> iterator = iteratorOfIterators.next();
            if (!iterator.hasNext()) {
                iteratorOfIterators.remove();
                continue;
            }
            return Maybe.just(iterator);
        }
        return Maybe.nothing();
    }

    @Override
    public E next() {
        final Maybe<Iterator<E>> maybeElement = currentElement(iterators);
        if (maybeElement.hasValue()) {
            return maybeElement.value().next();
        }
        throw new NoSuchElementException("iterator is consumed");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("cannot remove from a ChainIterator");
    }
}
