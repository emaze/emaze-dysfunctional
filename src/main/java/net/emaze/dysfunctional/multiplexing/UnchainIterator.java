package net.emaze.dysfunctional.multiplexing;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Supplier;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;
import net.emaze.dysfunctional.options.Box;

/**
 *
 *
 * @param <C>
 * @param <T>
 * @author rferranti
 */
public class UnchainIterator<C extends Collection<T>, T> extends ReadOnlyIterator<C> {

    private final Iterator<T> iterator;
    private final int channelSize;
    private final Supplier<C> channelProvider;
    private final Box<C> prefetched = Box.empty();

    public UnchainIterator(int channelSize, Iterator<T> iterator, Supplier<C> channelProvider) {
        dbc.precondition(channelSize > 0, "max channel size must be > 0");
        dbc.precondition(iterator != null, "iterator cannot be null");
        dbc.precondition(channelProvider != null, "channelProvider cannot be null");
        this.iterator = iterator;
        this.channelSize = channelSize;
        this.channelProvider = channelProvider;
    }

    @Override
    public boolean hasNext() {
        if (prefetched.isEmpty()) {
            prefetched.setContent(prefetch(iterator, channelSize));
        }
        return prefetched.getContent().size() != 0;
    }

    @Override
    public C next() {
        if (prefetched.isEmpty()) {
            prefetched.setContent(prefetch(iterator, channelSize));
        }
        if (prefetched.getContent().size() == 0) {
            throw new NoSuchElementException();
        }
        return prefetched.unload().get();
    }

    private C prefetch(Iterator<T> iter, int size) {
        final C result = channelProvider.get();
        for (int counter = 0; counter != size && iter.hasNext(); ++counter) {
            result.add(iter.next());
        }
        return result;
    }
}
