package net.emaze.dysfunctional.filtering;

import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.consumers.EagerConsumer;
import net.emaze.dysfunctional.consumers.GiveUpConsumer;
import net.emaze.dysfunctional.consumers.StubbornConsumer;
import net.emaze.dysfunctional.delegates.Predicate;
import net.emaze.dysfunctional.iterations.ArrayIterator;

/**
 * TODO: FIXME: decouple consumption strategy from filtering. We should be much more lazy
 * @author rferranti
 */
public class Filtering {

    /**
     * filters an iterable returning matching elements
     * @param iterable the iterable where elements are fetched from
     * @param predicate the predicate applied to each element
     * @return a List<R> containing the elements for which the predicate evaluates to true
     */
    public static <E> List<E> some(Iterable<E> iterable, Predicate<E> predicate) {
        return some(iterable.iterator(), predicate);
    }

    /**
     * filters an iterator returning matching elements
     * @param iterator the iterator where elements are fetched from
     * @param predicate the predicate applied to each element
     * @return a List<R> containing the elements for which the predicate evaluates to true
     */
    public static <E> List<E> some(Iterator<E> iterator, Predicate<E> predicate) {
        return new EagerConsumer().consume(new FilteringIterator(iterator, predicate));
    }

    /**
     * filters an array returning matching elements
     * @param array the array where elements are fetched from
     * @param predicate the predicate applied to each element
     * @return a List<R> containing the elements for which the predicate evaluates to true
     */
    public static <E> List<E> some(E[] array, Predicate<E> predicate) {
        return some(new ArrayIterator<E>(array), predicate);
    }

    /**
     * fetches the first matching element from an iterable, throwing an IllegalArgumentException
     * if no element matches
     * @param iterable
     * @param predicate
     * @return first matching element from the iterable (throws IllegalArgumentException if not found)
     */
    public static <E> E first(Iterable<E> iterable, Predicate<E> predicate) {
        return first(iterable.iterator(), predicate);
    }

    /**
     * fetches the first matching element from an iterator, throwing an IllegalArgumentException
     * if no element matches
     * @param iterator
     * @param predicate
     * @return first matching element from the iterator (throws IllegalArgumentException if not found)
     */
    public static <E> E first(Iterator<E> iterator, Predicate<E> predicate) {
        return new GiveUpConsumer<E>().consume(new FilteringIterator(iterator, predicate));
    }

    /**
     * fetches the first matching element from an array, throwing an IllegalArgumentException
     * if no element matches
     * @param array
     * @param predicate
     * @return first matching element from the array (throws IllegalArgumentException if not found)
     */
    public static <E> E first(E[] array, Predicate<E> predicate) {
        return first(new ArrayIterator<E>(array));
    }

    /**
     * fetches the first element from an iterable, throwing an IllegalArgumentException if no element
     * can be fetched
     * @param iterable
     * @param predicate
     * @return first element from the iterable (throws IllegalArgumentException if the iterable is empty)
     */
    public static <E> E first(Iterable<E> iterable) {
        return first(iterable.iterator());
    }

    /**
     * fetches the first element from an iterator, throwing an IllegalArgumentException if no element
     * can be fetched
     * @param iterator
     * @param predicate
     * @return first element from the iterator (throws IllegalArgumentException if the iterator is empty)
     */
    public static <E> E first(Iterator<E> iterator) {
        return new GiveUpConsumer<E>().consume(iterator);
    }

    /**
     * fetches the first element from an array, throwing an IllegalArgumentException if no element
     * can be fetched
     * @param array
     * @param predicate
     * @return first element from the array (throws IllegalArgumentException if the array is empty)
     */
    public static <E> E first(E[] array) {
        return first(new ArrayIterator<E>(array));
    }

    /**
     * fetches the last matching element from an iterable, throwing an IllegalArgumentException
     * if no element matches
     * @param iterable
     * @param predicate
     * @return last matching element from the iterable (throws IllegalArgumentException if not found)
     */
    public static <E> E last(Iterable<E> iterable, Predicate<E> predicate) {
        return last(iterable.iterator(), predicate);
    }

    /**
     * fetches the last matching element from an iterator, throwing an IllegalArgumentException
     * if no element matches
     * @param iterator
     * @param predicate
     * @return last matching element from the iterator (throws IllegalArgumentException if not found)
     */
    public static <E> E last(Iterator<E> iterator, Predicate<E> predicate) {
        return new StubbornConsumer<E>().consume(new FilteringIterator(iterator, predicate));
    }

    /**
     * fetches the last matching element from an array, throwing an IllegalArgumentException
     * if no element matches
     * @param array
     * @param predicate
     * @return last matching element from the iterator (throws IllegalArgumentException if not found)
     */
    public static <E> E last(E[] array, Predicate<E> predicate) {
        return last(new ArrayIterator<E>(array), predicate);
    }

    /**
     * fetches the last element from an iterable, throwing an IllegalArgumentException if no element
     * can be fetched
     * @param iterable
     * @param predicate
     * @return last element of the iterable (throws IllegalArgumentException if the iterable is empty)
     */
    public static <E> E last(Iterable<E> iterable) {
        return last(iterable.iterator());
    }

    /**
     * fetches the last element from an iterator, throwing an IllegalArgumentException
     * if no element matches
     * @param iterator
     * @param predicate
     * @return last matching element from the iterator (throws IllegalArgumentException if not found)
     */
    public static <E> E last(Iterator<E> iterator) {
        return new StubbornConsumer<E>().consume(iterator);
    }

    /**
     * fetches the last element from an array, throwing an IllegalArgumentException
     * if no element matches
     * @param array
     * @param predicate
     * @return last matching element from the iterator (throws IllegalArgumentException if not found)
     */
    public static <E> E last(E[] array) {
        return last(new ArrayIterator<E>(array));
    }

    public static <E> Iterator<E> takeWhile(Iterator<E> iterator, Predicate<E> predicate) {
        return new TakeWhileIterator<E>(iterator, predicate);
    }

    public static <E> Iterator<E> dropWhile(Iterator<E> iterator, Predicate<E> predicate) {
        return new DropWhileIterator<E>(iterator, predicate);
    }
    
    public static <E> Iterator<E> take(Iterator<E> iterator, long howMany) {
        return new TakeWhileIterator<E>(iterator, new UntilCount<E>(howMany));
    }

    public static <E> Iterator<E> drop(Iterator<E> iterator, long howMany) {
        return new DropWhileIterator<E>(iterator, new UntilCount<E>(howMany));
    }
}
