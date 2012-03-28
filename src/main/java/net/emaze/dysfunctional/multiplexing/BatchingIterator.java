package net.emaze.dysfunctional.multiplexing;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;
import net.emaze.dysfunctional.options.Box;

/**
 *
 *
 * @param <C>
 * @param <T>
 * @author rferranti
 */
public class BatchingIterator<C extends Collection<T>, T> extends ReadOnlyIterator<C> {

    private final Iterator<T> iterator;
    private final int maxChannelSize;
    private final Provider<C> channelProvider;
    private final Box<C> prefetched = Box.empty();

    public BatchingIterator(int maxChannelSize, Iterator<T> iterator, Provider<C> channelProvider) {
        dbc.precondition(maxChannelSize > 0, "max channel size must be > 0");
        dbc.precondition(iterator != null, "iterator cannot be null");
        dbc.precondition(channelProvider != null, "channelProvider cannot be null");
        this.iterator = iterator;
        this.maxChannelSize = maxChannelSize;
        this.channelProvider = channelProvider;
    }

    @Override
    public boolean hasNext() {
        if (prefetched.isEmpty()) {
            prefetched.setContent(prefetch(iterator, maxChannelSize));
        }
        return prefetched.getContent().size() != 0;
    }

    @Override
    public C next() {
        if (prefetched.isEmpty()) {
            prefetched.setContent(prefetch(iterator, maxChannelSize));
        }
        if (prefetched.getContent().size() == 0) {
            throw new NoSuchElementException();
        }
        return prefetched.unload().value();
    }

    private C prefetch(Iterator<T> iter, int size) {
        final C result = channelProvider.provide();
        for (int counter = 0; counter != size && iter.hasNext(); ++counter) {
            result.add(iter.next());
        }
        return result;
    }
}
