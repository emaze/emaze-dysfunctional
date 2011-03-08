package net.emaze.dysfunctional.multiplexing;

import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.adapting.ArrayIterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.IteratorPlucker;
import net.emaze.dysfunctional.iterations.Iterations;
import net.emaze.dysfunctional.options.Maybe;

/**
 *
 * @author rferranti
 */
public abstract class Multiplexing {

    /**
     * 
     * @param <E>
     * @param <T>
     * @param iterators
     * @return
     */
    public static <E, T extends Iterable<E>> Iterator<E> flatten(Iterator<T> iterators) {
        return new ChainIterator<E>(Iterations.transform(iterators, new IteratorPlucker<E, T>()));
    }

    /**
     * 
     * @param <E>
     * @param <T>
     * @param iterators
     * @return
     */
    public static <E, T extends Iterable<E>> Iterator<E> flatten(Iterable<T> iterators) {
        dbc.precondition(iterators != null, "cannot flatten a null iterable");
        return flatten(iterators.iterator());
    }

    /**
     * 
     * @param <E>
     * @param <T>
     * @param array
     * @return
     */
    public static <E, T extends Iterable<E>> Iterator<E> flatten(T... array) {
        return flatten(new ArrayIterator<T>(array));
    }

    /**
     *
     * @param <E>
     * @param <T> 
     * @param iterators
     * @return
     */
    public static <E, T extends Iterator<E>> Iterator<E> chain(Iterator<T> iterators) {
        return new ChainIterator<E>(iterators);
    }

    /**
     *
     * @param <E>
     * @param <T>
     * @param iterables
     * @return
     */
    public static <E, T extends Iterator<E>> Iterator<E> chain(Iterable<T> iterables) {
        dbc.precondition(iterables != null, "cannot chain a null iterable");
        return new ChainIterator<E>(iterables.iterator());
    }

    /**
     *
     * @param <E>
     * @param <T>
     * @param iterators
     * @return
     */
    public static <E, T extends Iterator<E>> Iterator<E> chain(T... iterators) {
        return new ChainIterator<E>(iterators);
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
     * @param iterators
     * @return
     */
    public static <E, T extends Iterator<E>> Iterator<E> mux(Iterable<T> iterators) {
        dbc.precondition(iterators != null, "cannot chain a null iterable");
        return mux(iterators.iterator());
    }

    /**
     * 
     * @param <E>
     * @param iterators
     * @return
     */
    public static <E> Iterator<E> mux(Iterator<E>... iterators) {
        return mux(new ArrayIterator<Iterator<E>>(iterators));
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
     * @param iterators
     * @return
     */
    public static <E, T extends Iterator<E>> Iterator<Maybe<E>> muxl(Iterable<T> iterators) {
        return muxl(iterators.iterator());
    }

    /**
     * 
     * @param <E>
     * @param iterators
     * @return
     */
    public static <E> Iterator<Maybe<E>> muxl(Iterator<E>... iterators) {
        return muxl(new ArrayIterator<Iterator<E>>(iterators));
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
     * @param iterators
     * @return
     */
    public static <E, T extends Iterator<E>> Iterator<E> roundrobin(Iterable<T> iterators) {
        return new RoundRobinIterator<E>(iterators.iterator());
    }

    /**
     * 
     * @param <E>
     * @param iterators
     * @return
     */
    public static <E> Iterator<E> roundrobin(Iterator<E>... iterators) {
        return new RoundRobinIterator<E>(new ArrayIterator<Iterator<E>>(iterators));
    }
}
