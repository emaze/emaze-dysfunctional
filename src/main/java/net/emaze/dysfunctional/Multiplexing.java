package net.emaze.dysfunctional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.casts.Vary;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.IteratorPlucker;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.multiplexing.BatchingIterator;
import net.emaze.dysfunctional.multiplexing.ChainIterator;
import net.emaze.dysfunctional.multiplexing.DemultiplexingIterator;
import net.emaze.dysfunctional.multiplexing.MultiplexingIterator;
import net.emaze.dysfunctional.multiplexing.PreciseDemultiplexingIterator;
import net.emaze.dysfunctional.multiplexing.PreciseMultiplexingIterator;
import net.emaze.dysfunctional.multiplexing.RoundRobinIterator;
import net.emaze.dysfunctional.options.Maybe;

/**
 * flatten, chain, mux, muxLongest, demux, demuxLongest, roundrobin.
 *
 * @author rferranti
 */
public abstract class Multiplexing {

    /**
     * Flattens an iterator of iterables of E to an iterator of E. E.g:
     * <code>
     * flatten([1,2], [3,4]) -> [1,2,3,4]
     * </code>
     *
     * @param <E> the iterable element type
     * @param <I> the iterable type
     * @param iterables the iterator of iterables to be flattened
     * @return the flattened iterator
     */
    public static <E, I extends Iterable<E>> Iterator<E> flatten(Iterator<I> iterables) {
        return new ChainIterator<E>(Applications.transform(iterables, new IteratorPlucker<E, I>()));
    }

    /**
     * Flattens an iterable of iterables of E to an iterator of E.E.g:
     * <code>
     * flatten([1,2], [3,4]) -> [1,2,3,4]
     * </code>
     *
     * @param <E> the iterable element type
     * @param <I> the iterable type
     * @param iterables the iterable of iterables to be flattened
     * @return the flattened iterator
     */
    public static <E, I extends Iterable<E>> Iterator<E> flatten(Iterable<I> iterables) {
        dbc.precondition(iterables != null, "cannot flatten a null iterable");
        return flatten(iterables.iterator());
    }

    /**
     * Flattens passed iterables of E to an iterator of E. E.g:
     * <code>
     * flatten([1,2], [3,4]) -> [1,2,3,4]
     * </code>
     *
     * @param <E> the iterable element type
     * @param <I> the iterable type
     * @param first the first iterable to be flattened
     * @param second the second iterable to be flattened
     * @return the flattened iterator
     */
    public static <E, I extends Iterable<E>> Iterator<E> flatten(I first, I second) {
        return flatten(Iterations.iterator(first, second));
    }

    /**
     * Flattens passed iterables of E to an iterator of E. E.g:
     * <code>
     * flatten([1,2], [3,4], [5,6]) -> [1,2,3,4,5,6]
     * </code>
     *
     * @param <E> the iterable element type
     * @param <I> the iterable type
     * @param first the first iterable to be flattened
     * @param second the second iterable to be flattened
     * @param third the third iterable to be flattened
     * @return the flattened iterator
     */
    public static <E, I extends Iterable<E>> Iterator<E> flatten(I first, I second, I third) {
        return flatten(Iterations.iterator(first, second, third));
    }

    /**
     * Flattens an iterator of iterators of E to an iterator of E. E.g:
     * <code>
     * chain([1,2], [3,4]) -> [1,2,3,4]
     * </code>
     *
     * @param <E> the iterator element type
     * @param <I> the iterator type
     * @param iterators the source iterators
     * @return the flattened iterator
     */
    public static <E, I extends Iterator<E>> Iterator<E> chain(Iterator<I> iterators) {
        return new ChainIterator<E>(iterators);
    }

    /**
     * Flattens an iterable of iterators of E to an iterator of E. E.g:
     * <code>
     * chain([1,2], [3,4]) -> [1,2,3,4]
     * </code>
     *
     * @param <E> the iterator element type
     * @param <I> the iterator type
     * @param iterable the source iterable
     * @return the flattened iterator
     */
    public static <E, I extends Iterator<E>> Iterator<E> chain(Iterable<I> iterable) {
        dbc.precondition(iterable != null, "cannot chain a null iterable");
        return new ChainIterator<E>(iterable.iterator());
    }

    /**
     * Flattens passed iterators of E to an iterator of E. E.g:
     * <code>
     * chain([1,2], [3,4]) -> [1,2,3,4]
     * </code>
     *
     * @param <E> the iterator element type
     * @param <I> the iterator type
     * @param first the first iterator to be flattened
     * @param second the second iterator to be flattened
     * @return the flattened iterator
     */
    public static <E, I extends Iterator<E>> Iterator<E> chain(I first, I second) {
        return new ChainIterator<E>(Iterations.iterator(first, second));
    }

    /**
     * Flattens passed iterators of E to an iterator of E. E.g:
     * <code>
     * chain([1,2], [3,4], [5,6]) -> [1,2,3,4,5,6]
     * </code>
     *
     * @param <E> the iterator element type
     * @param <I> the iterator type
     * @param first the first iterator to be flattened
     * @param second the second iterator to be flattened
     * @param third the third iterator to be flattened
     * @return the flattened iterator
     */
    public static <E, I extends Iterator<E>> Iterator<E> chain(I first, I second, I third) {
        return new ChainIterator<E>(Iterations.iterator(first, second, third));
    }

    /**
     * Multiplexes an iterator of iterators into a single iterator. The
     * iteration stops whenever any of the source iterators is empty causing the
     * same number of elements to be consumed from every iterator.
     * <code>
     * mux([1,2,3], [4,5]) -> [1,4,2,5]
     * </code>
     *
     * @param <E> the element type
     * @param <I> the iterator type
     * @param iterators the iterator of iterators to be multiplexed
     * @return an iterator containing multiplexed elements
     */
    public static <E, I extends Iterator<E>> Iterator<E> mux(Iterator<I> iterators) {
        return new MultiplexingIterator<E>(iterators);
    }

    /**
     * Multiplexes an iterable of iterators into a single iterator. The
     * iteration stops whenever any of the source iterators is empty causing the
     * same number of elements to be consumed from every iterator. E.g:
     * <code>
     * mux([1,2,3], [4,5]) -> [1,4,2,5]
     * </code>
     *
     * @param <E> the element type
     * @param <I> the source iterator type
     * @param iterable the source iterable
     * @return an iterator containing multiplexed elements
     */
    public static <E, I extends Iterator<E>> Iterator<E> mux(Iterable<I> iterable) {
        dbc.precondition(iterable != null, "cannot chain a null iterable");
        return mux(iterable.iterator());
    }

    /**
     * Multiplexes two iterators into a single iterator. The iteration stops
     * whenever any of the source iterators is empty causing the same number of
     * elements to be consumed from every iterator. E.g:
     * <code>
     * mux([1,2], [3]) -> [1,3]
     * </code>
     *
     * @param <E> the element type
     * @param first the first source iterator
     * @param second the second source iterator
     * @return an iterator containing multiplexed elements
     */
    public static <E> Iterator<E> mux(Iterator<E> first, Iterator<E> second) {
        return mux(Iterations.iterator(first, second));
    }

    /**
     * Multiplexes three iterators into a single iterator. The iteration stops
     * whenever any of the source iterators is empty causing the same number of
     * elements to be consumed from every iterator. E.g:
     * <code>
     * mux([1,2], [3], [4]) -> [1, 3, 4]
     * </code>
     *
     * @param <E> the element type
     * @param first the first source iterator
     * @param second the second source iterator
     * @param third the third source iterator
     * @return an iterator containing multiplexed elements
     */
    public static <E> Iterator<E> mux(Iterator<E> first, Iterator<E> second, Iterator<E> third) {
        return mux(Iterations.iterator(first, second, third));
    }

    /**
     * Multiplexes an iterator of iterators into a single iterator. The
     * iteration stops when every source iterator is empty.E.g:
     * <code>
     * muxLongest([1,2], [4]) -> [just 1, just 4, just 2, nothing]
     * </code>
     *
     * @param <E> the element type
     * @param <I> the iterator type
     * @param iterators the source iterator
     * @return an iterator containing multiplexed elements
     */
    public static <E, I extends Iterator<E>> Iterator<Maybe<E>> muxLongest(Iterator<I> iterators) {
        return new PreciseMultiplexingIterator<E>(iterators);
    }

    /**
     * Multiplexes an iterable of iterators into a single iterator. The
     * iteration stops when every source iterator is empty.E.g:
     * <code>
     * muxLongest([1,2], [4]) -> [just 1, just 4, just 2, nothing]
     * </code>
     *
     * @param <E> the element type
     * @param <I> the iterator type
     * @param iterable the source iterable
     * @return an iterator containing multiplexed elements
     */
    public static <E, I extends Iterator<E>> Iterator<Maybe<E>> muxLongest(Iterable<I> iterable) {
        dbc.precondition(iterable != null, "cannot muxLongest a null iterable");
        return muxLongest(iterable.iterator());
    }

    /**
     * Multiplexes two iterators into a single iterator. The iteration stops
     * when every source iterator is empty.E.g:
     * <code>
     * muxLongest([1,2], [4]) -> [just 1, just 4, just 2, nothing]
     * </code>
     *
     * @param <E> the element type
     * @param first the first source iterator
     * @param second the second source iterator
     * @return an iterator containing multiplexed elements
     */
    public static <E> Iterator<Maybe<E>> muxLongest(Iterator<E> first, Iterator<E> second) {
        return muxLongest(Iterations.iterator(first, second));
    }

    /**
     * Multiplexes three iterators into a single iterator. The iteration stops
     * when every source iterator is empty. E.g:
     * <code>
     * muxLongest([1,2], [4], [5]) -> [just 1, just 4, just 5, just 2, nothing, nothing]
     * </code>
     *
     * @param <E> the element type
     * @param first the first source iterator
     * @param second the second source iterator
     * @param third the third source iterator
     * @return an iterator containing multiplexed elements
     */
    public static <E> Iterator<Maybe<E>> muxLongest(Iterator<E> first, Iterator<E> second, Iterator<E> third) {
        return muxLongest(Iterations.iterator(first, second, third));
    }

    /**
     * Demultiplexes elements from the source iterator into an iterator of
     * channels.
     *
     *
     * @param <C> the channel collection type
     * @param <E> the element type
     * @param channelSize size of the channel
     * @param iterator the source iterator
     * @param channelProvider a provider used to create channels
     * @return an iterator of channels
     */
    public static <C extends Collection<E>, E> Iterator<C> demux(int channelSize, Iterator<E> iterator, Provider<C> channelProvider) {
        return new DemultiplexingIterator<C, E>(channelSize, iterator, channelProvider);
    }

    /**
     * Demultiplexes elements from the source iterator into an iterator of
     * channels.
     *
     * @param <E> the element type
     * @param channelSize size of the channel
     * @param iterator the source iterator
     * @return an iterator of channels
     */
    public static <E> Iterator<List<E>> demux(int channelSize, Iterator<E> iterator) {
        final Provider<List<E>> channelFactory = Compositions.compose(new Vary<List<E>, ArrayList<E>>(), new ArrayListFactory<E>());
        return new DemultiplexingIterator<List<E>, E>(channelSize, iterator, channelFactory);
    }

    /**
     * Demultiplexes elements from the source iterable into an iterator of
     * channels.
     *
     * @param <C> the channel collection type
     * @param <E> the element type
     * @param channelSize size of the channel
     * @param iterable the source iterable
     * @param channelProvider the provider used to create channels
     * @return an iterator of channels
     */
    public static <C extends Collection<E>, E> Iterator<C> demux(int channelSize, Iterable<E> iterable, Provider<C> channelProvider) {
        dbc.precondition(iterable != null, "cannot demux a null iterable");
        return new DemultiplexingIterator<C, E>(channelSize, iterable.iterator(), channelProvider);
    }

    /**
     * Demultiplexes elements from the source iterable into an iterator of
     * channels.
     *
     * @param <E> the element type
     * @param channelSize size of the channel
     * @param iterable the source iterable
     * @return an iterator of channels
     */
    public static <E> Iterator<List<E>> demux(int channelSize, Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot demux a null iterable");
        final Provider<List<E>> channelFactory = Compositions.compose(new Vary<List<E>, ArrayList<E>>(), new ArrayListFactory<E>());
        return new DemultiplexingIterator<List<E>, E>(channelSize, iterable.iterator(), channelFactory);
    }

    /**
     * Demultiplexes elements from the source array into an iterator of
     * channels.
     *
     * @param <C> the channel collection type
     * @param <E> the element type
     * @param channelSize size of the channel
     * @param array the source array
     * @param channelProvider the provider used to create channels
     * @return an iterator of channels
     */
    public static <C extends Collection<E>, E> Iterator<C> demux(int channelSize, Provider<C> channelProvider, E... array) {
        return new DemultiplexingIterator<C, E>(channelSize, new ArrayIterator<E>(array), channelProvider);
    }

    /**
     * Demultiplexes elements from the source array into an iterator of
     * channels.
     *
     * @param <E> the element type
     * @param channelSize size of the channel
     * @param array the source array
     * @return an iterator of channels
     */
    public static <E> Iterator<List<E>> demux(int channelSize, E... array) {
        final Provider<List<E>> channelFactory = Compositions.compose(new Vary<List<E>, ArrayList<E>>(), new ArrayListFactory<E>());
        return new DemultiplexingIterator<List<E>, E>(channelSize, new ArrayIterator<E>(array), channelFactory);
    }

    /**
     * Demultiplexes elements from the source iterator into an iterator of
     * channels.
     *
     *
     * @param <C> the channel collection type
     * @param <E> the element type
     * @param maxChannelSize maximum size of the channel
     * @param iterator the source iterator
     * @param channelProvider a provider used to create channels
     * @return an iterator of channels
     */
    public static <C extends Collection<E>, E> Iterator<C> batch(int maxChannelSize, Iterator<E> iterator, Provider<C> channelProvider) {
        return new BatchingIterator<C, E>(maxChannelSize, iterator, channelProvider);
    }

    /**
     * Demultiplexes elements from the source iterator into an iterator of
     * channels.
     *
     * @param <E> the element type
     * @param maxChannelSize maximum size of the channel
     * @param iterator the source iterator
     * @return an iterator of channels
     */
    public static <E> Iterator<List<E>> batch(int maxChannelSize, Iterator<E> iterator) {
        final Provider<List<E>> channelFactory = Compositions.compose(new Vary<List<E>, ArrayList<E>>(), new ArrayListFactory<E>());
        return new BatchingIterator<List<E>, E>(maxChannelSize, iterator, channelFactory);
    }

    /**
     * Demultiplexes elements from the source iterable into an iterator of
     * channels.
     *
     * @param <C> the channel collection type
     * @param <E> the element type
     * @param maxChannelSize maximum size of the channel
     * @param iterable the source iterable
     * @param channelProvider the provider used to create channels
     * @return an iterator of channels
     */
    public static <C extends Collection<E>, E> Iterator<C> batch(int maxChannelSize, Iterable<E> iterable, Provider<C> channelProvider) {
        dbc.precondition(iterable != null, "cannot demux a null iterable");
        return new BatchingIterator<C, E>(maxChannelSize, iterable.iterator(), channelProvider);
    }

    /**
     * Demultiplexes elements from the source iterable into an iterator of
     * channels.
     *
     * @param <E> the element type
     * @param maxChannelSize maximum size of the channel
     * @param iterable the source iterable
     * @return an iterator of channels
     */
    public static <E> Iterator<List<E>> batch(int maxChannelSize, Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot demux a null iterable");
        final Provider<List<E>> channelFactory = Compositions.compose(new Vary<List<E>, ArrayList<E>>(), new ArrayListFactory<E>());
        return new BatchingIterator<List<E>, E>(maxChannelSize, iterable.iterator(), channelFactory);
    }

    /**
     * Demultiplexes elements from the source array into an iterator of
     * channels.
     *
     * @param <C> the channel collection type
     * @param <E> the element type
     * @param maxChannelSize maximum size of the channel
     * @param array the source array
     * @param channelProvider the provider used to create channels
     * @return an iterator of channels
     */
    public static <C extends Collection<E>, E> Iterator<C> batch(int maxChannelSize, Provider<C> channelProvider, E... array) {
        return new BatchingIterator<C, E>(maxChannelSize, new ArrayIterator<E>(array), channelProvider);
    }

    /**
     * Demultiplexes elements from the source array into an iterator of
     * channels.
     *
     * @param <E> the element type
     * @param maxChannelSize maximum size of the channel
     * @param array the source array
     * @return an iterator of channels
     */
    public static <E> Iterator<List<E>> batch(int maxChannelSize, E... array) {
        final Provider<List<E>> channelFactory = Compositions.compose(new Vary<List<E>, ArrayList<E>>(), new ArrayListFactory<E>());
        return new BatchingIterator<List<E>, E>(maxChannelSize, new ArrayIterator<E>(array), channelFactory);
    }

    /**
     * Demultiplexes elements from the source array into an iterator of
     * channels.
     *
     * @param <C> the channel collection type
     * @param <E> the element type
     * @param channelSize size of the channel
     * @param iterator the source iterator
     * @param channelProvider the provider used to create channels
     * @return an iterator of channels
     */
    public static <C extends Collection<Maybe<E>>, E> Iterator<C> demuxLongest(int channelSize, Iterator<Maybe<E>> iterator, Provider<C> channelProvider) {
        return new PreciseDemultiplexingIterator<C, E>(channelSize, iterator, channelProvider);
    }

    /**
     * Demultiplexes elements from the source array into an iterator of
     * channels.
     *
     * @param <E> the element type
     * @param channelSize size of the channel
     * @param iterator the source iterator
     * @return an iterator of channels
     */
    public static <E> Iterator<List<Maybe<E>>> demuxLongest(int channelSize, Iterator<Maybe<E>> iterator) {
        final Provider<List<Maybe<E>>> channelFactory = Compositions.compose(new Vary<List<Maybe<E>>, ArrayList<Maybe<E>>>(), new ArrayListFactory<Maybe<E>>());
        return new PreciseDemultiplexingIterator<List<Maybe<E>>, E>(channelSize, iterator, channelFactory);
    }

    /**
     * Demultiplexes elements from the source array into an iterator of
     * channels.
     *
     * @param <C> the channel collection type
     * @param <E> the element type
     * @param channelSize size of the channel
     * @param iterable the source iterable
     * @param channelProvider the provider used to create channels
     * @return an iterator of channels
     */
    public static <C extends Collection<Maybe<E>>, E> Iterator<C> demuxLongest(int channelSize, Iterable<Maybe<E>> iterable, Provider<C> channelProvider) {
        dbc.precondition(iterable != null, "cannot demuxLongest a null iterable");
        return new PreciseDemultiplexingIterator<C, E>(channelSize, iterable.iterator(), channelProvider);
    }

    /**
     * Demultiplexes elements from the source array into an iterator of
     * channels.
     *
     * @param <E> the element type
     * @param channelSize size of the channel
     * @param iterable the source iterable
     * @return an iterator of channels
     */
    public static <E> Iterator<List<Maybe<E>>> demuxLongest(int channelSize, Iterable<Maybe<E>> iterable) {
        dbc.precondition(iterable != null, "cannot demuxLongest a null iterable");
        final Provider<List<Maybe<E>>> channelFactory = Compositions.compose(new Vary<List<Maybe<E>>, ArrayList<Maybe<E>>>(), new ArrayListFactory<Maybe<E>>());
        return new PreciseDemultiplexingIterator<List<Maybe<E>>, E>(channelSize, iterable.iterator(), channelFactory);
    }

    /**
     * Demultiplexes elements from the source array into an iterator of
     * channels.
     *
     * @param <C> the channel collection type
     * @param <E> the element type
     * @param channelSize size of the channel
     * @param channelProvider the provider used to create channels
     * @param array the source array
     * @return an iterator of channels
     */
    public static <C extends Collection<Maybe<E>>, E> Iterator<C> demuxLongest(int channelSize, Provider<C> channelProvider, Maybe<E>... array) {
        return demuxLongest(channelSize, new ArrayIterator<Maybe<E>>(array), channelProvider);
    }

    /**
     * Demultiplexes elements from the source array into an iterator of
     * channels.
     *
     * @param <E> the element type
     * @param channelSize size of the channel
     * @param array the source array
     * @return an iterator of channels
     */
    public static <E> Iterator<List<Maybe<E>>> demuxLongest(int channelSize, Maybe<E>... array) {
        return demuxLongest(channelSize, new ArrayIterator<Maybe<E>>(array));
    }

    /**
     * Multiplexes an iterator of iterators into an iterator.
     *
     * @param <E> the element type
     * @param <I> the iterator type
     * @param iterators the source iterator
     * @return an iterator yielding multiplexed elements
     */
    public static <E, I extends Iterator<E>> Iterator<E> roundrobin(Iterator<I> iterators) {
        return new RoundRobinIterator<E>(iterators);
    }

    /**
     * Multiplexes an iterator of iterators into an iterator.
     *
     * @param <E> the element type
     * @param <I> the iterator type
     * @param iterable the source iterable
     * @return an iterator yielding multiplexed elements
     */
    public static <E, I extends Iterator<E>> Iterator<E> roundrobin(Iterable<I> iterable) {
        dbc.precondition(iterable != null, "cannot roundrobin a null iterable");
        return new RoundRobinIterator<E>(iterable.iterator());
    }

    /**
     * Multiplexes an iterator of iterators into an iterator.
     *
     * @param <E> the element type
     * @param first the first source iterator
     * @param second the second source iterator
     * @return an iterator yielding multiplexed elements
     */
    public static <E> Iterator<E> roundrobin(Iterator<E> first, Iterator<E> second) {
        return new RoundRobinIterator<E>(Iterations.iterator(first, second));
    }

    /**
     * Multiplexes an iterator of iterators into an iterator.
     *
     * @param <E> the element type
     * @param first the first source iterator
     * @param second the second source iterator
     * @param third the third source iterator
     * @return an iterator yielding multiplexed elements
     */
    public static <E> Iterator<E> roundrobin(Iterator<E> first, Iterator<E> second, Iterator<E> third) {
        return new RoundRobinIterator<E>(Iterations.iterator(first, second, third));
    }
}
