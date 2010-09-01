package net.emaze.dysfunctional.iterations;

import java.util.Iterator;
import net.emaze.dysfunctional.delegates.Delegate;

/**
 *
 * @author rferranti
 */
public class TransformingIterator<R,T> implements Iterator<R> {

    private final Delegate<R,T> transformer;
    private final Iterator<T> iterator;

    public TransformingIterator(Iterator<T> iterator, Delegate<R,T> filter) {
        this.iterator = iterator;
        this.transformer = filter;
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public R next() {
        return transformer.perform(iterator.next());
    }

    public void remove() {
        iterator.remove();
    }

}
