package net.emaze.dysfunctional.multiplexing;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.adapting.ArrayIterator;
import net.emaze.dysfunctional.consumers.Consumers;
import net.emaze.dysfunctional.delegates.IteratorPlucker;
import net.emaze.dysfunctional.iterations.Iterations;
import net.emaze.dysfunctional.options.Maybe;

/**
 *
 * @author rferranti
 */
public abstract class Multiplexing {

    public static <E, T extends Collection<E>> Iterator<E> flatten(Iterator<T> iterators) {
        return new ChainIterator<E>(Consumers.all(Iterations.transform(iterators, new IteratorPlucker<E, T>())));
    }

    public static <E, T extends Collection<E>> Iterator<E> flatten(Iterable<T> iterators) {
        return flatten(iterators.iterator());
    }

    public static <E, T extends Collection<E>> Iterator<E> flatten(T[] array) {
        return flatten(new ArrayIterator<T>(array));
    }

    public static <E> Iterator<E> mux(Iterator<Iterator<E>> iterators) {
        return new MultiplexingIterator<E>(iterators);
    }

    public static <E> Iterator<E> mux(Iterable<Iterator<E>> iterators) {
        return mux(iterators.iterator());
    }

    public static <E> Iterator<E> mux(Iterator<E>[] iterators) {
        return mux(new ArrayIterator<Iterator<E>>(iterators));
    }

    public static <E> Iterator<Maybe<E>> muxl(Iterator<Iterator<E>> iterators) {
        return new PreciseMultiplexingIterator<E>(iterators);
    }

    public static <E> Iterator<Maybe<E>> muxl(Iterable<Iterator<E>> iterators) {
        return muxl(iterators.iterator());
    }

    public static <E> Iterator<Maybe<E>> muxl(Iterator<E>[] iterators) {
        return muxl(new ArrayIterator<Iterator<E>>(iterators));
    }

    public static <E> Iterator<List<E>> demux(int channels, Iterator<E> iterator) {
        return new DemultiplexingIterator<E>(channels, iterator);
    }

    public static <E> Iterator<List<E>> demux(int channels, Iterable<E> iterable) {
        return demux(channels, iterable.iterator());
    }

    public static <E> Iterator<List<E>> demux(int channels, E[] array) {
        return demux(channels, new ArrayIterator<E>(array));
    }

    public static <E> Iterator<List<Maybe<E>>> demuxl(int channels, Iterator<Maybe<E>> iterator) {
        return new PreciseDemultiplexingIterator<E>(channels, iterator);
    }

    public static <E> Iterator<List<Maybe<E>>> demuxli(int channels, Iterable<Maybe<E>> iterable) {
        return new PreciseDemultiplexingIterator<E>(channels, iterable.iterator());
    }

    public static <E> Iterator<List<Maybe<E>>> demuxl(int channels, Maybe<E>[] array) {
        return demuxl(channels, new ArrayIterator<Maybe<E>>(array));
    }

    public static <E> Iterator<E> roundrobin(Iterator<Iterator<E>> iterators) {
        return new RoundRobinIterator<E>(iterators);
    }

    public static <E> Iterator<E> roundrobin(Iterable<Iterator<E>> iterators) {
        return new RoundRobinIterator<E>(iterators.iterator());
    }

    public static <E> Iterator<E> roundrobin(Iterator<E>[] iterators) {
        return new RoundRobinIterator<E>(new ArrayIterator<Iterator<E>>(iterators));
    }
}
