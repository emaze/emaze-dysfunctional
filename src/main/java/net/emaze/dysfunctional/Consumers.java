package net.emaze.dysfunctional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.collections.CollectionProvider;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.ConsumeIntoCollection;
import net.emaze.dysfunctional.dispatching.delegates.ConsumeIntoOutputIterator;
import net.emaze.dysfunctional.dispatching.delegates.FirstElement;
import net.emaze.dysfunctional.dispatching.delegates.OneElement;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.options.MaybeFirstElement;
import net.emaze.dysfunctional.options.MaybeLastElement;
import net.emaze.dysfunctional.options.MaybeOneElement;
import net.emaze.dysfunctional.output.OutputIterator;

/**
 *
 * @author rferranti
 */
public abstract class Consumers {

    /**
     * yields all elements of the iterator (in the provided collection).
     *
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
     *
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
     *
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
     * yields all elements of the iterator (in a collection created by the
     * factory).
     *
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
     * yields all elements of the iterator (in a collection created by the
     * factory).
     *
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
     * yields all elements of the iterator (in a collection created by the
     * factory).
     *
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
     *
     * @param <E> the iterator element type
     * @param iterator the iterator that will be consumed
     * @return a list filled with iterator values
     */
    public static <E> List<E> all(Iterator<E> iterator) {
        return Consumers.all(iterator, new ArrayList<E>());
    }

    /**
     * yields all elements of the iterable's iterator (in a list).
     *
     * @param <E>
     * @param iterable
     * @return
     */
    public static <E> List<E> all(Iterable<E> iterable) {
        return Consumers.all(iterable, new ArrayList<E>());
    }

    /**
     * yields all element of the array in a list
     *
     * @param <E>
     * @param array
     * @return
     */
    public static <E> List<E> all(E[] array) {
        return Consumers.all(array, new ArrayList<E>());
    }

    /**
     * consumes the input iterator to the output iterator
     *
     * @param <E>
     * @param iterator
     * @param outputIterator
     */
    public static <E> void pipe(Iterator<E> iterator, OutputIterator<E> outputIterator) {
        new ConsumeIntoOutputIterator<E>(outputIterator).perform(iterator);
    }

    /**
     * consumes the iterable's input iterator to the output iterator
     *
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
     *
     * @param <E>
     * @param array
     * @param outputIterator
     */
    public static <E> void pipe(E[] array, OutputIterator<E> outputIterator) {
        pipe(new ArrayIterator<E>(array), outputIterator);
    }

    /**
     * yields the first element if found, nothing otherwise.
     *
     * @param <E>
     * @param iterable
     * @return
     */
    public static <E> Maybe<E> search(Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call search with a null iterable");
        return new MaybeFirstElement<E>().perform(iterable.iterator());
    }

    /**
     * yields the first element if found, nothing otherwise.
     *
     * @param <E>
     * @param iterator
     * @return just the first element or nothing
     */
    public static <E> Maybe<E> search(Iterator<E> iterator) {
        return new MaybeFirstElement<E>().perform(iterator);
    }

    /**
     * yields the first element if found, nothing otherwise.
     *
     * @param <E>
     * @param array
     * @return
     */
    public static <E> Maybe<E> search(E[] array) {
        return new MaybeFirstElement<E>().perform(new ArrayIterator<E>(array));
    }

    /**
     * Lookups the first matching element returning it.
     *
     * @param <E> the element type parameter
     * @param iterator the iterator to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalArgumentException if no element matches
     * @return the found element
     */
    public static <E> E find(Iterator<E> iterator) {
        return new FirstElement<E>().perform(iterator);
    }

    /**
     * Lookups the first matching element returning it.
     *
     * @param <E> the element type parameter
     * @param iterable the iterable to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalArgumentException if no element matches
     * @return the found element
     */
    public static <E> E find(Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call find with a null iterable");
        return new FirstElement<E>().perform(iterable.iterator());
    }

    /**
     * Lookups the first matching element returning it.
     *
     * @param <E> the element type parameter
     * @param array the array to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalArgumentException if no element matches
     * @return the found element
     */
    public static <E> E find(E[] array) {
        return new FirstElement<E>().perform(new ArrayIterator<E>(array));
    }

    /**
     * yields the only element if found, nothing otherwise.
     *
     * @param <E>
     * @param iterator
     * @throws IllegalStateException if the iterator contains more than one
     * element
     * @return
     */
    public static <E> Maybe<E> searchOne(Iterator<E> iterator) {
        return new MaybeOneElement<E>().perform(iterator);
    }

    /**
     * yields the only element if found, nothing otherwise.
     *
     * @param <E>
     * @param iterable
     * @throws IllegalStateException if the iterator contains more than one
     * element
     * @return
     */
    public static <E> Maybe<E> searchOne(Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call searchOne with a null iterable");
        return new MaybeOneElement<E>().perform(iterable.iterator());
    }

    /**
     * yields the only element if found, nothing otherwise.
     *
     * @param <E>
     * @param array
     * @throws IllegalStateException if the iterator contains more than one
     * element
     * @return
     */
    public static <E> Maybe<E> searchOne(E[] array) {
        return new MaybeOneElement<E>().perform(new ArrayIterator<E>(array));
    }

    /**
     * Lookups the only matching element returning it.
     *
     * @param <E> the element type parameter
     * @param iterator the iterator to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalStateException if more than one element is found
     * @throws IllegalArgumentException if no element matches
     * @return the found element
     */
    public static <E> E findOne(Iterator<E> iterator) {
        return new OneElement<E>().perform(iterator);
    }

    /**
     * Lookups the only matching element returning it.
     *
     * @param <E> the element type parameter
     * @param iterable the iterable to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalStateException if more than one element is found
     * @throws IllegalArgumentException if no element matches
     * @return the found element
     */
    public static <E> E findOne(Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call findOne with a null iterable");
        return new OneElement<E>().perform(iterable.iterator());
    }

    /**
     * Lookups the only matching element returning it.
     *
     * @param <E> the element type parameter
     * @param array the array to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalStateException if more than one element is found
     * @throws IllegalArgumentException if no element matches
     * @return the found element
     */
    public static <E> E findOne(E[] array) {
        return new OneElement<E>().perform(new ArrayIterator<E>(array));
    }

    /**
     *
     * @param <E>
     * @param iterator
     * @return
     */
    public static <E> Maybe<E> searchLast(Iterator<E> iterator) {
        return new MaybeLastElement<E>().perform(iterator);
    }

    /**
     *
     * @param <T>
     * @param iterable
     * @return
     */
    public static <E> Maybe<E> searchLast(Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call searchLast with a null iterable");
        return new MaybeLastElement<E>().perform(iterable.iterator());
    }

    /**
     *
     * @param <T>
     * @param array
     * @return
     */
    public static <E> Maybe<E> searchLast(E[] array) {
        return new MaybeLastElement<E>().perform(new ArrayIterator<E>(array));
    }
}
