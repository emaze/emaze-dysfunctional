package net.emaze.dysfunctional.consumers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.adapting.ArrayIterator;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.collections.CollectionFactory;

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
     * yields all elements of the iterator (in a collection created by the factory).
     * @param <R> the returned collection type
     * @param <E> the collection element type
     * @param <CF> the collection factory type
     * @param iterator the iterator that will be consumed
     * @param factory the factory used to create the returned collection
     * @return a collection filled with iterator values
     */
    public static <R extends Collection<E>, E, CF extends CollectionFactory<R, E>> R all(Iterator<E> iterator, CF factory) {
        return new EagerConsumer<R, E>(factory).consume(iterator);
    }

    /**
     * yields all elements of the iterator (in a collection created by the factory).
     * @param <R> the returned collection type
     * @param <E> the collection element type
     * @param <CF> the collection factory type
     * @param iterable the iterable that will be consumed
     * @param factory the factory used to create the returned collection
     * @return a collection filled with iterator values
     */
    public static <R extends Collection<E>, E, CF extends CollectionFactory<R, E>> R all(Iterable<E> iterable, CF factory) {
        return Consumers.<R, E, CF>all(iterable.iterator(), factory);
    }

    /**
     * yields all elements of the iterator (in a collection created by the factory).
     * @param <R> the returned collection type
     * @param <E> the collection element type
     * @param <CF> the collection factory type
     * @param array the array that will be consumed
     * @param factory the factory used to create the returned collection
     * @return a collection filled with iterator values
     */
    public static <R extends Collection<E>, E, CF extends CollectionFactory<R, E>> R all(E[] array, CF factory) {
        return Consumers.<R, E, CF>all(new ArrayIterator<E>(array), factory);
    }

    /**
     * yields all elements of the iterator (in a list).
     * @param <E> the iterator element type
     * @param iterator the iterator that will be consumed
     * @return a list filled with iterator values
     */
    public static <E> List<E> all(Iterator<E> iterator) {
        return Consumers.<ArrayList<E>, E, ArrayListFactory<E>>all(iterator, new ArrayListFactory<E>());
    }

    /**
     * yields all elements of the iterable's iterator (in a list).
     * @param <E>
     * @param iterable
     * @return
     */
    public static <E> List<E> all(Iterable<E> iterable) {
        return Consumers.<ArrayList<E>, E, ArrayListFactory<E>>all(iterable, new ArrayListFactory<E>());
    }

    /**
     * yields all element of the array in a list
     * @param <E>
     * @param array
     * @return
     */
    public static <E> List<E> all(E[] array) {
        return Consumers.<ArrayList<E>, E, ArrayListFactory<E>>all(array, new ArrayListFactory<E>());
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
