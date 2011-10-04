package net.emaze.dysfunctional.filtering;

import java.util.Iterator;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.adapting.ArrayIterator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * @author rferranti
 */
public abstract class Filtering {

    /**
     * filters an iterator returning matching elements
     * @param <E> 
     * @param iterator the iterator where elements are fetched from
     * @param predicate the predicate applied to each element
     * @return an Iterator<E> containing the elements for which the predicate evaluates to true
     */
    public static <E> Iterator<E> filter(Iterator<E> iterator, Predicate<E> predicate) {
        return new FilteringIterator<E>(iterator, predicate);
    }

    /**
     * filters an iterable returning matching elements
     * @param <E> 
     * @param iterable the iterable where elements are fetched from
     * @param predicate the predicate applied to each element
     * @return an Iterator<E> containing the elements for which the predicate evaluates to true
     */
    public static <E> Iterator<E> filter(Iterable<E> iterable, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot call filter with a null iterable");
        return filter(iterable.iterator(), predicate);
    }

    /**
     * filters an array returning matching elements
     * @param <E>
     * @param array the array where elements are fetched from
     * @param predicate the predicate applied to each element
     * @return an Iterator<E> containing the elements for which the predicate evaluates to true
     */
    public static <E> Iterator<E> filter(E[] array, Predicate<E> predicate) {
        return filter(new ArrayIterator<E>(array), predicate);
    }

    /**
     * fetches the first element from an iterator, throwing an IllegalArgumentException if no element
     * can be fetched
     * @param <E>
     * @param howMany
     * @param from
     * @return first element from the iterator (throws IllegalArgumentException if the iterator is empty)
     */
    public static <E> Iterator<E> first(long howMany, Iterator<E> from) {
        return take(howMany, from);
    }

    /**
     * fetches the first element from an iterable, throwing an IllegalArgumentException if no element
     * can be fetched
     * @param <E>
     * @param howMany
     * @param from
     * @return first element from the iterable (throws IllegalArgumentException if the iterable is empty)
     */
    public static <E> Iterator<E> first(long howMany, Iterable<E> from) {
        dbc.precondition(from != null, "cannot call first with a null iterable");
        return first(howMany, from.iterator());
    }

    /**
     * fetches the first element from an array, throwing an IllegalArgumentException if no element
     * can be fetched
     * @param <E>
     * @param howMany
     * @param from
     * @return first element from the array (throws IllegalArgumentException if the array is empty)
     */
    public static <E> Iterator<E> first(long howMany, E[] from) {
        return first(howMany, new ArrayIterator<E>(from));
    }

    /**
     * fetches the last element from an iterable, throwing an IllegalArgumentException if no element
     * can be fetched
     * @param <E>
     * @param howMany
     * @param from
     * @return last element of the iterable (throws IllegalArgumentException if the iterable is empty)
     */
    public static <E> Iterator<E> last(int howMany, Iterable<E> from) {
        dbc.precondition(from != null, "cannot call last with a null iterable");
        return last(howMany, from.iterator());
    }

    /**
     * fetches the last element from an iterator, throwing an IllegalArgumentException
     * if no element matches
     * @param <E>
     * @param howMany
     * @param from
     * @return last matching element from the iterator (throws IllegalArgumentException if not found)
     */
    public static <E> Iterator<E> last(int howMany, Iterator<E> from) {
        return new MemoryIterator<E>(from, howMany);
    }

    /**
     * fetches the last element from an array, throwing an IllegalArgumentException
     * if no element matches
     * @param <E>
     * @param howMany
     * @param from
     * @return last matching element from the iterator (throws IllegalArgumentException if not found)
     */
    public static <E> Iterator<E> last(int howMany, E[] from) {
        return last(howMany, new ArrayIterator<E>(from));
    }

    public static <E> Iterator<E> atMostLast(int howMany, Iterable<E> from) {
        dbc.precondition(from != null, "cannot call atMostLast with a null iterable");
        return atMostLast(howMany, from.iterator());
    }

    public static <E> Iterator<E> atMostLast(int howMany, Iterator<E> from) {
        return new AtMostMemoryIterator<E>(from, howMany);
    }

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
     * @param howMany
     * @param iterator
     * @return
     */
    public static <E> Iterator<E> take(long howMany, Iterator<E> iterator) {
        return new TakeUpToIterator<E>(iterator, howMany);
    }

    /**
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
     * yields nth (1-based) element of the iterator
     * @param <E>
     * @param count
     * @param iterator
     * @return
     */
    public static <E> Iterator<E> nth(long count, Iterator<E> iterator) {
        return filter(iterator, new Nth<E>(count));
    }

    /**
     * yields nth (1-based) element of the iterable's iterator
     * @param <E>
     * @param count
     * @param iterable
     * @return
     */
    public static <E> Iterator<E> nth(long count, Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call nth with a null iterable");
        return nth(count, iterable.iterator());
    }

    /**
     * yields nth (1-based) element of the array
     * @param <E>
     * @param count
     * @param array
     * @return
     */
    public static <E> Iterator<E> nth(long count, E[] array) {
        return nth(count, new ArrayIterator<E>(array));
    }

    /**
     * yields element at (0-based) position of the iterator
     * @param <E>
     * @param index
     * @param iterator
     * @return
     */
    public static <E> Iterator<E> at(long index, Iterator<E> iterator) {
        return filter(iterator, new AtIndex<E>(index));
    }

    /**
     * yields element at (0-based) position of the iterable's iterator
     * @param <E>
     * @param index
     * @param iterable
     * @return
     */
    public static <E> Iterator<E> at(long index, Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call at with a null iterable");
        return at(index, iterable.iterator());
    }

    /**
     * yields element at (0-based) position of the array
     * @param <E>
     * @param index
     * @param array
     * @return
     */
    public static <E> Iterator<E> at(long index, E[] array) {
        return at(index, new ArrayIterator<E>(array));
    }

    /**
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
