package net.emaze.dysfunctional;

import net.emaze.dysfunctional.casts.Vary;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.ConstantSupplier;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.iterations.TransformingIterator;
import net.emaze.dysfunctional.multiplexing.*;
import java.util.*;
import java.util.function.Supplier;

public abstract class Multiplexing {

    /**
     * Flattens an iterator of iterables of E to an iterator of E. E.g:
     * <code>
     * flatten([1,2], [3,4]) -> [1,2,3,4]
     * </code>
     *
     * @param <I> the iterable type
     * @param <E> the iterable element type
     * @param iterables the iterator of iterables to be flattened
     * @return the flattened iterator
     */
    public static <I extends Iterable<E>, E> Iterator<E> flatten(Iterator<I> iterables) {
        return new ChainIterator<>(new TransformingIterator<>(iterables, Iterable::iterator));
    }

    /**
     * Flattens an iterable of iterables of E to an iterator of E. E.g:
     * <code>
     * flatten([1,2], [3,4]) -> [1,2,3,4]
     * </code>
     *
     * @param <I> the iterable type
     * @param <E> the iterable element type
     * @param iterables the iterable of iterables to be flattened
     * @return the flattened iterator
     */
    public static <I extends Iterable<E>, E> Iterator<E> flatten(Iterable<I> iterables) {
        dbc.precondition(iterables != null, "cannot flatten a null iterable");
        final Iterator<I> iterator = iterables.iterator();
        return new ChainIterator<>(new TransformingIterator<>(iterator, Iterable::iterator));
    }

    /**
     * Flattens passed iterables of E to an iterator of E. E.g:
     * <code>
     * flatten([1,2], [3,4]) -> [1,2,3,4]
     * </code>
     *
     * @param <E> the iterable element type
     * @param <I> the iterable type
     * @param iterables the array of iterables to be flattened
     * @return the flattened iterator
     */
    public static <E, I extends Iterable<E>> Iterator<E> flatten(I... iterables) {
        final Iterator<I> iterator = ArrayIterator.of(iterables);
        return new ChainIterator<>(new TransformingIterator<>(iterator, Iterable::iterator));
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
        return new ChainIterator<>(iterators);
    }

    /**
     * Flattens an iterable of iterators of E to an iterator of E. E.g:
     * <code>
     * chain([1,2], [3,4]) -> [1,2,3,4]
     * </code>
     *
     * @param <E> the iterator element type
     * @param <I> the iterator type
     * @param iterators the source iterable
     * @return the flattened iterator
     */
    public static <E, I extends Iterator<E>> Iterator<E> chain(Iterable<I> iterators) {
        dbc.precondition(iterators != null, "cannot chain a null iterable");
        return new ChainIterator<>(iterators.iterator());
    }

    /**
     * Flattens passed iterators of E to an iterator of E. E.g:
     * <code>
     * chain([1,2], [3,4]) -> [1,2,3,4]
     * </code>
     *
     * @param <E> the iterator element type
     * @param <I> the iterator type
     * @param iterators the array of iterators to be flattened
     * @return the flattened iterator
     */
    public static <E, I extends Iterator<E>> Iterator<E> chain(I... iterators) {
        return new ChainIterator<>(ArrayIterator.of(iterators));
    }


    /**
     * Demultiplexes elements from the source iterator into an iterator of channels.
     * <code>
     * unchain(2, [1,2,3,4,5]) -> [[1,2],[3,4],[5]]
     * </code>
     *
     * @param <C> the channel collection type
     * @param <E> the element type
     * @param channelSize maximum size of the channel
     * @param iterator the source iterator
     * @param channelProvider a supplier used to create channels
     * @return an iterator of channels
     */
    public static <C extends Collection<E>, E> Iterator<C> unchain(int channelSize, Iterator<E> iterator, Supplier<C> channelProvider) {
        return new UnchainIterator<C, E>(channelSize, iterator, channelProvider);
    }

    /**
     * Demultiplexes elements from the source iterator into an iterator of channels.
     * <code>
     * unchain(2, [1,2,3,4,5]) -> [[1,2],[3,4],[5]]
     * </code>
     *
     * @param <E> the element type
     * @param channelSize maximum size of the channel
     * @param iterator the source iterator
     * @return an iterator of channels
     */
    public static <E> Iterator<List<E>> unchain(int channelSize, Iterator<E> iterator) {
        final Supplier<List<E>> channelFactory = Compositions.compose(new Vary<ArrayList<E>, List<E>>(), new ArrayListFactory<E>());
        return new UnchainIterator<List<E>, E>(channelSize, iterator, channelFactory);
    }

    /**
     * Demultiplexes elements from the source iterable into an iterator of channels.
     * <code>
     * unchain(2, [1,2,3,4,5]) -> [[1,2],[3,4],[5]]
     * </code>
     *
     * @param <C> the channel collection type
     * @param <E> the element type
     * @param channelSize maximum size of the channel
     * @param iterable the source iterable
     * @param channelProvider the supplier used to create channels
     * @return an iterator of channels
     */
    public static <C extends Collection<E>, E> Iterator<C> unchain(int channelSize, Iterable<E> iterable, Supplier<C> channelProvider) {
        dbc.precondition(iterable != null, "cannot unchain a null iterable");
        return new UnchainIterator<C, E>(channelSize, iterable.iterator(), channelProvider);
    }

    /**
     * Demultiplexes elements from the source iterable into an iterator of channels.
     * <code>
     * unchain(2, [1,2,3,4,5]) -> [[1,2],[3,4],[5]]
     * </code>
     *
     * @param <E> the element type
     * @param channelSize maximum size of the channel
     * @param iterable the source iterable
     * @return an iterator of channels
     */
    public static <E> Iterator<List<E>> unchain(int channelSize, Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot unchain a null iterable");
        final Supplier<List<E>> channelFactory = Compositions.compose(new Vary<ArrayList<E>, List<E>>(), new ArrayListFactory<E>());
        return new UnchainIterator<List<E>, E>(channelSize, iterable.iterator(), channelFactory);
    }

    /**
     * Demultiplexes elements from the source array into an iterator of channels.
     * <code>
     * unchain(2, [1,2,3,4,5]) -> [[1,2],[3,4],[5]]
     * </code>
     *
     * @param <C> the channel collection type
     * @param <E> the element type
     * @param channelSize maximum size of the channel
     * @param array the source array
     * @param channelProvider the supplier used to create channels
     * @return an iterator of channels
     */
    public static <C extends Collection<E>, E> Iterator<C> unchain(int channelSize, Supplier<C> channelProvider, E... array) {
        return new UnchainIterator<C, E>(channelSize, new ArrayIterator<E>(array), channelProvider);
    }

    /**
     * Demultiplexes elements from the source array into an iterator of channels.
     * <code>
     * unchain(2, [1,2,3,4,5]) -> [[1,2],[3,4],[5]]
     * </code>
     *
     * @param <E> the element type
     * @param channelSize maximum size of the channel
     * @param array the source array
     * @return an iterator of channels
     */
    public static <E> Iterator<List<E>> unchain(int channelSize, E... array) {
        final Supplier<List<E>> channelFactory = Compositions.compose(new Vary<ArrayList<E>, List<E>>(), new ArrayListFactory<E>());
        return new UnchainIterator<List<E>, E>(channelSize, new ArrayIterator<E>(array), channelFactory);
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
        return new RoundrobinIterator<E>(iterators);
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
     * @param iterators the source iterable
     * @return an iterator yielding multiplexed elements
     */
    public static <E, I extends Iterator<E>> Iterator<E> roundrobin(Iterable<I> iterators) {
        dbc.precondition(iterators != null, "cannot roundrobin a null iterable");
        return new RoundrobinIterator<E>(iterators.iterator());
    }

    /**
     * Multiplexes an iterator of iterators into an iterator. Iteration stops
     * when every element from every source has been consumed.
     * <code>
     * roundRobin([1,2], [3]) -> [1,3,2]
     * </code>
     *
     * @param <E> the element type
     * @param iterators the source iterators
     * @return an iterator yielding multiplexed elements
     */
    public static <E> Iterator<E> roundrobin(Iterator<E>... iterators) {
        return new RoundrobinIterator<E>(ArrayIterator.of(iterators));
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
        return new RoundrobinShortestIterator<E>(iterators);
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
     * @param iterators the source iterable
     * @return an iterator containing multiplexed elements
     */
    public static <E, I extends Iterator<E>> Iterator<E> roundrobinShortest(Iterable<I> iterators) {
        dbc.precondition(iterators != null, "cannot roundrobinShortest a null iterable");
        final Iterator<I> iterator = iterators.iterator();
        return new RoundrobinShortestIterator<E>(iterator);
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
     * @param iterators the source iterators
     * @return an iterator containing multiplexed elements
     */
    public static <E> Iterator<E> roundrobinShortest(Iterator<E>... iterators) {
        final Iterator<Iterator<E>> iterator = ArrayIterator.of(iterators);
        return new RoundrobinShortestIterator<E>(iterator);
    }


    /**
     * Multiplexes an iterator of iterators into a single iterator. The
     * iteration stops when every source iterator is empty.E.g:
     * <code>
     * roundrobinLongest([1,2], [4]) -> [of 1, of 4, of 2, empty]
     * </code>
     *
     * @param <E> the element type
     * @param <I> the iterator type
     * @param iterators the source iterator
     * @return an iterator containing multiplexed elements
     */
    public static <E, I extends Iterator<E>> Iterator<Optional<E>> roundrobinLongest(Iterator<I> iterators) {
        return new RoundrobinLongestIterator<E>(iterators);
    }

    /**
     * Multiplexes an iterable of iterators into a single iterator. The
     * iteration stops when every source iterator is empty.E.g:
     * <code>
     * roundrobinLongest([1,2], [4]) -> [of 1, of 4, of 2, empty]
     * </code>
     *
     * @param <E> the element type
     * @param <I> the iterator type
     * @param iterators the source iterable
     * @return an iterator containing multiplexed elements
     */
    public static <E, I extends Iterator<E>> Iterator<Optional<E>> roundrobinLongest(Iterable<I> iterators) {
        dbc.precondition(iterators != null, "cannot roundrobinLongest a null iterable");
        final Iterator<I> iterator = iterators.iterator();
        return new RoundrobinLongestIterator<E>(iterator);
    }

    /**
     * Multiplexes two iterators into a single iterator. The iteration stops
     * when every source iterator is empty.E.g:
     * <code>
     * roundrobinLongest([1,2], [4]) -> [of 1, of 4, of 2, empty]
     * </code>
     *
     * @param <E> the element type
     * @param iterators the source iterators
     * @return an iterator containing multiplexed elements
     */
    public static <E> Iterator<Optional<E>> roundrobinLongest(Iterator<E>... iterators) {
        final Iterator<Iterator<E>> iterator = ArrayIterator.of(iterators);
        return new RoundrobinLongestIterator<E>(iterator);
    }

    /**
     * Creates an infinite iterator that issues elements from the parameter cyclicly.
     *
     * @param <E> the element type
     * @param iterator the iterator to cycle
     * @return an iterator that cyclicly returns the elements from the argument
     */
    public static <E> Iterator<E> cycle(Iterator<E> iterator) {
        return new CyclicIterator<E>(iterator);
    }

    /**
     * Creates an infinite iterator that issues elements from the parameter cyclicly.
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
     * Creates an infinite iterator that issues elements from the parameter cyclicly.
     *
     * @param <E> the element type
     * @param array the array to cycle
     * @return an iterator that cyclicly returns the elements from the arguments
     */
    public static <E> Iterator<E> cycle(E... array) {
        return new CyclicIterator<E>(ArrayIterator.of(array));
    }

    /**
     * Demultiplexes elements from the source iterable into an iterator of channels.
     * All returned channels will have the specified size, any remaining elements will be discarded.
     * <code>
     * unchainShortest(2, [1,2,3,4,5]) -> [[1,2],[3,4]]
     * </code>
     *
     * @param <C> the channel collection type
     * @param <E> the element type
     * @param channelSize size of the channel
     * @param iterator the source iterator
     * @param channelProvider a supplier used to create channels
     * @return an iterator of channels
     */
    public static <C extends Collection<E>, E> Iterator<C> unchainShortest(int channelSize, Iterator<E> iterator, Supplier<C> channelProvider) {
        final ConstantSupplier<Optional<Integer>> channelsSizesProvider = new ConstantSupplier<Optional<Integer>>(Optional.of(channelSize));
        return new UnchainShortestIterator<C, E>(channelsSizesProvider, iterator, channelProvider);
    }

    /**
     * Demultiplexes elements from the source iterable into an iterator of channels.
     * All returned channels will have the specified size, any remaining elements will be discarded.
     * <code>
     * unchainShortest(2, [1,2,3,4,5]) -> [[1,2],[3,4]]
     * </code>
     *
     * @param <E> the element type
     * @param channelSize size of the channel
     * @param iterator the source iterator
     * @return an iterator of channels
     */
    public static <E> Iterator<List<E>> unchainShortest(int channelSize, Iterator<E> iterator) {
        final ConstantSupplier<Optional<Integer>> channelsSizesProvider = new ConstantSupplier<Optional<Integer>>(Optional.of(channelSize));
        final Supplier<List<E>> channelFactory = Compositions.compose(new Vary<ArrayList<E>, List<E>>(), new ArrayListFactory<E>());
        return new UnchainShortestIterator<List<E>, E>(channelsSizesProvider, iterator, channelFactory);
    }

    /**
     * Demultiplexes elements from the source iterable into an iterator of channels.
     * All returned channels will have the specified size, any remaining elements will be discarded.
     * <code>
     * unchainShortest(2, [1,2,3,4,5]) -> [[1,2],[3,4]]
     * </code>
     *
     * @param <C> the channel collection type
     * @param <E> the element type
     * @param channelSize size of the channel
     * @param iterable the source iterable
     * @param channelProvider the supplier used to create channels
     * @return an iterator of channels
     */
    public static <C extends Collection<E>, E> Iterator<C> unchainShortest(int channelSize, Iterable<E> iterable, Supplier<C> channelProvider) {
        dbc.precondition(iterable != null, "cannot unchainShortest a null iterable");
        final ConstantSupplier<Optional<Integer>> channelsSizesProvider = new ConstantSupplier<Optional<Integer>>(Optional.of(channelSize));
        final Iterator<E> iterator = iterable.iterator();
        return new UnchainShortestIterator<C, E>(channelsSizesProvider, iterator, channelProvider);
    }

    /**
     * Demultiplexes elements from the source iterable into an iterator of channels.
     * All returned channels will have the specified size, any remaining elements will be discarded.
     * <code>
     * unchainShortest(2, [1,2,3,4,5]) -> [[1,2],[3,4]]
     * </code>
     *
     * @param <E> the element type
     * @param channelSize size of the channel
     * @param iterable the source iterable
     * @return an iterator of channels
     */
    public static <E> Iterator<List<E>> unchainShortest(int channelSize, Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot unchainShortest a null iterable");
        final ConstantSupplier<Optional<Integer>> channelsSizesProvider = new ConstantSupplier<Optional<Integer>>(Optional.of(channelSize));
        final Supplier<List<E>> channelFactory = Compositions.compose(new Vary<ArrayList<E>, List<E>>(), new ArrayListFactory<E>());
        final Iterator<E> iterator = iterable.iterator();
        return new UnchainShortestIterator<List<E>, E>(channelsSizesProvider, iterator, channelFactory);
    }

    /**
     * Demultiplexes elements from the source iterable into an iterator of channels.
     * All returned channels will have the specified size, any remaining elements will be discarded.
     * <code>
     * unchainShortest(2, [1,2,3,4,5]) -> [[1,2],[3,4]]
     * </code>
     *
     * @param <C> the channel collection type
     * @param <E> the element type
     * @param channelSize size of the channel
     * @param array the source array
     * @param channelProvider the supplier used to create channels
     * @return an iterator of channels
     */
    public static <C extends Collection<E>, E> Iterator<C> unchainShortest(int channelSize, Supplier<C> channelProvider, E... array) {
        final ConstantSupplier<Optional<Integer>> channelsSizesProvider = new ConstantSupplier<Optional<Integer>>(Optional.of(channelSize));
        final ArrayIterator<E> iterator = new ArrayIterator<E>(array);
        return new UnchainShortestIterator<C, E>(channelsSizesProvider, iterator, channelProvider);
    }

    /**
     * Demultiplexes elements from the source iterable into an iterator of channels.
     * All returned channels will have the specified size, any remaining elements will be discarded.
     * <code>
     * unchainShortest(2, [1,2,3,4,5]) -> [[1,2],[3,4]]
     * </code>
     *
     * @param <E> the element type
     * @param channelSize size of the channel
     * @param array the source array
     * @return an iterator of channels
     */
    public static <E> Iterator<List<E>> unchainShortest(int channelSize, E... array) {
        final ConstantSupplier<Optional<Integer>> channelsSizesProvider = new ConstantSupplier<Optional<Integer>>(Optional.of(channelSize));
        final Supplier<List<E>> channelFactory = Compositions.compose(new Vary<ArrayList<E>, List<E>>(), new ArrayListFactory<E>());
        final ArrayIterator<E> iterator = new ArrayIterator<E>(array);
        return new UnchainShortestIterator<List<E>, E>(channelsSizesProvider, iterator, channelFactory);
    }

    /**
     * Demultiplexes elements from the source array into an iterator of channels.
     * All returned channels will have the specified size, eventually the last channel could have the last elements empty.
     * <code>
     * unchainLongest(2, [1,2,3,4,5]) -> [[just 1, just 2],[just 3, just 4],[just 5, nothing]]
     * </code>
     *
     * @param <C> the channel collection type
     * @param <E> the element type
     * @param channelSize size of the channel
     * @param iterator the source iterator
     * @param channelProvider the supplier used to create channels
     * @return an iterator of channels
     */
    public static <C extends Collection<Optional<E>>, E> Iterator<C> unchainLongest(int channelSize, Iterator<E> iterator, Supplier<C> channelProvider) {
        final ConstantSupplier<Optional<Integer>> channelsSizesProvider = new ConstantSupplier<Optional<Integer>>(Optional.of(channelSize));
        return new UnchainLongestIterator<C, E>(channelsSizesProvider, iterator, channelProvider);
    }

    /**
     * Demultiplexes elements from the source array into an iterator of channels.
     * All returned channels will have the specified size, eventually the last channel could have the last elements empty.
     * <code>
     * unchainLongest(2, [1,2,3,4,5]) -> [[just 1, just 2],[just 3, just 4],[just 5, nothing]]
     * </code>
     *
     * @param <E> the element type
     * @param channelSize size of the channel
     * @param iterator the source iterator
     * @return an iterator of channels
     */
    public static <E> Iterator<List<Optional<E>>> unchainLongest(int channelSize, Iterator<E> iterator) {
        final ConstantSupplier<Optional<Integer>> channelsSizesProvider = new ConstantSupplier<Optional<Integer>>(Optional.of(channelSize));
        final Supplier<List<Optional<E>>> channelFactory = Compositions.compose(new Vary<ArrayList<Optional<E>>, List<Optional<E>>>(), new ArrayListFactory<Optional<E>>());
        return new UnchainLongestIterator<List<Optional<E>>, E>(channelsSizesProvider, iterator, channelFactory);
    }

    /**
     * Demultiplexes elements from the source array into an iterator of channels.
     * All returned channels will have the specified size, eventually the last channel could have the last elements empty.
     * <code>
     * unchainLongest(2, [1,2,3,4,5]) -> [[just 1, just 2],[just 3, just 4],[just 5, nothing]]
     * </code>
     *
     * @param <C> the channel collection type
     * @param <E> the element type
     * @param channelSize size of the channel
     * @param iterable the source iterable
     * @param channelProvider the supplier used to create channels
     * @return an iterator of channels
     */
    public static <C extends Collection<Optional<E>>, E> Iterator<C> unchainLongest(int channelSize, Iterable<E> iterable, Supplier<C> channelProvider) {
        dbc.precondition(iterable != null, "cannot unchainLongest a null iterable");
        final ConstantSupplier<Optional<Integer>> channelsSizesProvider = new ConstantSupplier<Optional<Integer>>(Optional.of(channelSize));
        return new UnchainLongestIterator<C, E>(channelsSizesProvider, iterable.iterator(), channelProvider);
    }

    /**
     * Demultiplexes elements from the source array into an iterator of channels.
     * All returned channels will have the specified size, eventually the last channel could have the last elements empty.
     * <code>
     * unchainLongest(2, [1,2,3,4,5]) -> [[just 1, just 2],[just 3, just 4],[just 5, nothing]]
     * </code>
     *
     * @param <E> the element type
     * @param channelSize size of the channel
     * @param iterable the source iterable
     * @return an iterator of channels
     */
    public static <E> Iterator<List<Optional<E>>> unchainLongest(int channelSize, Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot unchainLongest a null iterable");
        final ConstantSupplier<Optional<Integer>> channelsSizesProvider = new ConstantSupplier<Optional<Integer>>(Optional.of(channelSize));
        final Supplier<List<Optional<E>>> channelFactory = Compositions.compose(new Vary<ArrayList<Optional<E>>, List<Optional<E>>>(), new ArrayListFactory<Optional<E>>());
        return new UnchainLongestIterator<List<Optional<E>>, E>(channelsSizesProvider, iterable.iterator(), channelFactory);
    }

    /**
     * Demultiplexes elements from the source array into an iterator of channels.
     * All returned channels will have the specified size, eventually the last channel could have the last elements empty.
     * <code>
     * unchainLongest(2, [1,2,3,4,5]) -> [[just 1, just 2],[just 3, just 4],[just 5, nothing]]
     * </code>
     *
     * @param <C> the channel collection type
     * @param <E> the element type
     * @param channelSize size of the channel
     * @param channelProvider the supplier used to create channels
     * @param array the source array
     * @return an iterator of channels
     */
    public static <C extends Collection<Optional<E>>, E> Iterator<C> unchainLongest(int channelSize, Supplier<C> channelProvider, E... array) {
        final ConstantSupplier<Optional<Integer>> channelsSizesProvider = new ConstantSupplier<Optional<Integer>>(Optional.of(channelSize));
        return new UnchainLongestIterator<C, E>(channelsSizesProvider, new ArrayIterator<E>(array), channelProvider);
    }

    /**
     * Demultiplexes elements from the source array into an iterator of channels.
     * All returned channels will have the specified size, eventually the last channel could have the last elements empty.
     * <code>
     * unchainLongest(2, [1,2,3,4,5]) -> [[just 1, just 2],[just 3, just 4],[just 5, nothing]]
     * </code>
     *
     * @param <E> the element type
     * @param channelSize size of the channel
     * @param array the source array
     * @return an iterator of channels
     */
    public static <E> Iterator<List<Optional<E>>> unchainLongest(int channelSize, E... array) {
        final ConstantSupplier<Optional<Integer>> channelsSizesProvider = new ConstantSupplier<Optional<Integer>>(Optional.of(channelSize));
        final Supplier<List<Optional<E>>> channelFactory = Compositions.compose(new Vary<ArrayList<Optional<E>>, List<Optional<E>>>(), new ArrayListFactory<Optional<E>>());
        return new UnchainLongestIterator<List<Optional<E>>, E>(channelsSizesProvider, new ArrayIterator<E>(array), channelFactory);
    }
}
