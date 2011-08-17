package net.emaze.dysfunctional.iterations;

import java.util.Iterator;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.contracts.dbc;

public class TruncatingIterator<T> implements Iterator<T> {

    private final Iterator<T> inner;
    private long remaining;

    public TruncatingIterator(Iterator<T> inner, long size) {
        dbc.precondition(inner != null, "cannot create a truncating iterator with a null iterator");
        dbc.precondition(inner != null, "cannot create a truncating iterator with a null iterator");
        this.inner = inner;
        this.remaining = size;
    }

    @Override
    public boolean hasNext() {
        return remaining != 0 && inner.hasNext();
    }

    @Override
    public T next() {
        if (remaining == 0) {
            throw new NoSuchElementException("TruncatingIterator is consumed");
        }
        --remaining;
        return inner.next();
    }

    @Override
    public void remove() {
        inner.remove();
    }
}
