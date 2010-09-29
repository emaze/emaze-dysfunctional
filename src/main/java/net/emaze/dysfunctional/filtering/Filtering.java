package net.emaze.dysfunctional.filtering;

import java.util.Iterator;
import net.emaze.dysfunctional.delegates.Predicate;
import net.emaze.dysfunctional.iterations.ArrayIterator;

/**
 * @author rferranti
 */
public class Filtering {

    /**
     * filters an iterator returning matching elements
     * @param iterator the iterator where elements are fetched from
     * @param predicate the predicate applied to each element
     * @return an Iterator<E> containing the elements for which the predicate evaluates to true
     */
    public static <E> Iterator<E> filter(Iterator<E> iterator, Predicate<E> predicate) {
        return new FilteringIterator(iterator, predicate);
    }

    /**
     * filters an iterable returning matching elements
     * @param iterable the iterable where elements are fetched from
     * @param predicate the predicate applied to each element
     * @return an Iterator<E> containing the elements for which the predicate evaluates to true
     */
    public static <E> Iterator<E> filter(Iterable<E> iterable, Predicate<E> predicate) {
        return filter(iterable.iterator(), predicate);
    }

    /**
     * filters an array returning matching elements
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
     * @param from
     * @param predicate
     * @return first element from the iterator (throws IllegalArgumentException if the iterator is empty)
     */
    public static <E> Iterator<E> first(long howMany, Iterator<E> from) {
        return take(howMany,from);
    }

    /**
     * fetches the first element from an iterable, throwing an IllegalArgumentException if no element
     * can be fetched
     * @param from
     * @param predicate
     * @return first element from the iterable (throws IllegalArgumentException if the iterable is empty)
     */
    public static <E> Iterator<E> first(long howMany, Iterable<E> from) {
        return first(howMany, from.iterator());
    }

    /**
     * fetches the first element from an array, throwing an IllegalArgumentException if no element
     * can be fetched
     * @param from
     * @param predicate
     * @return first element from the array (throws IllegalArgumentException if the array is empty)
     */
    public static <E> Iterator<E> first(long howMany, E[] from) {
        return first(howMany, new ArrayIterator<E>(from));
    }

    /**
     * fetches the last element from an iterable, throwing an IllegalArgumentException if no element
     * can be fetched
     * @param from
     * @param predicate
     * @return last element of the iterable (throws IllegalArgumentException if the iterable is empty)
     */
    public static <E> Iterator<E> last(int howMany, Iterable<E> from) {
        return last(howMany, from.iterator());
    }

    /**
     * fetches the last element from an iterator, throwing an IllegalArgumentException
     * if no element matches
     * @param from
     * @param predicate
     * @return last matching element from the iterator (throws IllegalArgumentException if not found)
     */
    public static <E> Iterator<E> last(int howMany, Iterator<E> from) {
        return new MemoryIterator<E>(from, howMany);
    }

    /**
     * fetches the last element from an array, throwing an IllegalArgumentException
     * if no element matches
     * @param from
     * @param predicate
     * @return last matching element from the iterator (throws IllegalArgumentException if not found)
     */
    public static <E> Iterator<E> last(int howMany, E[] from) {
        return last(howMany, new ArrayIterator<E>(from));
    }

    public static <E> Iterator<E> takeWhile(Iterator<E> iterator, Predicate<E> predicate) {
        return new TakeWhileIterator<E>(iterator, predicate);
    }

    public static <E> Iterator<E> dropWhile(Iterator<E> iterator, Predicate<E> predicate) {
        return new DropWhileIterator<E>(iterator, predicate);
    }
    
    public static <E> Iterator<E> take(long howMany, Iterator<E> iterator) {
        return new TakeWhileIterator<E>(iterator, new UntilCount<E>(howMany));
    }

    public static <E> Iterator<E> drop(long howMany, Iterator<E> iterator) {
        return new DropWhileIterator<E>(iterator, new UntilCount<E>(howMany));
    }
}
