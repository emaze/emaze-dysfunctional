package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;
import net.emaze.dysfunctional.options.Maybe;

/**
 * shortest
 * @param <T> 
 * @author rferranti
 */
public class DemultiplexingIterator<T> extends ReadOnlyIterator<List<T>> {

    private final Iterator<T> iterator;
    private final int channelSize;
    private Maybe<List<T>> prefetched = Maybe.nothing();

    public DemultiplexingIterator(int channelSize, Iterator<T> iterator) {
        dbc.precondition(channelSize > 0, "channels count must be > 0");
        dbc.precondition(iterator != null, "iterator cannot be null");
        this.iterator = iterator;
        this.channelSize = channelSize;
    }

    @Override
    public boolean hasNext() {
        if (!prefetched.hasValue()) {
            prefetched = Maybe.just(prefetch(iterator, channelSize));
        }
        return prefetched.value().size() == channelSize;
    }

    @Override
    public List<T> next() {
        if (prefetched.hasValue()) {
            if (prefetched.value().size() != channelSize) {
                throw new NoSuchElementException("iterator is not squared");
            }
            final List<T> value = prefetched.value();
            prefetched = Maybe.nothing();
            return value;
        }
        final List<T> out = new ArrayList<T>(channelSize);
        for (int i = 0; i != channelSize; ++i) {
            out.add(iterator.next());
        }
        return out;
    }

    private static <T> List<T> prefetch(Iterator<T> iter, int size) {
        final List<T> result = new ArrayList<T>(size);
        for (int counter = 0; counter != size && iter.hasNext(); ++counter) {
            result.add(iter.next());

        }
        return result;
    }
}
