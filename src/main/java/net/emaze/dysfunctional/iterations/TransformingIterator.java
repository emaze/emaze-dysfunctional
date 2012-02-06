package net.emaze.dysfunctional.iterations;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Decorates an iterator adapting (via a delegate) consumed elements
 *
 * @param <R> the resulting element type
 * @param <T> the source element type
 * @author rferranti
 */
public class TransformingIterator<R, T> implements Iterator<R> {

    private final Delegate<R, T> transformer;
    private final Iterator<T> iterator;

    public TransformingIterator(Iterator<T> iterator, Delegate<R, T> transformer) {
        dbc.precondition(iterator != null, "trying to create a TransformingIterator from a null iterator");
        dbc.precondition(transformer != null, "trying to create a TransformingIterator with a null transformer");
        this.iterator = iterator;
        this.transformer = transformer;
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