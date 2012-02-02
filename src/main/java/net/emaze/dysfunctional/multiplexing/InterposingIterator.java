package net.emaze.dysfunctional.multiplexing;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;

/**
 * A composite iterator interposing value yielded by the values iterator
 * with value from the separators iterator.
 * (separators iterator length) must be >= (values iterator length - 1)
 * @param <T>
 * @author rferranti
 */
public class InterposingIterator<T> extends ReadOnlyIterator<T> {

    private final Iterator<T> values;
    private final Iterator<T> separators;
    private boolean consumingSeparator = false;

    public InterposingIterator(Iterator<T> values, Iterator<T> separators) {
        dbc.precondition(values != null, "cannot create an InterposingIterator with a null values iterator");
        dbc.precondition(separators != null, "cannot create an InterposingIterator with a null separators iterator");
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
        dbc.state(!consumingSeparator || separators.hasNext(), "consuming an InterleavingIterator led to an empty separatorIterator (which should be same length or longer than the valueIterator)");
        final T value = toBeConsumed.next();
        consumingSeparator = !consumingSeparator;
        return value;
    }
}
