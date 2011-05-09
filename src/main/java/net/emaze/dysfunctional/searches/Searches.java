package net.emaze.dysfunctional.searches;

import java.util.Iterator;
import net.emaze.dysfunctional.adapting.ArrayIterator;
import net.emaze.dysfunctional.consumers.Consumers;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.filtering.Filtering;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.options.MaybeIterator;

/**
 *
 * @author fdegrassi, rferranti
 */
public abstract class Searches {

    /**
     * Lookups the first matching element returning just element if found,
     * nothing otherwise.
     * @param <E> the element type parameter
     * @param iterator the iterator to be searched
     * @param predicate the predicate to be applied to each element
     * @return just the element found or nothing
     */
    public static <E> Maybe<E> search(Iterator<E> iterator, Predicate<E> predicate) {
        return searchFirst(Filtering.filter(iterator, predicate));
    }

    /**
     * Lookups the first matching element returning just element if found,
     * nothing otherwise.
     * @param <E> the element type parameter
     * @param iterable the iterable to be searched
     * @param predicate the predicate to be applied to each element
     * @return just the element found or nothing
     */
    public static <E> Maybe<E> search(Iterable<E> iterable, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot call search with a null iterable");
        return search(iterable.iterator(), predicate);
    }

    /**
     * Lookups the first matching element returning just element if found,
     * nothing otherwise.
     * @param <E> the element type parameter
     * @param array the array to be searched
     * @param predicate the predicate to be applied to each element
     * @return just the element found or nothing
     */
    public static <E> Maybe<E> search(E[] array, Predicate<E> predicate) {
        return search(new ArrayIterator<E>(array), predicate);
    }

    /**
     * Lookups the first matching element returning it.
     * @param <E> the element type parameter
     * @param iterator the iterator to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalArgumentException if no element matches
     * @return the found element
     */
    public static <E> E find(Iterator<E> iterator, Predicate<E> predicate) {
        final Iterator<E> filter = Filtering.filter(iterator, predicate);
        dbc.precondition(filter.hasNext(), "no element matches the given predicate");
        return Consumers.first(filter);
    }

    /**
     * Lookups the first matching element returning it.
     * @param <E> the element type parameter
     * @param iterable the iterable to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalArgumentException if no element matches
     * @return the found element
     */
    public static <E> E find(Iterable<E> iterable, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot call find with a null iterable");
        return find(iterable.iterator(), predicate);
    }

    /**
     * Lookups the first matching element returning it.
     * @param <E> the element type parameter
     * @param array the array to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalArgumentException if no element matches
     * @return the found element
     */
    public static <E> E find(E[] array, Predicate<E> predicate) {
        return find(new ArrayIterator<E>(array), predicate);
    }

    /**
     * Lookups the only matching element returning just element if found,
     * nothing otherwise.
     * @param <E> the element type parameter
     * @param iterator the iterator to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalStateException if more than one element is found
     * @return just the element found or nothing
     */
    public static <E> Maybe<E> searchOne(Iterator<E> iterator, Predicate<E> predicate) {
        return searchOne(Filtering.filter(iterator, predicate));
    }

    /**
     * Lookups the only matching element returning just element if found,
     * nothing otherwise.
     * @param <E> the element type parameter
     * @param iterable the iterable to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalStateException if more than one element is found
     * @return just the element found or nothing
     */
    public static <E> Maybe<E> searchOne(Iterable<E> iterable, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot call searchOne with a null iterable");
        return searchOne(iterable.iterator(), predicate);
    }

    /**
     * Lookups the only matching element returning just element if found,
     * nothing otherwise.
     * @param <E> the element type parameter
     * @param array the array to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalStateException if more than one element is found
     * @return just the element found or nothing
     */
    public static <E> Maybe<E> searchOne(E[] array, Predicate<E> predicate) {
        return searchOne(new ArrayIterator<E>(array), predicate);
    }

    /**
     * Lookups the only matching element returning it.
     * @param <E> the element type parameter
     * @param iterator the iterator to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalStateException if more than one element is found
     * @throws IllegalArgumentException if no element matches
     * @return the found element
     */
    public static <E> E findOne(Iterator<E> iterator, Predicate<E> predicate) {
        return Consumers.one(Filtering.filter(iterator, predicate));
    }

    /**
     * Lookups the only matching element returning it.
     * @param <E> the element type parameter
     * @param iterable the iterable to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalStateException if more than one element is found
     * @throws IllegalArgumentException if no element matches
     * @return the found element
     */
    public static <E> E findOne(Iterable<E> iterable, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot call findOne with a null iterable");
        return findOne(iterable.iterator(), predicate);
    }

    /**
     * Lookups the only matching element returning it.
     * @param <E> the element type parameter
     * @param array the array to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalStateException if more than one element is found
     * @throws IllegalArgumentException if no element matches
     * @return the found element
     */
    public static <E> E findOne(E[] array, Predicate<E> predicate) {
        return findOne(new ArrayIterator<E>(array), predicate);
    }

    /**
     * yields the first element if found, nothing otherwise.
     * @param <E>
     * @param iterator
     * @return just the first element or nothing
     */
    public static <E> Maybe<E> searchFirst(Iterator<E> iterator) {
        return new MaybeIterator<E>(iterator).next();
    }

    /**
     * yields the first element if found, nothing otherwise.
     * @param <E>
     * @param iterable
     * @return
     */
    public static <E> Maybe<E> searchFirst(Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call maybeFirst with a null iterable");
        return searchFirst(iterable.iterator());
    }

    /**
     * yields the first element if found, nothing otherwise.
     * @param <E>
     * @param array
     * @return
     */
    public static <E> Maybe<E> searchFirst(E[] array) {
        return searchFirst(new ArrayIterator<E>(array));
    }

    /**
     * yields the only element if found, nothing otherwise.
     * @param <E>
     * @param iterator
     * @throws IllegalStateException if the iterator contains more than one element
     * @return
     */
    public static <E> Maybe<E> searchOne(Iterator<E> iterator) {
        Iterator<Maybe<E>> maybeIter = new MaybeIterator<E>(iterator);
        final Maybe<E> element = maybeIter.next();
        dbc.stateprecondition(!maybeIter.hasNext(), "Expected only one value in iterator");
        return element;
    }

    /**
     * yields the only element if found, nothing otherwise.
     * @param <E>
     * @param iterable
     * @throws IllegalStateException if the iterator contains more than one element
     * @return
     */
    public static <E> Maybe<E> searchOne(Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call searchOne with a null iterable");
        return searchOne(iterable.iterator());
    }

    /**
     * yields the only element if found, nothing otherwise.
     * @param <E>
     * @param array
     * @throws IllegalStateException if the iterator contains more than one element
     * @return
     */
    public static <E> Maybe<E> searchOne(E[] array) {
        return searchOne(new ArrayIterator<E>(array));
    }

    /**
     *
     * @param <T>
     * @param iterator
     * @return
     */
    public static <T> Maybe<T> searchLast(Iterator<T> iterator) {
        dbc.precondition(iterator != null, "cannot call searchLast with a null iterator");
        if (iterator.hasNext()) {
            return Maybe.just(Consumers.last(iterator));
        }
        return Maybe.nothing();
    }

    /**
     *
     * @param <T>
     * @param iterable
     * @return
     */
    public static <T> Maybe<T> searchLast(Iterable<T> iterable) {
        dbc.precondition(iterable != null, "cannot call searchLast with a null iterable");
        return searchLast(iterable.iterator());
    }

    /**
     *
     * @param <T>
     * @param array
     * @return
     */
    public static <T> Maybe<T> searchLast(T[] array) {
        return searchLast(new ArrayIterator<T>(array));
    }
}
