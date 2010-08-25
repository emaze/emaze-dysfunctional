package net.emaze.disfunctional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author rferranti
 */
public class ChainIterator<E> implements Iterator<E> {

    private final List<Iterator<E>> iterators;

    public ChainIterator(Iterator<E>... iterators) {
        this.iterators = Arrays.asList(iterators);
    }

    public ChainIterator(Iterable<E>... iterables) {
        this.iterators = new ArrayList<Iterator<E>>();
        for (Iterable iterable : iterables) {
            this.iterators.add(iterable.iterator());
        }
    }

    public boolean hasNext() {
        if (iterators.isEmpty()) {
            return false;
        }
        return Iterations.any(iterators,new Predicate<Iterator<E>>() {
            public boolean match(Iterator<E> iterator) {
                return iterator.hasNext();
            }
        });
    }


    private Maybe<Iterator<E>> currentElement(){
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

    public E next() {
        final Maybe<Iterator<E>> maybeElement = currentElement();
        if(maybeElement.hasValue()){
            return maybeElement.value().next();
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        final Maybe<Iterator<E>> maybeElement = currentElement();
        if(maybeElement.hasValue()){
            maybeElement.value().remove();
        }
    }
}
