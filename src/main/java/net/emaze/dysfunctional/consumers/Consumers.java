package net.emaze.dysfunctional.consumers;

import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.adapting.ArrayIterator;

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
        return first(new ArrayIterator<E>(array));
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
        return all(new ArrayIterator<E>(array));
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
        return last(new ArrayIterator<E>(array));
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
        pipe(new ArrayIterator<E>(array), outputIterator);
    }
}
