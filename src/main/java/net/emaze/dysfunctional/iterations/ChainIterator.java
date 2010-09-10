package net.emaze.dysfunctional.iterations;

import java.util.Arrays;
import net.emaze.dysfunctional.delegates.Predicate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.Maybe;
import net.emaze.dysfunctional.contracts.dbc;

/**
 *
 * @author rferranti
 */
public class ChainIterator<E> implements Iterator<E> {

    private final List<Iterator<E>> iterators = new ArrayList<Iterator<E>>();

    public ChainIterator(Iterator<E>... iterators) {
        this.iterators.addAll(Arrays.asList(iterators));
    }

    public ChainIterator(List<Iterator<E>> iterators) {
        dbc.precondition(iterators != null, "trying to create a ChainIterator from a null list of iterators");
        this.iterators.addAll(iterators);
    }

    @Override
    public boolean hasNext() {
        if (iterators.isEmpty()) {
            return false;
        }
        return Iterations.any(iterators, new Predicate<Iterator<E>>() {

            @Override
            public boolean test(Iterator<E> iterator) {
                return iterator.hasNext();
            }
        });
    }

    private Maybe<Iterator<E>> currentElement() {
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
        final Maybe<Iterator<E>> maybeElement = currentElement();
        if (maybeElement.hasValue()) {
            return maybeElement.value().next();
        }
        throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        final Maybe<Iterator<E>> maybeElement = currentElement();
        if (maybeElement.hasValue()) {
            maybeElement.value().remove();
        }
    }
}
