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
import net.emaze.dysfunctional.dispatching.delegates.LastElement;
import net.emaze.dysfunctional.dispatching.delegates.OneElement;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.filtering.AtIndex;
import net.emaze.dysfunctional.filtering.FilteringIterator;
import net.emaze.dysfunctional.filtering.Nth;
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
     * Yields all elements of the iterator (in the provided collection).
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
     * Yields all elements of the iterator (in the provided collection).
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
     * Yields all elements of the array (in the provided collection).
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
     * Yields all elements of the iterator (in a collection created by the
     * provider).
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
     * Yields all elements of the iterator (in a collection created by the
     * provider).
     *
     * @param <R> the returned collection type
     * @param <E> the collection element type
     * @param iterable the iterable that will be consumed
     * @param provider the factory used to provide the returned collection
     * @return a collection filled with iterator values
     */
    public static <E, R extends Collection<E>> R all(Iterable<E> iterable, Provider<R> provider) {
        dbc.precondition(iterable != null, "cannot call all with a null iterable");
        return Consumers.all(iterable.iterator(), provider);
    }

    /**
     * Yields all elements of the iterator (in a collection created by the
     * provider).
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
     * Yields all elements of the iterable's iterator (in a list).
     *
     * @param <E> the iterable element type
     * @param iterable the iterable that will be consumed
     * @return a list filled with iterable values
     */
    public static <E> List<E> all(Iterable<E> iterable) {
        return Consumers.all(iterable, new ArrayList<E>());
    }

    /**
     * Yields all element of the array in a list.
     *
     * @param <E> the array element type
     * @param array the array that will be consumed
     * @return a list filled with array values
     */
    public static <E> List<E> all(E[] array) {
        return Consumers.all(array, new ArrayList<E>());
    }

    /**
     * Consumes the input iterator to the output iterator.
     *
     * @param <E> the iterator element type
     * @param iterator the iterator that will be consumed
     * @param outputIterator the iterator that will be filled
     */
    public static <E> void pipe(Iterator<E> iterator, OutputIterator<E> outputIterator) {
        new ConsumeIntoOutputIterator<E>(outputIterator).perform(iterator);
    }

    /**
     * Consumes the iterable's input iterator to the output iterator.
     *
     * @param <E> the iterator element type
     * @param iterable the iterable that will be consumed
     * @param outputIterator the iterator that will be filled
     */
    public static <E> void pipe(Iterable<E> iterable, OutputIterator<E> outputIterator) {
        dbc.precondition(iterable != null, "cannot call pipe with a null iterable");
        pipe(iterable.iterator(), outputIterator);
    }

    /**
     * Consumes the array to the output iterator.
     *
     * @param <E> the iterator element type
     * @param array the array that will be consumed
     * @param outputIterator the iterator that will be filled
     */
    public static <E> void pipe(E[] array, OutputIterator<E> outputIterator) {
        pipe(new ArrayIterator<E>(array), outputIterator);
    }

    /**
     * Yields the first element if present, nothing otherwise.
     *
     * @param <E> the iterable element type
     * @param iterable the iterable that will be consumed
     * @return just the first element or nothing
     */
    public static <E> Maybe<E> maybeFirst(Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call maybeFirst with a null iterable");
        return new MaybeFirstElement<E>().perform(iterable.iterator());
    }

    /**
     * Yields the first element if present, nothing otherwise.
     *
     * @param <E> the iterator element type
     * @param iterator the iterator that will be consumed
     * @return just the first element or nothing
     */
    public static <E> Maybe<E> maybeFirst(Iterator<E> iterator) {
        return new MaybeFirstElement<E>().perform(iterator);
    }

    /**
     * Yields the first element if present, nothing otherwise.
     *
     * @param <E> the array element type
     * @param array the array that will be consumed
     * @return just the first element or nothing
     */
    public static <E> Maybe<E> maybeFirst(E[] array) {
        return new MaybeFirstElement<E>().perform(new ArrayIterator<E>(array));
    }

    /**
     * Yields the first element of the iterator.
     *
     * @param <E> the element type parameter
     * @param iterator the iterator to be searched
     * @throws IllegalArgumentException if no element is present
     * @return the found element
     */
    public static <E> E first(Iterator<E> iterator) {
        return new FirstElement<E>().perform(iterator);
    }

    /**
     * Yields the first element of the iterable.
     *
     * @param <E> the element type parameter
     * @param iterable the iterable to be searched
     * @throws IllegalArgumentException if no element is present
     * @return the found element
     */
    public static <E> E first(Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call first with a null iterable");
        return new FirstElement<E>().perform(iterable.iterator());
    }

    /**
     * Yields the first element of the array.
     *
     * @param <E> the element type parameter
     * @param array the array to be searched
     * @throws IllegalArgumentException if no element matches
     * @return the found element
     */
    public static <E> E first(E[] array) {
        return new FirstElement<E>().perform(new ArrayIterator<E>(array));
    }

    /**
     * Yields the only element if found, nothing otherwise.
     *
     * @param <E> the iterator element type
     * @param iterator the iterator that will be consumed
     * @throws IllegalStateException if the iterator contains more than one
     * element
     * @return just the element or nothing
     */
    public static <E> Maybe<E> maybeOne(Iterator<E> iterator) {
        return new MaybeOneElement<E>().perform(iterator);
    }

    /**
     * Yields the only element if found, nothing otherwise.
     *
     * @param <E> the iterable element type
     * @param iterable the iterable that will be consumed
     * @throws IllegalStateException if the iterator contains more than one
     * element
     * @return just the element or nothing
     */
    public static <E> Maybe<E> maybeOne(Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call maybeOne with a null iterable");
        return new MaybeOneElement<E>().perform(iterable.iterator());
    }

    /**
     * Yields the only element if found, nothing otherwise.
     *
     * @param <E> the array element type
     * @param array the array that will be consumed
     * @throws IllegalStateException if the iterator contains more than one
     * element
     * @return just the element or nothing
     */
    public static <E> Maybe<E> maybeOne(E[] array) {
        return new MaybeOneElement<E>().perform(new ArrayIterator<E>(array));
    }

    /**
     * Yields the only element.
     *
     * @param <E> the element type parameter
     * @param iterator the iterator to be searched
     * @throws IllegalStateException if more than one element is found
     * @throws IllegalArgumentException if no element matches
     * @return the found element
     */
    public static <E> E one(Iterator<E> iterator) {
        return new OneElement<E>().perform(iterator);
    }

    /**
     * Yields the only element.
     *
     * @param <E> the element type parameter
     * @param iterable the iterable to be searched
     * @throws IllegalStateException if more than one element is found
     * @throws IllegalArgumentException if no element matches
     * @return the found element
     */
    public static <E> E one(Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call one with a null iterable");
        return new OneElement<E>().perform(iterable.iterator());
    }

    /**
     * Yields the only element.
     *
     * @param <E> the element type parameter
     * @param array the array to be searched
     * @throws IllegalStateException if more than one element is found
     * @throws IllegalArgumentException if no element matches
     * @return the found element
     */
    public static <E> E one(E[] array) {
        return new OneElement<E>().perform(new ArrayIterator<E>(array));
    }

    /**
     * Yields the last element if present, nothing otherwise.
     *
     * @param <E> the iterator element type
     * @param iterator the iterator that will be consumed
     * @return the last element or nothing
     */
    public static <E> Maybe<E> maybeLast(Iterator<E> iterator) {
        return new MaybeLastElement<E>().perform(iterator);
    }

    /**
     * Yields the last element if present, nothing otherwise.
     *
     * @param <E> the iterable element type
     * @param iterable the iterable that will be consumed
     * @return the last element or nothing
     */
    public static <E> Maybe<E> maybeLast(Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call maybeLast with a null iterable");
        return new MaybeLastElement<E>().perform(iterable.iterator());
    }

    /**
     * Yields the last element if present, nothing otherwise.
     *
     * @param <E> the array element type
     * @param array the array that will be consumed
     * @return the last element or nothing
     */
    public static <E> Maybe<E> maybeLast(E[] array) {
        return new MaybeLastElement<E>().perform(new ArrayIterator<E>(array));
    }

    /**
     * Yields the last element.
     *
     * @param <E> the iterator element type
     * @param iterator the iterator that will be consumed @throw
     * IllegalArgumentException if no element is found
     * @return the last element
     */
    public static <E> E last(Iterator<E> iterator) {
        return new LastElement<E>().perform(iterator);
    }

    /**
     * Yields the last element.
     *
     * @param <E> the iterable element type
     * @param iterable the iterable that will be consumed @throw
     * IllegalArgumentException if no element is found
     * @return the last element
     */
    public static <E> E last(Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call last with a null iterable");
        return new LastElement<E>().perform(iterable.iterator());
    }

    /**
     * Yields the last element.
     *
     * @param <E> the array element type
     * @param array the array that will be consumed @throw
     * IllegalArgumentException if no element is found
     * @return the last element
     */
    public static <E> E last(E[] array) {
        return new LastElement<E>().perform(new ArrayIterator<E>(array));
    }

    /**
     * Yields nth (1-based) element of the iterator.
     *
     * @param <E> the iterator element type
     * @param count the element cardinality
     * @param iterator the iterator that will be consumed
     * @return the nth element
     */
    public static <E> E nth(long count, Iterator<E> iterator) {
        final Iterator<E> filtered = new FilteringIterator<E>(iterator, new Nth<E>(count));
        return new FirstElement<E>().perform(filtered);
    }

    /**
     * Yields nth (1-based) element of the iterable.
     *
     * @param <E> the iterable element type
     * @param count the element cardinality
     * @param iterable the iterable that will be consumed
     * @return the nth element
     */
    public static <E> E nth(long count, Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call nth with a null iterable");
        final Iterator<E> filtered = new FilteringIterator<E>(iterable.iterator(), new Nth<E>(count));
        return new FirstElement<E>().perform(filtered);
    }

    /**
     * Yields nth (1-based) element of the array.
     *
     * @param <E> the array element type
     * @param count the element cardinality
     * @param array the array that will be consumed
     * @return the nth element
     */
    public static <E> E nth(long count, E[] array) {
        final Iterator<E> filtered = new FilteringIterator<E>(new ArrayIterator<E>(array), new Nth<E>(count));
        return new FirstElement<E>().perform(filtered);
    }

    /**
     * Yields nth (1-based) element of the iterator if found or nothing.
     *
     * @param <E> the iterator element type
     * @param count the element cardinality
     * @param iterator the iterator that will be consumed
     * @return just the element or nothing
     */
    public static <E> Maybe<E> maybeNth(long count, Iterator<E> iterator) {
        final Iterator<E> filtered = new FilteringIterator<E>(iterator, new Nth<E>(count));
        return new MaybeFirstElement<E>().perform(filtered);
    }

    /**
     * Yields nth (1-based) element of the iterable if found or nothing.
     *
     * @param <E> the iterable element type
     * @param count the element cardinality
     * @param iterable the iterable that will be consumed
     * @return just the element or nothing
     */
    public static <E> Maybe<E> maybeNth(long count, Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call maybeNth with a null iterable");
        final Iterator<E> filtered = new FilteringIterator<E>(iterable.iterator(), new Nth<E>(count));
        return new MaybeFirstElement<E>().perform(filtered);
    }

    /**
     * Yields nth (1-based) element of the array if found or nothing.
     *
     * @param <E> the array element type
     * @param count the element cardinality
     * @param array the array that will be consumed
     * @return just the element or nothing
     */
    public static <E> Maybe<E> maybeNth(long count, E[] array) {
        final Iterator<E> filtered = new FilteringIterator<E>(new ArrayIterator<E>(array), new Nth<E>(count));
        return new MaybeFirstElement<E>().perform(filtered);
    }

    /**
     * Yields element at (0-based) position of the iterator.
     *
     * @param <E> the iterator element type
     * @param index the element index
     * @param iterator the iterator that will be consumed
     * @return the element
     */
    public static <E> E at(long index, Iterator<E> iterator) {
        final Iterator<E> filtered = new FilteringIterator<E>(iterator, new AtIndex<E>(index));
        return new FirstElement<E>().perform(filtered);
    }

    /**
     * Yields element at (0-based) position of the iterable.
     *
     * @param <E> the iterable element type
     * @param index the element index
     * @param iterable the iterable that will be consumed
     * @return the element
     */
    public static <E> E at(long index, Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call at with a null iterable");
        final Iterator<E> filtered = new FilteringIterator<E>(iterable.iterator(), new AtIndex<E>(index));
        return new FirstElement<E>().perform(filtered);
    }

    /**
     * Yields element at (0-based) position of the array.
     *
     * @param <E> the array element type
     * @param index the element index
     * @param array the array that will be consumed
     * @return just the element or nothing
     */
    public static <E> E at(long index, E[] array) {
        final Iterator<E> filtered = new FilteringIterator<E>(new ArrayIterator<E>(array), new AtIndex<E>(index));
        return new FirstElement<E>().perform(filtered);
    }

    /**
     * Yields element at (0-based) position of the iterator if found or nothing.
     *
     * @param <E> the iterator element type
     * @param index the element index
     * @param iterator the iterator that will be consumed
     * @return just the element or nothing
     */
    public static <E> Maybe<E> maybeAt(long index, Iterator<E> iterator) {
        final Iterator<E> filtered = new FilteringIterator<E>(iterator, new AtIndex<E>(index));
        return new MaybeFirstElement<E>().perform(filtered);
    }

    /**
     * Yields element at (0-based) position of the iterable if found or nothing.
     *
     * @param <E> the iterable element type
     * @param index the element index
     * @param iterable the iterable that will be consumed
     * @return just the element or nothing
     */
    public static <E> Maybe<E> maybeAt(long index, Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call maybeAt with a null iterable");
        final Iterator<E> filtered = new FilteringIterator<E>(iterable.iterator(), new AtIndex<E>(index));
        return new MaybeFirstElement<E>().perform(filtered);
    }

    /**
     * Yields element at (0-based) position of the array if found or nothing.
     *
     * @param <E> the array element type
     * @param index the element index
     * @param array the array that will be consumed
     * @return just the element or nothing
     */
    public static <E> Maybe<E> maybeAt(long index, E[] array) {
        final Iterator<E> filtered = new FilteringIterator<E>(new ArrayIterator<E>(array), new AtIndex<E>(index));
        return new MaybeFirstElement<E>().perform(filtered);
    }
}
