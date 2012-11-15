package net.emaze.dysfunctional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.casts.Vary;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.ConstantProvider;
import net.emaze.dysfunctional.dispatching.delegates.IteratorPlucker;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.iterations.TransformingIterator;
import net.emaze.dysfunctional.multiplexing.BatchingIterator;
import net.emaze.dysfunctional.multiplexing.ChainIterator;
import net.emaze.dysfunctional.multiplexing.CyclicIterator;
import net.emaze.dysfunctional.multiplexing.RoundRobinIterator;
import net.emaze.dysfunctional.multiplexing.RoundRobinLongestIterator;
import net.emaze.dysfunctional.multiplexing.RoundRobinShortestIterator;
import net.emaze.dysfunctional.multiplexing.UnchainIterator;
import net.emaze.dysfunctional.multiplexing.UnchainWithExactChannelSizeIterator;
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
        return new ChainIterator<E>(new TransformingIterator<Iterator<E>, I>(iterables, new IteratorPlucker<E, I>()));
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
        final Iterator<I> iterator = iterables.iterator();
        return new ChainIterator<E>(new TransformingIterator<Iterator<E>, I>(iterator, new IteratorPlucker<E, I>()));
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
        final Iterator<I> iterator = ArrayIterator.of(first, second);
        return new ChainIterator<E>(new TransformingIterator<Iterator<E>, I>(iterator, new IteratorPlucker<E, I>()));
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
        final Iterator<I> iterator = ArrayIterator.of(first, second, third);
        return new ChainIterator<E>(new TransformingIterator<Iterator<E>, I>(iterator, new IteratorPlucker<E, I>()));
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
        return new ChainIterator<E>(ArrayIterator.of(first, second));
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
        return new ChainIterator<E>(ArrayIterator.of(first, second, third));
    }

    /**
     * Demultiplexes elements from the source iterator into an iterator of
     * channels.
     *
     *
     * @param <C> the channel collection type
     * @param <E> the element type
     * @param batchSize maximum size of the channel
     * @param iterator the source iterator
     * @param channelProvider a provider used to create channels
     * @return an iterator of channels
     */
    public static <C extends Collection<E>, E> Iterator<C> batch(int batchSize, Iterator<E> iterator, Provider<C> channelProvider) {
        return new BatchingIterator<C, E>(batchSize, iterator, channelProvider);
    }

    /**
     * Demultiplexes elements from the source iterator into an iterator of
     * channels.
     *
     * @param <E> the element type
     * @param batchSize maximum size of the channel
     * @param iterator the source iterator
     * @return an iterator of channels
     */
    public static <E> Iterator<List<E>> batch(int batchSize, Iterator<E> iterator) {
        final Provider<List<E>> channelFactory = Compositions.compose(new Vary<List<E>, ArrayList<E>>(), new ArrayListFactory<E>());
        return new BatchingIterator<List<E>, E>(batchSize, iterator, channelFactory);
    }

    /**
     * Demultiplexes elements from the source iterable into an iterator of
     * channels.
     *
     * @param <C> the channel collection type
     * @param <E> the element type
     * @param batchSize maximum size of the channel
     * @param iterable the source iterable
     * @param channelProvider the provider used to create channels
     * @return an iterator of channels
     */
    public static <C extends Collection<E>, E> Iterator<C> batch(int batchSize, Iterable<E> iterable, Provider<C> channelProvider) {
        dbc.precondition(iterable != null, "cannot batch a null iterable");
        return new BatchingIterator<C, E>(batchSize, iterable.iterator(), channelProvider);
    }

    /**
     * Demultiplexes elements from the source iterable into an iterator of
     * channels.
     *
     * @param <E> the element type
     * @param batchSize maximum size of the channel
     * @param iterable the source iterable
     * @return an iterator of channels
     */
    public static <E> Iterator<List<E>> batch(int batchSize, Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot batch a null iterable");
        final Provider<List<E>> channelFactory = Compositions.compose(new Vary<List<E>, ArrayList<E>>(), new ArrayListFactory<E>());
        return new BatchingIterator<List<E>, E>(batchSize, iterable.iterator(), channelFactory);
    }

    /**
     * Demultiplexes elements from the source array into an iterator of
     * channels.
     *
     * @param <C> the channel collection type
     * @param <E> the element type
     * @param batchSize maximum size of the channel
     * @param array the source array
     * @param channelProvider the provider used to create channels
     * @return an iterator of channels
     */
    public static <C extends Collection<E>, E> Iterator<C> batch(int batchSize, E[] array, Provider<C> channelProvider) {
        return new BatchingIterator<C, E>(batchSize, new ArrayIterator<E>(array), channelProvider);
    }

    /**
     * Demultiplexes elements from the source array into an iterator of
     * channels.
     *
     * @param <E> the element type
     * @param batchSize maximum size of the channel
     * @param array the source array
     * @return an iterator of channels
     */
    public static <E> Iterator<List<E>> batch(int batchSize, E[] array) {
        final Provider<List<E>> channelFactory = Compositions.compose(new Vary<List<E>, ArrayList<E>>(), new ArrayListFactory<E>());
        return new BatchingIterator<List<E>, E>(batchSize, new ArrayIterator<E>(array), channelFactory);
    }

    /**
     * Multiplexes an iterator of iterators into an iterator. Iteration stops
     * when every element from every source has been consumed.
     * <code>
     * roundRobin([1,2], [3], [4,5]) -> [1,3,4,2,5]
     * </code>
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
     * Multiplexes an iterator of iterators into an iterator. Iteration stops
     * when every element from every source has been consumed.
     * <code>
     * roundRobin([1,2], [3], [4,5]) -> [1,3,4,2,5]
     * </code>
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
     * Multiplexes an iterator of iterators into an iterator. Iteration stops
     * when every element from every source has been consumed.
     * <code>
     * roundRobin([1,2], [3]) -> [1,3,2]
     * </code>
     *
     * @param <E> the element type
     * @param first the first source iterator
     * @param second the second source iterator
     * @return an iterator yielding multiplexed elements
     */
    public static <E> Iterator<E> roundrobin(Iterator<E> first, Iterator<E> second) {
        return new RoundRobinIterator<E>(ArrayIterator.of(first, second));
    }

    /**
     * Multiplexes an iterator of iterators into an iterator. Iteration stops
     * when every element from every source has been consumed.
     * <code>
     * roundRobin([1,2], [3], [4,5,6]) -> [1,3,4,2,5,6]
     * </code>
     *
     * @param <E> the element type
     * @param first the first source iterator
     * @param second the second source iterator
     * @param third the third source iterator
     * @return an iterator yielding multiplexed elements
     */
    public static <E> Iterator<E> roundrobin(Iterator<E> first, Iterator<E> second, Iterator<E> third) {
        return new RoundRobinIterator<E>(ArrayIterator.of(first, second, third));
    }

    /**
     * Multiplexes an iterator of iterators into a single iterator. The
     * iteration stops whenever any of the source iterators is empty causing the
     * same number of elements to be consumed from every iterator.
     * <code>
     * roundrobinShortest([1,2,3], [4,5]) -> [1,4,2,5]
     * </code>
     *
     * @param <E> the element type
     * @param <I> the iterator type
     * @param iterators the iterator of iterators to be multiplexed
     * @return an iterator containing multiplexed elements
     */
    public static <E, I extends Iterator<E>> Iterator<E> roundrobinShortest(Iterator<I> iterators) {
        return new RoundRobinShortestIterator<E>(iterators);
    }

    /**
     * Multiplexes an iterable of iterators into a single iterator. The
     * iteration stops whenever any of the source iterators is empty causing the
     * same number of elements to be consumed from every iterator. E.g:
     * <code>
     * roundrobinShortest([1,2,3], [4,5]) -> [1,4,2,5]
     * </code>
     *
     * @param <E> the element type
     * @param <I> the source iterator type
     * @param iterable the source iterable
     * @return an iterator containing multiplexed elements
     */
    public static <E, I extends Iterator<E>> Iterator<E> roundrobinShortest(Iterable<I> iterable) {
        dbc.precondition(iterable != null, "cannot roundrobinShortest a null iterable");
        final Iterator<I> iterator = iterable.iterator();
        return new RoundRobinShortestIterator<E>(iterator);
    }

    /**
     * Multiplexes two iterators into a single iterator. The iteration stops
     * whenever any of the source iterators is empty causing the same number of
     * elements to be consumed from every iterator. E.g:
     * <code>
     * roundrobinShortest([1,2], [3]) -> [1,3]
     * </code>
     *
     * @param <E> the element type
     * @param first the first source iterator
     * @param second the second source iterator
     * @return an iterator containing multiplexed elements
     */
    public static <E> Iterator<E> roundrobinShortest(Iterator<E> first, Iterator<E> second) {
        final Iterator<Iterator<E>> iterator = ArrayIterator.of(first, second);
        return new RoundRobinShortestIterator<E>(iterator);
    }

    /**
     * Multiplexes three iterators into a single iterator. The iteration stops
     * whenever any of the source iterators is empty causing the same number of
     * elements to be consumed from every iterator. E.g:
     * <code>
     * roundrobinShortest([1,2], [3], [4]) -> [1, 3, 4]
     * </code>
     *
     * @param <E> the element type
     * @param first the first source iterator
     * @param second the second source iterator
     * @param third the third source iterator
     * @return an iterator containing multiplexed elements
     */
    public static <E> Iterator<E> roundrobinShortest(Iterator<E> first, Iterator<E> second, Iterator<E> third) {
        final ArrayIterator<Iterator<E>> iterator = ArrayIterator.of(first, second, third);
        return new RoundRobinShortestIterator<E>(iterator);
    }

    /**
     * Multiplexes an iterator of iterators into a single iterator. The
     * iteration stops when every source iterator is empty.E.g:
     * <code>
     * roundrobinLongest([1,2], [4]) -> [just 1, just 4, just 2, nothing]
     * </code>
     *
     * @param <E> the element type
     * @param <I> the iterator type
     * @param iterators the source iterator
     * @return an iterator containing multiplexed elements
     */
    public static <E, I extends Iterator<E>> Iterator<Maybe<E>> roundrobinLongest(Iterator<I> iterators) {
        return new RoundRobinLongestIterator<E>(iterators);
    }

    /**
     * Multiplexes an iterable of iterators into a single iterator. The
     * iteration stops when every source iterator is empty.E.g:
     * <code>
     * roundrobinLongest([1,2], [4]) -> [just 1, just 4, just 2, nothing]
     * </code>
     *
     * @param <E> the element type
     * @param <I> the iterator type
     * @param iterable the source iterable
     * @return an iterator containing multiplexed elements
     */
    public static <E, I extends Iterator<E>> Iterator<Maybe<E>> roundrobinLongest(Iterable<I> iterable) {
        dbc.precondition(iterable != null, "cannot roundrobinLongest a null iterable");
        final Iterator<I> iterator = iterable.iterator();
        return new RoundRobinLongestIterator<E>(iterator);
    }

    /**
     * Multiplexes two iterators into a single iterator. The iteration stops
     * when every source iterator is empty.E.g:
     * <code>
     * roundrobinLongest([1,2], [4]) -> [just 1, just 4, just 2, nothing]
     * </code>
     *
     * @param <E> the element type
     * @param first the first source iterator
     * @param second the second source iterator
     * @return an iterator containing multiplexed elements
     */
    public static <E> Iterator<Maybe<E>> roundrobinLongest(Iterator<E> first, Iterator<E> second) {
        final Iterator<Iterator<E>> iterator = ArrayIterator.of(first, second);
        return new RoundRobinLongestIterator<E>(iterator);
    }

    /**
     * Multiplexes three iterators into a single iterator. The iteration stops
     * when every source iterator is empty. E.g:
     * <code>
     * roundrobinLongest([1,2], [4], [5]) -> [just 1, just 4, just 5, just 2, nothing, nothing]
     * </code>
     *
     * @param <E> the element type
     * @param first the first source iterator
     * @param second the second source iterator
     * @param third the third source iterator
     * @return an iterator containing multiplexed elements
     */
    public static <E> Iterator<Maybe<E>> roundrobinLongest(Iterator<E> first, Iterator<E> second, Iterator<E> third) {
        final Iterator<Iterator<E>> iterator = ArrayIterator.of(first, second, third);
        return new RoundRobinLongestIterator<E>(iterator);
    }

    /**
     * Creates an infinite iterator that issues elements from the parameter
     * cyclicly.
     *
     * @param <E> the element type
     * @param iterator the iterator to cycle
     * @return an iterator that cyclicly returns the elements from the argument
     */
    public static <E> Iterator<E> cycle(Iterator<E> iterator) {
        return new CyclicIterator<E>(iterator);
    }

    /**
     * Creates an infinite iterator that issues elements from the parameter
     * cyclicly.
     *
     * @param <E> the element type
     * @param iterable the iterable to cycle
     * @return an iterator that cyclicly returns the elements from the argument
     */
    public static <E> Iterator<E> cycle(Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot cycle a null iterable");
        return new CyclicIterator<E>(iterable.iterator());
    }

    /**
     * Creates an infinite iterator that issues elements from the parameter
     * cyclicly.
     *
     * @param <E> the element type
     * @param first the first element to consider while cycling
     * @param second the second element to consider while cycling
     * @return an iterator that cyclicly returns the elements from the arguments
     */
    public static <E> Iterator<E> cycle(E first, E second) {
        return new CyclicIterator<E>(Iterations.iterator(first, second));
    }

    /**
     * Creates an infinite iterator that issues elements from the parameter
     * cyclicly.
     *
     * @param <E> the element type
     * @param first the first element to consider while cycling
     * @param second the second element to consider while cycling
     * @param third the third element to consider while cycling
     * @return an iterator that cyclicly returns the elements from the arguments
     */
    public static <E> Iterator<E> cycle(E first, E second, E third) {
        return new CyclicIterator<E>(Iterations.iterator(first, second, third));
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
    public static <C extends Collection<E>, E> Iterator<C> unchain(int channelSize, Iterator<E> iterator, Provider<C> channelProvider) {
        final ConstantProvider<Maybe<Integer>> channelsSizesProvider = new ConstantProvider<Maybe<Integer>>(Maybe.just(channelSize));
        return new UnchainIterator<C, E>(channelsSizesProvider, iterator, channelProvider);
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
    public static <E> Iterator<List<E>> unchain(int channelSize, Iterator<E> iterator) {
        final ConstantProvider<Maybe<Integer>> channelsSizesProvider = new ConstantProvider<Maybe<Integer>>(Maybe.just(channelSize));
        final Provider<List<E>> channelFactory = Compositions.compose(new Vary<List<E>, ArrayList<E>>(), new ArrayListFactory<E>());
        return new UnchainIterator<List<E>, E>(channelsSizesProvider, iterator, channelFactory);
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
    public static <C extends Collection<E>, E> Iterator<C> unchain(int channelSize, Iterable<E> iterable, Provider<C> channelProvider) {
        dbc.precondition(iterable != null, "cannot unchain a null iterable");
        final ConstantProvider<Maybe<Integer>> channelsSizesProvider = new ConstantProvider<Maybe<Integer>>(Maybe.just(channelSize));
        final Iterator<E> iterator = iterable.iterator();
        return new UnchainIterator<C, E>(channelsSizesProvider, iterator, channelProvider);
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
    public static <E> Iterator<List<E>> unchain(int channelSize, Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot unchain a null iterable");
        final ConstantProvider<Maybe<Integer>> channelsSizesProvider = new ConstantProvider<Maybe<Integer>>(Maybe.just(channelSize));
        final Provider<List<E>> channelFactory = Compositions.compose(new Vary<List<E>, ArrayList<E>>(), new ArrayListFactory<E>());
        final Iterator<E> iterator = iterable.iterator();
        return new UnchainIterator<List<E>, E>(channelsSizesProvider, iterator, channelFactory);
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
    public static <C extends Collection<E>, E> Iterator<C> unchain(int channelSize, Provider<C> channelProvider, E... array) {
        final ConstantProvider<Maybe<Integer>> channelsSizesProvider = new ConstantProvider<Maybe<Integer>>(Maybe.just(channelSize));
        final ArrayIterator<E> iterator = new ArrayIterator<E>(array);
        return new UnchainIterator<C, E>(channelsSizesProvider, iterator, channelProvider);
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
    public static <E> Iterator<List<E>> unchain(int channelSize, E... array) {
        final ConstantProvider<Maybe<Integer>> channelsSizesProvider = new ConstantProvider<Maybe<Integer>>(Maybe.just(channelSize));
        final Provider<List<E>> channelFactory = Compositions.compose(new Vary<List<E>, ArrayList<E>>(), new ArrayListFactory<E>());
        final ArrayIterator<E> iterator = new ArrayIterator<E>(array);
        return new UnchainIterator<List<E>, E>(channelsSizesProvider, iterator, channelFactory);
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
    public static <C extends Collection<Maybe<E>>, E> Iterator<C> unchainWithExactChannelSize(int channelSize, Iterator<E> iterator, Provider<C> channelProvider) {
        final ConstantProvider<Maybe<Integer>> channelsSizesProvider = new ConstantProvider<Maybe<Integer>>(Maybe.just(channelSize));
        return new UnchainWithExactChannelSizeIterator<C, E>(channelsSizesProvider, iterator, channelProvider);
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
    public static <E> Iterator<List<Maybe<E>>> unchainWithExactChannelSize(int channelSize, Iterator<E> iterator) {
        final ConstantProvider<Maybe<Integer>> channelsSizesProvider = new ConstantProvider<Maybe<Integer>>(Maybe.just(channelSize));
        final Provider<List<Maybe<E>>> channelFactory = Compositions.compose(new Vary<List<Maybe<E>>, ArrayList<Maybe<E>>>(), new ArrayListFactory<Maybe<E>>());
        return new UnchainWithExactChannelSizeIterator<List<Maybe<E>>, E>(channelsSizesProvider, iterator, channelFactory);
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
    public static <C extends Collection<Maybe<E>>, E> Iterator<C> unchainWithExactChannelSize(int channelSize, Iterable<E> iterable, Provider<C> channelProvider) {
        dbc.precondition(iterable != null, "cannot unchainWithExactChannelSize a null iterable");
        final ConstantProvider<Maybe<Integer>> channelsSizesProvider = new ConstantProvider<Maybe<Integer>>(Maybe.just(channelSize));
        return new UnchainWithExactChannelSizeIterator<C, E>(channelsSizesProvider, iterable.iterator(), channelProvider);
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
    public static <E> Iterator<List<Maybe<E>>> unchainWithExactChannelSize(int channelSize, Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot unchainWithExactChannelSize a null iterable");
        final ConstantProvider<Maybe<Integer>> channelsSizesProvider = new ConstantProvider<Maybe<Integer>>(Maybe.just(channelSize));
        final Provider<List<Maybe<E>>> channelFactory = Compositions.compose(new Vary<List<Maybe<E>>, ArrayList<Maybe<E>>>(), new ArrayListFactory<Maybe<E>>());
        return new UnchainWithExactChannelSizeIterator<List<Maybe<E>>, E>(channelsSizesProvider, iterable.iterator(), channelFactory);
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
    public static <C extends Collection<Maybe<E>>, E> Iterator<C> unchainWithExactChannelSize(int channelSize, Provider<C> channelProvider, E... array) {
        final ConstantProvider<Maybe<Integer>> channelsSizesProvider = new ConstantProvider<Maybe<Integer>>(Maybe.just(channelSize));
        return new UnchainWithExactChannelSizeIterator<C, E>(channelsSizesProvider, new ArrayIterator<E>(array), channelProvider);
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
    public static <E> Iterator<List<Maybe<E>>> unchainWithExactChannelSize(int channelSize, E... array) {
        final ConstantProvider<Maybe<Integer>> channelsSizesProvider = new ConstantProvider<Maybe<Integer>>(Maybe.just(channelSize));
        final Provider<List<Maybe<E>>> channelFactory = Compositions.compose(new Vary<List<Maybe<E>>, ArrayList<Maybe<E>>>(), new ArrayListFactory<Maybe<E>>());
        return new UnchainWithExactChannelSizeIterator<List<Maybe<E>>, E>(channelsSizesProvider, new ArrayIterator<E>(array), channelFactory);
    }
}
