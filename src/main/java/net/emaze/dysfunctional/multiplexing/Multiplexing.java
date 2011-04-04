package net.emaze.dysfunctional.multiplexing;

import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.adapting.ArrayIterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.IteratorPlucker;
import net.emaze.dysfunctional.iterations.Iterations;
import net.emaze.dysfunctional.options.Maybe;

/**
 *
 * @author rferranti
 */
public abstract class Multiplexing {

    /**
     * Flattens an iterator of iterables of E to an iterator of E.
     * @param <E> the iterable element type
     * @param <T> the iterable type
     * @param iterables the iterator of iterables to be flattened
     * @return the flattened iterator
     */
    public static <E, T extends Iterable<E>> Iterator<E> flatten(Iterator<T> iterables) {
        return new ChainIterator<E>(Iterations.transform(iterables, new IteratorPlucker<E, T>()));
    }

    /**
     * Flattens an iterable of iterables of E to an iterator of E.
     * @param <E> the iterable element type
     * @param <T> the iterable type
     * @param iterables the iterable of iterables to be flattened
     * @return the flattened iterator
     */
    public static <E, T extends Iterable<E>> Iterator<E> flatten(Iterable<T> iterables) {
        dbc.precondition(iterables != null, "cannot flatten a null iterable");
        return flatten(iterables.iterator());
    }

    /**
     * Flattens passed iterables of E to an iterator of E.
     * @param <E> the iterable element type
     * @param <T> the iterable type
     * @param first the first iterable to be flattened
     * @param second the second iterable to be flattened
     * @return the flattened iterator
     */
    public static <E, T extends Iterable<E>> Iterator<E> flatten(T first, T second) {
        return flatten(Iterations.iterator(first, second));
    }

    /**
     * Flattens passed iterables of E to an iterator of E.
     * @param <E> the iterable element type
     * @param <T> the iterable type
     * @param first the first iterable to be flattened
     * @param second the second iterable to be flattened
     * @param third the third iterable to be flattened
     * @return the flattened iterator
     */
    public static <E, T extends Iterable<E>> Iterator<E> flatten(T first, T second, T third) {
        return flatten(Iterations.iterator(first, second, third));
    }

    /**
     * Flattens an iterator of iterators of E to an iterator of E.
     * @param <E> the iterator element type
     * @param <T> the iterator type
     * @param iterators
     * @return the flattened iterator
     */
    public static <E, T extends Iterator<E>> Iterator<E> chain(Iterator<T> iterators) {
        return new ChainIterator<E>(iterators);
    }

    /**
     * Flattens an iterable of iterators of E to an iterator of E.
     * @param <E> the iterator element type
     * @param <T> the iterator type
     * @param iterable
     * @return the flattened iterator
     */
    public static <E, T extends Iterator<E>> Iterator<E> chain(Iterable<T> iterable) {
        dbc.precondition(iterable != null, "cannot chain a null iterable");
        return new ChainIterator<E>(iterable.iterator());
    }

    /**
     * Flattens passed iterators of E to an iterator of E.
     * @param <E> the iterator element type
     * @param <T> the iterator type
     * @param first the first iterator to be flattened
     * @param second the second iterator to be flattened
     * @return the flattened iterator
     */
    public static <E, T extends Iterator<E>> Iterator<E> chain(T first, T second) {
        return new ChainIterator<E>(Iterations.iterator(first, second));
    }

    /**
     * Flattens passed iterators of E to an iterator of E.
     * @param <E> the iterator element type
     * @param <T> the iterator type
     * @param first the first iterator to be flattened
     * @param second the second iterator to be flattened
     * @param third the third iterator to be flattened
     * @return the flattened iterator
     */
    public static <E, T extends Iterator<E>> Iterator<E> chain(T first, T second, T third) {
        return new ChainIterator<E>(Iterations.iterator(first, second, third));
    }

    /**
     * 
     * @param <E>
     * @param <T>
     * @param iterators
     * @return
     */
    public static <E, T extends Iterator<E>> Iterator<E> mux(Iterator<T> iterators) {
        return new MultiplexingIterator<E>(iterators);
    }

    /**
     * 
     * @param <E>
     * @param <T> 
     * @param iterable
     * @return
     */
    public static <E, T extends Iterator<E>> Iterator<E> mux(Iterable<T> iterable) {
        dbc.precondition(iterable != null, "cannot chain a null iterable");
        return mux(iterable.iterator());
    }

    /**
     * 
     * @param <E>
     * @param first
     * @param second
     * @return
     */
    public static <E> Iterator<E> mux(Iterator<E> first, Iterator<E> second) {
        return mux(Iterations.iterator(first, second));
    }

    /**
     * 
     * @param <E>
     * @param first
     * @param second
     * @param third
     * @return
     */
    public static <E> Iterator<E> mux(Iterator<E> first, Iterator<E> second, Iterator<E> third) {
        return mux(Iterations.iterator(first, second, third));
    }

    /**
     * 
     * @param <E>
     * @param <T>
     * @param iterators
     * @return
     */
    public static <E, T extends Iterator<E>> Iterator<Maybe<E>> muxl(Iterator<T> iterators) {
        return new PreciseMultiplexingIterator<E>(iterators);
    }

    /**
     * 
     * @param <E>
     * @param <T> 
     * @param iterable
     * @return
     */
    public static <E, T extends Iterator<E>> Iterator<Maybe<E>> muxl(Iterable<T> iterable) {
        dbc.precondition(iterable != null, "cannot muxl a null iterable");
        return muxl(iterable.iterator());
    }

    /**
     * 
     * @param <E>
     * @param first
     * @param second
     * @return
     */
    public static <E> Iterator<Maybe<E>> muxl(Iterator<E> first, Iterator<E> second) {
        return muxl(Iterations.iterator(first, second));
    }

    /**
     * 
     * @param <E>
     * @param first
     * @param second
     * @param third
     * @return
     */
    public static <E> Iterator<Maybe<E>> muxl(Iterator<E> first, Iterator<E> second, Iterator<E> third) {
        return muxl(Iterations.iterator(first, second, third));
    }

    /**
     * 
     * @param <E>
     * @param channelSize
     * @param iterator
     * @return
     */
    public static <E> Iterator<List<E>> demux(int channelSize, Iterator<E> iterator) {
        return new DemultiplexingIterator<E>(channelSize, iterator);
    }

    /**
     * 
     * @param <E>
     * @param channelSize
     * @param iterable
     * @return
     */
    public static <E> Iterator<List<E>> demux(int channelSize, Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot demux a null iterable");
        return demux(channelSize, iterable.iterator());
    }

    /**
     * 
     * @param <E>
     * @param channelSize
     * @param array
     * @return
     */
    public static <E> Iterator<List<E>> demux(int channelSize, E... array) {
        return demux(channelSize, new ArrayIterator<E>(array));
    }

    /**
     * 
     * @param <E>
     * @param channelSize
     * @param iterator
     * @return
     */
    public static <E> Iterator<List<Maybe<E>>> demuxl(int channelSize, Iterator<Maybe<E>> iterator) {
        return new PreciseDemultiplexingIterator<E>(channelSize, iterator);
    }

    /**
     * 
     * @param <E>
     * @param channelSize
     * @param iterable
     * @return
     */
    public static <E> Iterator<List<Maybe<E>>> demuxl(int channelSize, Iterable<Maybe<E>> iterable) {
        dbc.precondition(iterable != null, "cannot roundrobin a null iterable");
        return new PreciseDemultiplexingIterator<E>(channelSize, iterable.iterator());
    }

    /**
     * 
     * @param <E>
     * @param channelSize
     * @param array
     * @return
     */
    public static <E> Iterator<List<Maybe<E>>> demuxl(int channelSize, Maybe<E>... array) {
        return demuxl(channelSize, new ArrayIterator<Maybe<E>>(array));
    }

    /**
     * 
     * @param <E>
     * @param <T>
     * @param iterators
     * @return
     */
    public static <E, T extends Iterator<E>> Iterator<E> roundrobin(Iterator<T> iterators) {
        return new RoundRobinIterator<E>(iterators);
    }

    /**
     * 
     * @param <E>
     * @param <T>
     * @param iterable
     * @return
     */
    public static <E, T extends Iterator<E>> Iterator<E> roundrobin(Iterable<T> iterable) {
        dbc.precondition(iterable != null, "cannot roundrobin a null iterable");
        return new RoundRobinIterator<E>(iterable.iterator());
    }

    /**
     * 
     * @param <E>
     * @param first
     * @param second
     * @return
     */
    public static <E> Iterator<E> roundrobin(Iterator<E> first, Iterator<E> second) {
        return new RoundRobinIterator<E>(Iterations.iterator(first, second));
    }

    /**
     * 
     * @param <E>
     * @param first
     * @param second
     * @param third
     * @return
     */
    public static <E> Iterator<E> roundrobin(Iterator<E> first, Iterator<E> second, Iterator<E> third) {
        return new RoundRobinIterator<E>(Iterations.iterator(first, second, third));
    }
}
