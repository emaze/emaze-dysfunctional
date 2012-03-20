package net.emaze.dysfunctional.multiplexing;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;
import net.emaze.dysfunctional.options.Maybe;

/**
 * shortest
 *
 * @param <C> 
 * @param <T>
 * @author rferranti
 */
public class DemultiplexingIterator<C extends Collection<T>, T> extends ReadOnlyIterator<C> {

    private final Iterator<T> iterator;
    private final int channelSize;
    private final Provider<C> channelProvider;
    private Maybe<C> prefetched = Maybe.nothing();

    public DemultiplexingIterator(int channelSize, Iterator<T> iterator, Provider<C> channelProvider) {
        dbc.precondition(channelSize > 0, "channels size must be > 0");
        dbc.precondition(iterator != null, "iterator cannot be null");
        dbc.precondition(channelProvider != null, "channelProvider cannot be null");
        this.iterator = iterator;
        this.channelSize = channelSize;
        this.channelProvider = channelProvider;
    }

    @Override
    public boolean hasNext() {
        if (!prefetched.hasValue()) {
            prefetched = Maybe.just(prefetch(iterator, channelSize));
        }
        return prefetched.value().size() == channelSize;
    }

    @Override
    public C next() {
        if (prefetched.hasValue()) {
            if (prefetched.value().size() != channelSize) {
                throw new NoSuchElementException("iterator is not squared");
            }
            final C value = prefetched.value();
            prefetched = Maybe.nothing();
            return value;
        }
        final C out = channelProvider.provide();
        for (int i = 0; i != channelSize; ++i) {
            out.add(iterator.next());
        }
        return out;
    }

    private C prefetch(Iterator<T> iter, int size) {
        final C result = channelProvider.provide();
        for (int counter = 0; counter != size && iter.hasNext(); ++counter) {
            result.add(iter.next());
        }
        return result;
    }
}
