package net.emaze.dysfunctional.consumers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.collections.CollectionProvider;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Provider;

/**
 *
 * @author rferranti
 */
public abstract class Consumers {


    /**
     * yields all elements of the iterator (in the provided collection).
     * @param <R> the returned collection type
     * @param <E> the collection element type
     * @param iterator the iterator that will be consumed
     * @param collection the collection where the iterator is consumed
     * @return the collection filled with iterator values
     */
    public static <R extends Collection<E>, E> R all(Iterator<E> iterator, R collection) {
        return new ConsumeIntoCollection<R, E>(new CollectionProvider<R, E>(collection)).perform(iterator);
    }

    /**
     * yields all elements of the iterator (in the provided collection).
     * @param <R> the returned collection type
     * @param <E> the collection element type
     * @param iterable the iterable that will be consumed
     * @param collection the collection where the iterator is consumed
     * @return the collection filled with iterator values
     */
    public static <R extends Collection<E>, E> R all(Iterable<E> iterable, R collection) {
        dbc.precondition(iterable != null, "cannot call all with a null iterable");
        return Consumers.all(iterable.iterator(), collection);
    }

    /**
     * yields all elements of the array (in the provided collection).
     * @param <R> the returned collection type
     * @param <E> the collection element type
     * @param array the array that will be consumed
     * @param collection the collection where the iterator is consumed
     * @return the collection filled with iterator values
     */
    public static <R extends Collection<E>, E> R all(E[] array, R collection) {
        return Consumers.all(new ArrayIterator<E>(array), collection);
    }

    /**
     * yields all elements of the iterator (in a collection created by the factory).
     * @param <R> the returned collection type
     * @param <E> the collection element type
     * @param iterator the iterator that will be consumed
     * @param provider the factory used to provide the returned collection
     * @return a collection filled with iterator values
     */
    public static <E, R extends Collection<E>> R all(Iterator<E> iterator, Provider<R> provider) {
        return new ConsumeIntoCollection<R, E>(provider).perform(iterator);
    }

    /**
     * yields all elements of the iterator (in a collection created by the factory).
     * @param <R> the returned collection type
     * @param <E> the collection element type
     * @param iterable the iterable that will be consumed
     * @param provider the factory used to provide the returned collection
     * @return a collection filled with iterator values
     */
    public static <E, R extends Collection<E>> R all(Iterable<E> iterable, Provider<R> provider) {
        dbc.precondition(iterable != null, "cannot call first with a null iterable");
        return Consumers.all(iterable.iterator(), provider);
    }

    /**
     * yields all elements of the iterator (in a collection created by the factory).
     * @param <R> the returned collection type
     * @param <E> the collection element type
     * @param array the array that will be consumed
     * @param provider the factory used to provide the returned collection
     * @return a collection filled with iterator values
     */
    public static <R extends Collection<E>, E> R all(E[] array, Provider<R> provider) {
        return Consumers.all(new ArrayIterator<E>(array), provider);
    }

    /**
     * yields all elements of the iterator (in a list).
     * @param <E> the iterator element type
     * @param iterator the iterator that will be consumed
     * @return a list filled with iterator values
     */
    public static <E> List<E> all(Iterator<E> iterator) {
        return Consumers.all(iterator, new ArrayList<E>());
    }

    /**
     * yields all elements of the iterable's iterator (in a list).
     * @param <E>
     * @param iterable
     * @return
     */
    public static <E> List<E> all(Iterable<E> iterable) {
        return Consumers.all(iterable, new ArrayList<E>());
    }

    /**
     * yields all element of the array in a list
     * @param <E>
     * @param array
     * @return
     */
    public static <E> List<E> all(E[] array) {
        return Consumers.all(array, new ArrayList<E>());
    }

    /**
     * consumes the input iterator to the output iterator
     * @param <E>
     * @param iterator
     * @param outputIterator
     */
    public static <E> void pipe(Iterator<E> iterator, OutputIterator<E> outputIterator) {
        new ConsumeIntoOutputIterator<E>(outputIterator).perform(iterator);
    }

    /**
     * consumes the iterable's input iterator to the output iterator
     * @param <E>
     * @param iterable
     * @param outputIterator
     */
    public static <E> void pipe(Iterable<E> iterable, OutputIterator<E> outputIterator) {
        dbc.precondition(iterable != null, "cannot call pipe with a null iterable");
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
