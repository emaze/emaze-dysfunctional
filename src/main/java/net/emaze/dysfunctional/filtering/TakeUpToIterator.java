package net.emaze.dysfunctional.filtering;

import java.util.Iterator;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.contracts.dbc;

public class TakeUpToIterator<E> implements Iterator<E> {

    private final Iterator<E> iterator;
    private long left;

    public TakeUpToIterator(Iterator<E> iterator, long howMany) {
        dbc.precondition(iterator != null, "trying to create a TakeUpToIterator from a null iterator");
        dbc.precondition(howMany >= 0, "trying to create a TakeUpToIterator from a negative howMany");
        this.iterator = iterator;
        this.left = howMany;
    }

    @Override
    public boolean hasNext() {
        return left != 0 && iterator.hasNext();
    }

    @Override
    public E next() {
        if (left == 0) {
            throw new NoSuchElementException();
        }
        final E el = iterator.next();
        --left;
        return el;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
