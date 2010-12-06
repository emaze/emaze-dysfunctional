package net.emaze.dysfunctional.consumers;

import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.adapting.ArrayToIterableAdapter;
import net.emaze.dysfunctional.delegates.AtIndex;
import net.emaze.dysfunctional.delegates.Nth;

/**
 *
 * @author rferranti
 */
public abstract class Consumers {

    /**
     * yields the first element in the iterator
     * @param <E>
     * @param iterator
     * @return
     */
    public static <E> E first(Iterator<E> iterator) {
        return new GiveUpConsumer<E>().consume(iterator);
    }

    /**
     * yields the first element of the iterable's iterator
     * @param <E>
     * @param iterable
     * @return
     */
    public static <E> E first(Iterable<E> iterable) {
        return first(iterable.iterator());
    }

    /**
     * yields the first element of the array
     * @param <E>
     * @param array
     * @return
     */
    public static <E> E first(E[] array) {
        return first(new ArrayToIterableAdapter<E>(array));
    }

    /**
     * yields all elements of the iterator (in a list)
     * @param <E>
     * @param iterator
     * @return
     */
    public static <E> List<E> all(Iterator<E> iterator) {
        return new EagerConsumer<E>().consume(iterator);
    }

    /**
     * yields all elements of the iterable's iterator (in a list)
     * @param <E>
     * @param iterable
     * @return
     */
    public static <E> List<E> all(Iterable<E> iterable) {
        return all(iterable.iterator());
    }

    /**
     * yields all element of the array in a list
     * @param <E>
     * @param array
     * @return
     */
    public static <E> List<E> all(E[] array) {
        return all(new ArrayToIterableAdapter<E>(array));
    }

    /**
     * yields last elemenet of the iterator
     * @param <E>
     * @param iterator
     * @return
     */
    public static <E> E last(Iterator<E> iterator) {
        return new StubbornConsumer<E>().consume(iterator);
    }

    /**
     * yields last element of the iterable's iterator
     * @param <E>
     * @param iterable
     * @return
     */
    public static <E> E last(Iterable<E> iterable) {
        return last(iterable.iterator());
    }

    /**
     * yields last element of the array
     * @param <E>
     * @param array
     * @return
     */
    public static <E> E last(E[] array) {
        return last(new ArrayToIterableAdapter<E>(array));
    }

    /**
     * yields nth (1-based) element of the iterator
     * @param <E>
     * @param count
     * @param iterator
     * @return
     */
    public static <E> E nth(long count, Iterator<E> iterator) {
        return new PreciseConsumer<E>(new Nth<E>(count)).consume(iterator);
    }

    /**
     * yields nth (1-based) element of the iterable's iterator
     * @param <E>
     * @param count
     * @param iterable
     * @return
     */
    public static <E> E nth(long count, Iterable<E> iterable) {
        return nth(count, iterable.iterator());
    }

    /**
     * yields nth (1-based) element of the array
     * @param <E>
     * @param count
     * @param array
     * @return
     */
    public static <E> E nth(long count, E[] array) {
        return nth(count, new ArrayToIterableAdapter<E>(array));
    }

    /**
     * yields element at (0-based) position of the iterator
     * @param <E>
     * @param index
     * @param iterator
     * @return
     */
    public static <E> E at(long index, Iterator<E> iterator) {
        return new PreciseConsumer<E>(new AtIndex<E>(index)).consume(iterator);
    }

    /**
     * yields element at (0-based) position of the iterable's iterator
     * @param <E>
     * @param index
     * @param iterable
     * @return
     */
    public static <E> E at(long index, Iterable<E> iterable) {
        return at(index, iterable.iterator());
    }

    /**
     * yields element at (0-based) position of the array
     * @param <E>
     * @param index
     * @param array
     * @return
     */
    public static <E> E at(long index, E[] array) {
        return at(index, new ArrayToIterableAdapter<E>(array));
    }

    /**
     * consumes the input iterator to the output iterator
     * @param <E>
     * @param iterator
     * @param outputIterator
     */
    public static <E> void pipe(Iterator<E> iterator, OutputIterator<E> outputIterator) {
        new PipingConsumer<E>(outputIterator).consume(iterator);
    }

    /**
     * consumes the iterable's input iterator to the output iterator
     * @param <E>
     * @param iterable
     * @param outputIterator
     */
    public static <E> void pipe(Iterable<E> iterable, OutputIterator<E> outputIterator) {
        pipe(iterable.iterator(), outputIterator);
    }

    /**
     * consumes the array to the output iterator
     * @param <E>
     * @param array
     * @param outputIterator 
     */
    public static <E> void pipe(E[] array, OutputIterator<E> outputIterator) {
        pipe(new ArrayToIterableAdapter<E>(array), outputIterator);
    }

    /**
     * 
     * @param <E>
     * @param iterator
     * @return
     */
    public static <E> long count(Iterator<E> iterator) {
        return new CountingConsumer<E>().consume(iterator);
    }

    /**
     * 
     * @param <E>
     * @param iterable
     * @return
     */
    public static <E> long count(Iterable<E> iterable) {
        return count(iterable.iterator());
    }

    /**
     * 
     * @param <E>
     * @param array
     * @param outputIterator
     * @return
     */
    public static <E> long count(E[] array, OutputIterator<E> outputIterator) {
        return count(new ArrayToIterableAdapter<E>(array));
    }
}
