package net.emaze.dysfunctional;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.FirstElement;
import net.emaze.dysfunctional.dispatching.delegates.OneElement;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.options.MaybeFirstElement;
import net.emaze.dysfunctional.options.MaybeLastElement;
import net.emaze.dysfunctional.options.MaybeOneElement;

/**
 *
 * @author fdegrassi, rferranti
 */
public abstract class Searches {

    /**
     * Lookups the first matching element returning just element if found,
     * nothing otherwise.
     *
     * @param <E> the element type parameter
     * @param array the array to be searched
     * @param predicate the predicate to be applied to each element
     * @return just the element found or nothing
     */
    public static <E> Maybe<E> search(E[] array, Predicate<E> predicate) {
        return new MaybeFirstElement<E>().perform(Filtering.filter(array, predicate));
    }

    /**
     * Lookups the first matching element returning just element if found,
     * nothing otherwise.
     *
     * @param <E> the element type parameter
     * @param iterator the iterator to be searched
     * @param predicate the predicate to be applied to each element
     * @return just the element found or nothing
     */
    public static <E> Maybe<E> search(Iterator<E> iterator, Predicate<E> predicate) {
        return new MaybeFirstElement<E>().perform(Filtering.filter(iterator, predicate));
    }

    /**
     * Lookups the first matching element returning just element if found,
     * nothing otherwise.
     *
     * @param <E> the element type parameter
     * @param iterable the iterable to be searched
     * @param predicate the predicate to be applied to each element
     * @return just the element found or nothing
     */
    public static <E> Maybe<E> search(Iterable<E> iterable, Predicate<E> predicate) {
        return new MaybeFirstElement<E>().perform(Filtering.filter(iterable, predicate));
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
    public static <E> E find(Iterator<E> iterator, Predicate<E> predicate) {
        return new FirstElement<E>().perform(Filtering.filter(iterator, predicate));
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
    public static <E> E find(Iterable<E> iterable, Predicate<E> predicate) {
        return new FirstElement<E>().perform(Filtering.filter(iterable, predicate));
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
    public static <E> E find(E[] array, Predicate<E> predicate) {
        return new FirstElement<E>().perform(Filtering.filter(array, predicate));
    }

    /**
     * Lookups the only matching element returning just element if found,
     * nothing otherwise.
     *
     * @param <E> the element type parameter
     * @param iterator the iterator to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalStateException if more than one element is found
     * @return just the element found or nothing
     */
    public static <E> Maybe<E> searchOne(Iterator<E> iterator, Predicate<E> predicate) {
        return new MaybeOneElement<E>().perform(Filtering.filter(iterator, predicate));
    }

    /**
     * Lookups the only matching element returning just element if found,
     * nothing otherwise.
     *
     * @param <E> the element type parameter
     * @param iterable the iterable to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalStateException if more than one element is found
     * @return just the element found or nothing
     */
    public static <E> Maybe<E> searchOne(Iterable<E> iterable, Predicate<E> predicate) {
        return new MaybeOneElement<E>().perform(Filtering.filter(iterable, predicate));
    }

    /**
     * Lookups the only matching element returning just element if found,
     * nothing otherwise.
     *
     * @param <E> the element type parameter
     * @param array the array to be searched
     * @param predicate the predicate to be applied to each element
     * @throws IllegalStateException if more than one element is found
     * @return just the element found or nothing
     */
    public static <E> Maybe<E> searchOne(E[] array, Predicate<E> predicate) {
        return new MaybeOneElement<E>().perform(Filtering.filter(array, predicate));
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
    public static <E> E findOne(Iterator<E> iterator, Predicate<E> predicate) {
        return new OneElement<E>().perform(Filtering.filter(iterator, predicate));
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
    public static <E> E findOne(Iterable<E> iterable, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot call findOne with a null iterable");
        return new OneElement<E>().perform(Filtering.filter(iterable, predicate));
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
    public static <E> E findOne(E[] array, Predicate<E> predicate) {
        return new OneElement<E>().perform(Filtering.filter(array, predicate));
    }

    /**
     *
     * @param <E>
     * @param iterator
     * @return
     */
    public static <E> Maybe<E> searchLast(Iterator<E> iterator, Predicate<E> predicate) {
        return new MaybeLastElement<E>().perform(Filtering.filter(iterator, predicate));
    }

    /**
     *
     * @param <E>
     * @param iterable
     * @return
     */
    public static <E> Maybe<E> searchLast(Iterable<E> iterable, Predicate<E> predicate) {
        return new MaybeLastElement<E>().perform(Filtering.filter(iterable, predicate));
    }

    /**
     *
     * @param <T>
     * @param array
     * @return
     */
    public static <E> Maybe<E> searchLast(E[] array, Predicate<E> predicate) {
        return new MaybeLastElement<E>().perform(Filtering.filter(array, predicate));
    }
}
