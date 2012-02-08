package net.emaze.dysfunctional.multiplexing;

import java.util.Collection;
import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;
import net.emaze.dysfunctional.options.Maybe;

/**
 * squared
 *
 * @param <C> 
 * @param <T>
 * @author rferranti
 */
public class PreciseDemultiplexingIterator<C extends Collection<Maybe<T>>, T> extends ReadOnlyIterator<C> {

    private final Iterator<Maybe<T>> iterator;
    private final int channelSize;
    private final Provider<C> channelProvider;

    public PreciseDemultiplexingIterator(int channelSize, Iterator<Maybe<T>> iterator, Provider<C> channelProvider) {
        dbc.precondition(channelSize > 0, "cannot build a PreciseDemultiplexingIterator with channelsCount < 1");
        dbc.precondition(iterator != null, "cannot build a PreciseDemultiplexingIterator with a null iterator");
        dbc.precondition(channelProvider != null, "cannot build a PreciseDemultiplexingIterator with a null channelProvider");
        this.iterator = iterator;
        this.channelSize = channelSize;
        this.channelProvider = channelProvider;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public C next() {
        final C out = channelProvider.provide();
        for (int i = 0; i != channelSize; ++i) {
            out.add(iterator.next());
        }
        return out;
    }
}
