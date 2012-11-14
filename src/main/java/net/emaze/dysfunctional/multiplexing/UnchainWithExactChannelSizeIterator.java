package net.emaze.dysfunctional.multiplexing;

import java.util.Collection;
import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.options.MaybeIterator;

/**
 * squared
 *
 * @param <C>
 * @param <T>
 * @author rferranti
 */
public class UnchainWithExactChannelSizeIterator<C extends Collection<Maybe<T>>, T> extends ReadOnlyIterator<C> {

    private final Iterator<Maybe<T>> iterator;
    private final Provider<Maybe<Integer>> channelsSizesProvider;
    private final Provider<C> channelProvider;

    public UnchainWithExactChannelSizeIterator(Provider<Maybe<Integer>> channelsSizesProvider, Iterator<T> iterator, Provider<C> channelProvider) {
        dbc.precondition(channelsSizesProvider != null, "cannot build a UnchainWithExactChannelSizeIterator with channelsSizesProvider < 1");
        dbc.precondition(iterator != null, "cannot build a UnchainWithExactChannelSizeIterator with a null iterator");
        dbc.precondition(channelProvider != null, "cannot build a UnchainWithExactChannelSizeIterator with a null channelProvider");
        this.iterator = new MaybeIterator<T>(iterator);
        this.channelsSizesProvider = channelsSizesProvider;
        this.channelProvider = channelProvider;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public C next() {
        final C out = channelProvider.provide();
        final Maybe<Integer> maybeChannelSize = channelsSizesProvider.provide();
        dbc.state(maybeChannelSize.hasValue(), "unexpected channel size request (provider returned Nothing)");
        final int channelSize = maybeChannelSize.value();
        for (int i = 0; i != channelSize; ++i) {
            out.add(iterator.next());
        }
        return out;
    }
}
