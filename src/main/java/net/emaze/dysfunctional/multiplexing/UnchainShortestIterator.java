package net.emaze.dysfunctional.multiplexing;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.tuples.Pair;

/**
 * shortest
 *
 * @param <C>
 * @param <T>
 * @author rferranti
 */
public class UnchainShortestIterator<C extends Collection<T>, T> extends ReadOnlyIterator<C> {

    private final Supplier<Optional<Integer>> channelsSizesProvider;
    private final Iterator<T> iterator;
    private final Supplier<C> channelProvider;
    private final Box<Pair<Integer, C>> prefetched = Box.empty();

    public UnchainShortestIterator(Supplier<Optional<Integer>> channelsSizesProvider, Iterator<T> iterator, Supplier<C> channelProvider) {
        dbc.precondition(channelsSizesProvider != null, "channelsSizes cannot be null");
        dbc.precondition(iterator != null, "iterator cannot be null");
        dbc.precondition(channelProvider != null, "channelProvider cannot be null");
        this.channelsSizesProvider = channelsSizesProvider;
        this.iterator = iterator;
        this.channelProvider = channelProvider;
    }

    @Override
    public boolean hasNext() {
        if (!prefetched.isPresent()) {
            prefetched.setContent(fetch(iterator, channelsSizesProvider));
        }
        return prefetched.getContent().second().size() == prefetched.getContent().first();
    }

    @Override
    public C next() {
        if (prefetched.isPresent()) {
            if (prefetched.getContent().second().size() != prefetched.getContent().first()) {
                throw new NoSuchElementException("iterator is not squared");
            }
            return prefetched.unload().get().second();
        }
        return fetch(iterator, channelsSizesProvider).second();
    }

    private Pair<Integer, C> fetch(Iterator<T> iter, Supplier<Optional<Integer>> sizeProvider) {
        final Optional<Integer> maybeChannelSize = sizeProvider.get();
        dbc.state(maybeChannelSize.isPresent(), "unexpected channel size request (supplier returned Nothing)");
        final int channelSize = maybeChannelSize.get();
        final C result = channelProvider.get();
        for (int counter = 0; counter != channelSize && iter.hasNext(); ++counter) {
            result.add(iter.next());
        }
        return Pair.of(channelSize, result);
    }
}
