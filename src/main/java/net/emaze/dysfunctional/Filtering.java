package net.emaze.dysfunctional;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.filtering.AtMostMemoryIterator;
import net.emaze.dysfunctional.filtering.DropWhileIterator;
import net.emaze.dysfunctional.filtering.FilteringIterator;
import net.emaze.dysfunctional.filtering.MemoryIterator;
import net.emaze.dysfunctional.filtering.TakeUpToIterator;
import net.emaze.dysfunctional.filtering.TakeWhileIterator;
import net.emaze.dysfunctional.filtering.UntilCount;
import net.emaze.dysfunctional.iterations.ArrayIterator;

/**
 * @author rferranti
 */
public abstract class Filtering {

    /**
     * Creates an iterator yielding elements from the source iterator matching
     * the given predicate. This transformation is applied lazily and the
     * predicate is applied while consuming returned iterator. E.g:
     * <code>filter([1,2,3,4], isEven) -> [2,4]</code>
     *
     * @param <E> the iterator element type
     * @param iterator the iterator where elements are fetched from
     * @param predicate the predicate applied to each element
     * @return an iterator containing the elements for which the predicate
     * evaluates to true
     */
    public static <E> Iterator<E> filter(Iterator<E> iterator, Predicate<E> predicate) {
        return new FilteringIterator<E>(iterator, predicate);
    }

    /**
     * Creates an iterator yielding elements from the source iterable matching
     * the given predicate. This transformation is applied lazily and the
     * predicate is applied while consuming returned iterator. E.g:
     * <code>filter([1,2,3,4], isEven) -> [2,4]</code>
     *
     * @param <E> the iterable element type
     * @param iterable the iterable where elements are fetched from
     * @param predicate the predicate applied to each element
     * @return an iterator containing the elements for which the predicate
     * evaluates to true
     */
    public static <E> Iterator<E> filter(Iterable<E> iterable, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot call filter with a null iterable");
        return filter(iterable.iterator(), predicate);
    }

    /**
     * Creates an iterator yielding elements from the source array matching the
     * given predicate. This transformation is applied lazily and the predicate
     * is applied while consuming returned iterator. E.g:
     * <code>filter([1,2,3,4], isEven) -> [2,4]</code>
     *
     * @param <E> the array element type
     * @param array the array where elements are fetched from
     * @param predicate the predicate applied to each element
     * @return an Iterator<E> containing the elements for which the predicate
     * evaluates to true
     */
    public static <E> Iterator<E> filter(E[] array, Predicate<E> predicate) {
        return filter(new ArrayIterator<E>(array), predicate);
    }

    /**
     * fetches the last element from an iterable, throwing an
     * IllegalArgumentException if no element can be fetched
     *
     * @param <E>
     * @param howMany
     * @param from
     * @return last element of the iterable (throws IllegalArgumentException if
     * the iterable is empty)
     */
    public static <E> Iterator<E> takeLast(int howMany, Iterable<E> from) {
        dbc.precondition(from != null, "cannot call last with a null iterable");
        return takeLast(howMany, from.iterator());
    }

    /**
     * fetches the last element from an iterator, throwing an
     * IllegalArgumentException if no element matches
     *
     * @param <E>
     * @param howMany
     * @param from
     * @return last matching element from the iterator (throws
     * IllegalArgumentException if not enough elements are found)
     */
    public static <E> Iterator<E> takeLast(int howMany, Iterator<E> from) {
        return new MemoryIterator<E>(from, howMany);
    }

    /**
     * fetches the last element from an array, throwing an
     * IllegalArgumentException if no element matches
     *
     * @param <E>
     * @param howMany
     * @param from
     * @return last matching element from the iterator (throws
     * IllegalArgumentException if not enough elements are found)
     */
    public static <E> Iterator<E> takeLast(int howMany, E[] from) {
        return takeLast(howMany, new ArrayIterator<E>(from));
    }

    /**
     * Fetches at most last elements from an iterable.
     *
     * @param <E>
     * @param howMany
     * @param from
     * @return
     */
    public static <E> Iterator<E> atMostLast(int howMany, Iterable<E> from) {
        dbc.precondition(from != null, "cannot call atMostLast with a null iterable");
        return atMostLast(howMany, from.iterator());
    }

    /**
     * Fetches at most last elements from an iterator.
     *
     * @param <E>
     * @param howMany
     * @param from
     * @return
     */
    public static <E> Iterator<E> atMostLast(int howMany, Iterator<E> from) {
        return new AtMostMemoryIterator<E>(from, howMany);
    }

    /**
     * Fetches at most last elements from an array.
     *
     * @param <E>
     * @param howMany
     * @param from
     * @return
     */
    public static <E> Iterator<E> atMostLast(int howMany, E[] from) {
        return atMostLast(howMany, new ArrayIterator<E>(from));
    }

    /**
     *
     * @param <E>
     * @param iterator
     * @param predicate
     * @return
     */
    public static <E> Iterator<E> takeWhile(Iterator<E> iterator, Predicate<E> predicate) {
        return new TakeWhileIterator<E>(iterator, predicate);
    }

    /**
     *
     * @param <E>
     * @param iterable
     * @param predicate
     * @return
     */
    public static <E> Iterator<E> takeWhile(Iterable<E> iterable, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot takeWhile from a null iterable");
        return new TakeWhileIterator<E>(iterable.iterator(), predicate);
    }

    /**
     *
     * @param <E>
     * @param iterator
     * @param predicate
     * @return
     */
    public static <E> Iterator<E> takeWhile(E[] array, Predicate<E> predicate) {
        return new TakeWhileIterator<E>(new ArrayIterator<E>(array), predicate);
    }

    /**
     *
     * @param <E>
     * @param iterator
     * @param predicate
     * @return
     */
    public static <E> Iterator<E> dropWhile(Iterator<E> iterator, Predicate<E> predicate) {
        return new DropWhileIterator<E>(iterator, predicate);
    }

    /**
     *
     * @param <E>
     * @param iterable
     * @param predicate
     * @return
     */
    public static <E> Iterator<E> dropWhile(Iterable<E> iterable, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot dropWhile from a null iterable");
        return new DropWhileIterator<E>(iterable.iterator(), predicate);
    }

    /**
     *
     * @param <E>
     * @param iterable
     * @param predicate
     * @return
     */
    public static <E> Iterator<E> dropWhile(E[] array, Predicate<E> predicate) {
        return new DropWhileIterator<E>(new ArrayIterator<E>(array), predicate);
    }

    /**
     * Creates an iterator showing first elements of the passed iterator.
     *
     * @param <E>
     * @param howMany
     * @param iterator
     * @return
     */
    public static <E> Iterator<E> take(long howMany, Iterator<E> iterator) {
        return new TakeUpToIterator<E>(iterator, howMany);
    }

    /**
     * Creates an iterator showing first elements of the passed iterable.
     *
     * @param <E>
     * @param howMany
     * @param iterable
     * @return
     */
    public static <E> Iterator<E> take(long howMany, Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot take from a null iterable");
        return new TakeUpToIterator<E>(iterable.iterator(), howMany);
    }

    /**
     * Creates an iterator showing first elements of the passed array.
     *
     * @param <E>
     * @param howMany
     * @param iterator
     * @return
     */
    public static <E> Iterator<E> take(long howMany, E... array) {
        return new TakeUpToIterator<E>(new ArrayIterator<E>(array), howMany);
    }

    /**
     * Creates an iterator not showing first elements of the passed iterator.
     *
     * @param <E>
     * @param howMany
     * @param iterator
     * @return
     */
    public static <E> Iterator<E> drop(long howMany, Iterator<E> iterator) {
        return new DropWhileIterator<E>(iterator, new UntilCount<E>(howMany));
    }

    /**
     * Creates an iterator not showing first elements of the passed iterable.
     *
     * @param <E>
     * @param howMany
     * @param iterable
     * @return
     */
    public static <E> Iterator<E> drop(long howMany, Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot drop from a null iterable");
        return new DropWhileIterator<E>(iterable.iterator(), new UntilCount<E>(howMany));
    }

    /**
     * Creates an iterator not showing first elements of the passed array.
     *
     * @param <E>
     * @param howMany
     * @param iterator
     * @return
     */
    public static <E> Iterator<E> drop(long howMany, E... array) {
        return new DropWhileIterator<E>(new ArrayIterator<E>(array), new UntilCount<E>(howMany));
    }

    /**
     * Creates an iterator showing a slice of the passed iterator.
     *
     * @param <E>
     * @param from
     * @param howMany
     * @param iterator
     * @return
     */
    public static <E> Iterator<E> slice(long from, long howMany, Iterator<E> iterator) {
        return take(howMany, drop(from, iterator));
    }

    /**
     * Creates an iterator showing a slice of the passed iterator.
     *
     * @param <E>
     * @param from
     * @param howMany
     * @param iterable
     * @return
     */
    public static <E> Iterator<E> slice(long from, long howMany, Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call slice with a null iterable");
        return take(howMany, drop(from, iterable.iterator()));
    }

    /**
     * Creates an iterator showing a slice of the passed array.
     *
     * @param <E>
     * @param from
     * @param howMany
     * @param array
     * @return
     */
    public static <E> Iterator<E> slice(long from, long howMany, E[] array) {
        return take(howMany, drop(from, new ArrayIterator<E>(array)));
    }
}
