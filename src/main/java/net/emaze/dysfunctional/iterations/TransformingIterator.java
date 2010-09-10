package net.emaze.dysfunctional.iterations;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;

/**
 * Decorates an iterator adapting (via a delegate) consumed elements
 * @author rferranti
 */
public class TransformingIterator<R,T> implements Iterator<R> {

    private final Delegate<R,T> transformer;
    private final Iterator<T> iterator;

    public TransformingIterator(Iterator<T> iterator, Delegate<R,T> filter) {
        dbc.precondition(iterator != null, "trying to create a TransformingIterator from a null iterator");
        dbc.precondition(filter != null, "trying to create a TransformingIterator with a null filter");
        this.iterator = iterator;
        this.transformer = filter;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public R next() {
        return transformer.perform(iterator.next());
    }

    @Override
    public void remove() {
        iterator.remove();
    }

}