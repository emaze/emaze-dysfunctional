package net.emaze.dysfunctional.iterations;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * A composite iterator interleaving value yielded by the values iterator
 * with value from the separators iterator.
 * separators iterator length must be >= values iterator length
 * @author rferranti
 */
public class InterleavingIterator<T> implements Iterator<T> {

    private final Iterator<T> values;
    private final Iterator<T> separators;
    private boolean consumingSeparator = false;

    public InterleavingIterator(Iterator<T> values, Iterator<T> separators) {
        this.values = values;
        this.separators = separators;
    }

    @Override
    public boolean hasNext() {
        return values.hasNext();
    }

    @Override
    public T next() {
        final Iterator<T> toBeConsumed = consumingSeparator
                ? separators
                : values;
        dbc.invariant(!consumingSeparator || separators.hasNext(), "consuming an InterleavingIterator led to an empty separatorIterator (which should be same length or longer than the valueIterator)");
        final T value = toBeConsumed.next();
        consumingSeparator = !consumingSeparator;
        return value;
    }

    @Override
    public void remove() {
        throw new IllegalStateException("trying to remove a value from an interleavingIterator");
    }
}
