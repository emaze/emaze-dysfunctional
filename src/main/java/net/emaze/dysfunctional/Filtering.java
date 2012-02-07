package net.emaze.dysfunctional;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.filtering.AtMostMemoryIterator;
import net.emaze.dysfunctional.filtering.DropWhile;
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
     * Creates an iterator yielding last n elements from the source iterable.
     * Consuming the resulting iterator yields an IllegalArgumentException if
     * not enough elements can be fetched. E.g:
     * <code>takeLast(2, [1, 2, 3]) -> [2, 3]</code>
     *
     * @param <E> the iterable element type
     * @param howMany number of elements to be yielded
     * @param from the source iterable
     * @return the resulting iterator
     */
    public static <E> Iterator<E> takeLast(int howMany, Iterable<E> from) {
        dbc.precondition(from != null, "cannot call last with a null iterable");
        return takeLast(howMany, from.iterator());
    }

    /**
     * Creates an iterator yielding last n elements from the source iterator.
     * Consuming the resulting iterator yields an IllegalArgumentException if
     * not enough elements can be fetched. E.g:
     * <code>takeLast(2, [1, 2, 3]) -> [2, 3]</code>
     *
     * @param <E> the iterator element type
     * @param howMany number of elements to be yielded
     * @param from the source iterator
     * @return the resulting iterator
     */
    public static <E> Iterator<E> takeLast(int howMany, Iterator<E> from) {
        return new MemoryIterator<E>(from, howMany);
    }

    /**
     * Creates an iterator yielding last n elements from the source array.
     * Consuming the resulting iterator yields an IllegalArgumentException if
     * not enough elements can be fetched. E.g:
     * <code>takeLast(2, [1, 2, 3]) -> [2, 3]</code>
     *
     * @param <E> the array element type
     * @param howMany number of elements to be yielded
     * @param from the source array
     * @return the resulting iterator
     */
    public static <E> Iterator<E> takeLast(int howMany, E[] from) {
        return takeLast(howMany, new ArrayIterator<E>(from));
    }

    /**
     * Creates an iterator yielding at most last n elements from the source
     * iterable. E.g:
     * <code>atMostLast(4, [1, 2, 3]) -> [1, 2, 3]</code>
     * <code>atMostLast(2, [1, 2, 3]) -> [2, 3]</code>
     *
     * @param <E> the iterable element type
     * @param howMany number of elements to be yielded (at most)
     * @param from the source iterable
     * @return the resulting iterator
     */
    public static <E> Iterator<E> atMostLast(int howMany, Iterable<E> from) {
        dbc.precondition(from != null, "cannot call atMostLast with a null iterable");
        return atMostLast(howMany, from.iterator());
    }

    /**
     * Creates an iterator yielding at most last n elements from the source
     * iterator. E.g:
     * <code>atMostLast(4, [1, 2, 3]) -> [1, 2, 3]</code>
     * <code>atMostLast(2, [1, 2, 3]) -> [2, 3]</code>
     *
     * @param <E> the iterator element type
     * @param howMany number of elements to be yielded (at most)
     * @param from the source iterator
     * @return the resulting iterator
     */
    public static <E> Iterator<E> atMostLast(int howMany, Iterator<E> from) {
        return new AtMostMemoryIterator<E>(from, howMany);
    }

    /**
     * Creates an iterator yielding at most last n elements from the source
     * array. E.g:
     * <code>atMostLast(4, [1, 2, 3]) -> [1, 2, 3]</code>
     * <code>atMostLast(2, [1, 2, 3]) -> [2, 3]</code>
     *
     * @param <E> the array element type
     * @param howMany number of elements to be yielded (at most)
     * @param from the source array
     * @return the resulting iterator
     */
    public static <E> Iterator<E> atMostLast(int howMany, E[] from) {
        return atMostLast(howMany, new ArrayIterator<E>(from));
    }

    /**
     * Creates an iterator yielding values from the source iterator up until the
     * passed predicate matches. E.g:
     * <code>takeWhile([2, 4, 3, 6], isEven) -> [2, 4]</code>
     *
     * @param <E> the iterator element type
     * @param iterator the source iterator
     * @param predicate the predicate to be evaluated
     * @return the resulting iterator
     */
    public static <E> Iterator<E> takeWhile(Iterator<E> iterator, Predicate<E> predicate) {
        return new TakeWhileIterator<E>(iterator, predicate);
    }

    /**
     * Creates an iterator yielding values from the source iterable up until the
     * passed predicate matches. E.g:
     * <code>takeWhile([2, 4, 3, 6], isEven) -> [2, 4]</code>
     *
     * @param <E> the iterable element type
     * @param iterable the source iterable
     * @param predicate the predicate to be evaluated
     * @return the resulting iterator
     */
    public static <E> Iterator<E> takeWhile(Iterable<E> iterable, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot takeWhile from a null iterable");
        return new TakeWhileIterator<E>(iterable.iterator(), predicate);
    }

    /**
     * Creates an iterator yielding values from the source array up until the
     * passed predicate matches. E.g:
     * <code>takeWhile([2, 4, 3, 6], isEven) -> [2, 4]</code>
     *
     * @param <E> the array element type
     * @param array the source iterable
     * @param predicate the predicate to be evaluated
     * @return the resulting iterator
     */
    public static <E> Iterator<E> takeWhile(E[] array, Predicate<E> predicate) {
        return new TakeWhileIterator<E>(new ArrayIterator<E>(array), predicate);
    }

    /**
     * Creates an iterator yielding values from the source iterator up until the
     * passed predicate doesn't match. E.g:
     * <code>dropWhile([2, 4, 3, 6], isOdd) -> [2, 4]</code>
     *
     * @param <E> the iterator element type
     * @param iterator the source iterator
     * @param predicate the predicate to be evaluated
     * @return the resulting iterator
     */
    public static <E> Iterator<E> dropWhile(Iterator<E> iterator, Predicate<E> predicate) {
        return new FilteringIterator<E>(iterator, new DropWhile<E>(predicate));
    }

    /**
     * Creates an iterator yielding values from the source iterable up until the
     * passed predicate doesn't match. E.g:
     * <code>dropWhile([2, 4, 3, 6], isOdd) -> [2, 4]</code>
     *
     * @param <E> the iterable element type
     * @param iterable the source iterable
     * @param predicate the predicate to be evaluated
     * @return the resulting iterator
     */
    public static <E> Iterator<E> dropWhile(Iterable<E> iterable, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot dropWhile from a null iterable");
        return new FilteringIterator<E>(iterable.iterator(), new DropWhile<E>(predicate));
    }

    /**
     * Creates an iterator yielding values from the source array up until the
     * passed predicate doesn't match. E.g:
     * <code>dropWhile([2, 4, 3, 6], isOdd) -> [2, 4]</code>
     *
     * @param <E> the array element type
     * @param array the source array
     * @param predicate the predicate to be evaluated
     * @return the resulting iterator
     */
    public static <E> Iterator<E> dropWhile(E[] array, Predicate<E> predicate) {
        return new FilteringIterator<E>(new ArrayIterator<E>(array), new DropWhile<E>(predicate));
    }

    /**
     * Creates an iterator yielding at most first n elements of the passed
     * iterator. E.g:
     * <code>take(2, [1, 2, 3]) -> [1, 2]</code>
     * <code>take(4, [1, 2, 3]) -> [1, 2, 3]</code>
     *
     * @param <E> the iterator element type
     * @param howMany elements to be consumed (at most)
     * @param iterator the source iterator
     * @return the resulting iterator
     */
    public static <E> Iterator<E> take(long howMany, Iterator<E> iterator) {
        return new TakeUpToIterator<E>(iterator, howMany);
    }

    /**
     * Creates an iterator yielding at most first n elements of the passed
     * iterable. E.g:
     * <code>take(2, [1, 2, 3]) -> [1, 2]</code>
     * <code>take(4, [1, 2, 3]) -> [1, 2, 3]</code>
     *
     * @param <E> the iterable element type
     * @param howMany elements to be consumed (at most)
     * @param iterable the source iterable
     * @return the resulting iterator
     */
    public static <E> Iterator<E> take(long howMany, Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot take from a null iterable");
        return new TakeUpToIterator<E>(iterable.iterator(), howMany);
    }

    /**
     * Creates an iterator yielding at most first n elements of the passed
     * array. E.g:
     * <code>take(2, [1, 2, 3]) -> [1, 2]</code>
     * <code>take(4, [1, 2, 3]) -> [1, 2, 3]</code>
     *
     * @param <E> the array element type
     * @param howMany elements to be consumed (at most)
     * @param array the source array
     * @return the resulting iterator
     */
    public static <E> Iterator<E> take(long howMany, E... array) {
        return new TakeUpToIterator<E>(new ArrayIterator<E>(array), howMany);
    }

    /**
     * Creates an iterator yielding all but first n elements from the passed
     * iterator. E.g:
     * <code>drop(3, [1,2,3,4]) -> [4]</code>
     *
     * @param <E> the iterator element type
     * @param howMany elements to be discarded
     * @param iterator the source iterator
     * @return the resulting iterator
     */
    public static <E> Iterator<E> drop(long howMany, Iterator<E> iterator) {
        return new FilteringIterator<E>(iterator, new DropWhile<E>(new UntilCount<E>(howMany)));
    }

    /**
     * Creates an iterator yielding all but first n elements from the passed
     * iterable. E.g:
     * <code>drop(3, [1,2,3,4]) -> [4]</code>
     *
     * @param <E> the iterator element type
     * @param howMany elements to be discarded
     * @param iterable the source iterable
     * @return the resulting iterator
     */
    public static <E> Iterator<E> drop(long howMany, Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot drop from a null iterable");
        return new FilteringIterator<E>(iterable.iterator(), new DropWhile<E>(new UntilCount<E>(howMany)));
    }

    /**
     * Creates an iterator yielding all but first n elements from the passed
     * array. E.g:
     * <code>drop(3, [1,2,3,4]) -> [4]</code>
     *
     * @param <E> the iterator element type
     * @param howMany elements to be discarded
     * @param array the source array
     * @return the resulting iterator
     */
    public static <E> Iterator<E> drop(long howMany, E... array) {
        return new FilteringIterator<E>(new ArrayIterator<E>(array), new DropWhile<E>(new UntilCount<E>(howMany)));
    }

    /**
     * Creates an iterator yielding a slice of the source iterator. E.g:
     * <code>slice(1, 2, ["a", "b", "c", "d"]) ->  ["b", "c"]</code>
     *
     * @param <E> the iterator element type
     * @param from the index at which the slice starts
     * @param howMany the slice size (at most)
     * @param iterator the source iterator
     * @return the resulting iterator
     */
    public static <E> Iterator<E> slice(long from, long howMany, Iterator<E> iterator) {
        final Iterator<E> dropping = new FilteringIterator<E>(iterator, new DropWhile<E>(new UntilCount<E>(from)));
        return new TakeUpToIterator<E>(dropping, howMany);
    }

    /**
     * Creates an iterator yielding a slice of the source iterable. E.g:
     * <code>slice(1, 2, ["a", "b", "c", "d"]) ->  ["b", "c"]</code>
     *
     * @param <E> the iterator element type
     * @param from the index at which the slice starts
     * @param howMany the slice size (at most)
     * @param iterable the source iterable
     * @return the resulting iterator
     */
    public static <E> Iterator<E> slice(long from, long howMany, Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call slice with a null iterable");
        final Iterator<E> dropping = new FilteringIterator<E>(iterable.iterator(), new DropWhile<E>(new UntilCount<E>(from)));
        return new TakeUpToIterator<E>(dropping, howMany);
    }

    /**
     * Creates an iterator yielding a slice of the source array. E.g:
     * <code>slice(1, 2, ["a", "b", "c", "d"]) ->  ["b", "c"]</code>
     *
     * @param <E> the iterator element type
     * @param from the index at which the slice starts
     * @param howMany the slice size (at most)
     * @param array the source array
     * @return the resulting iterator
     */
    public static <E> Iterator<E> slice(long from, long howMany, E... array) {
        final Iterator<E> dropping = new FilteringIterator<E>(ArrayIterator.of(array), new DropWhile<E>(new UntilCount<E>(from)));
        return new TakeUpToIterator<E>(dropping, howMany);

    }
}
