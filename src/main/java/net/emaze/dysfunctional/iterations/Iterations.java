package net.emaze.dysfunctional.iterations;

import java.util.Iterator;
import net.emaze.dysfunctional.consumers.EagerConsumer;
import java.util.List;
import net.emaze.dysfunctional.consumers.GiveUpConsumer;
import net.emaze.dysfunctional.consumers.StubbornConsumer;
import net.emaze.dysfunctional.delegates.Action;
import net.emaze.dysfunctional.delegates.Delegate;
import net.emaze.dysfunctional.delegates.Predicate;

/**
 * iterating shortcuts (usage shouldn't be abused)
 * @author rferranti
 */
public abstract class Iterations {

    /**
     * @param iterable the iterable where elements are fetched from
     * @param predicate the predicate applied to every element until a match is found
     * @return true if ANY predicate application yields true (gives up on the first positive match)
     */
    public static <E> boolean any(Iterable<E> iterable, Predicate<E> predicate) {
        for (E element : iterable) {
            if (predicate.test(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param iterator the iterator where elements are fetched from
     * @param predicate the predicate applied to every element until a match is found
     * @return true if ANY predicate application yields true (gives up on the first positive match)
     */
    public static <E> boolean any(Iterator<E> iterator, Predicate<E> predicate) {
        return any(new OneTimeIterable<E>(iterator), predicate);
    }

    /**
     * @param array the array where elements are fetched from
     * @param predicate the predicate applied to every element until a match is found
     * @return true if ANY predicate application yields true (gives up on the first positive match)
     */
    public static <E> boolean any(E[] array, Predicate<E> predicate) {
        return any(new ArrayToIterableAdapter<E>(array), predicate);
    }

    /**
     * @param iterable the iterable where elements are fetched from
     * @param predicate the predicate applied to every element fetched from the iterable
     * @return true if EVERY predicate application yields true
     */
    public static <E> boolean every(Iterable<E> iterable, Predicate<E> predicate) {
        for (E element : iterable) {
            if (!predicate.test(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param iterator the iterator where elements are fetched from
     * @param predicate the predicate applied to every element fetched from the iterator
     * @return true if EVERY predicate application yields true
     */
    public static <E> boolean every(Iterator<E> iterator, Predicate<E> predicate) {
        return every(new OneTimeIterable<E>(iterator), predicate);
    }

    /**
     * @param array the array where elements are fetched from
     * @param predicate the predicate applied to every element fetched from the array
     * @return true if EVERY predicate application yields true
     */
    public static <E> boolean every(E[] array, Predicate<E> predicate) {
        return every(new ArrayToIterableAdapter<E>(array), predicate);
    }

    /**
     * @param iterable the iterable where elements are fetched from
     * @param action the action applied to every element fetched from the iterable
     */
    public static <E> void each(Iterable<E> iterable, Action<E> action) {
        for (E element : iterable) {
            action.perform(element);
        }
    }

    /**
     * @param iterator the iterator where elements are fetched from
     * @param action the action applied to every element fetched from the iterator
     */
    public static <E> void each(Iterator<E> iterator, Action<E> action) {
        each(new OneTimeIterable<E>(iterator), action);
    }

    /**
     * @param array the array where elements are fetched from
     * @param action the action applied to every element fetched from the array
     */
    public static <E> void each(E[] array, Action<E> action) {
        each(new ArrayToIterableAdapter<E>(array), action);
    }

    /**
     * 
     * @param iterable the iterable where elements are fetched from
     * @param delegate a delegate used to transform each element
     * @return a List<R> containing the transformed elements
     */
    public static <R, E> List<R> map(Iterable<E> iterable, Delegate<R, E> delegate) {
        return map(iterable.iterator(), delegate);
    }

    /**
     *
     * @param iterator the iterator where elements are fetched from
     * @param delegate a delegate used to transform each element
     * @return a List<R> containing the transformed elements
     */
    public static <R, E> List<R> map(Iterator<E> iterator, Delegate<R, E> delegate) {
        return new EagerConsumer().consume(new TransformingIterator(iterator, delegate));
    }

    /**
     *
     * @param array the array where elements are fetched from
     * @param delegate a delegate used to transform each element
     * @return a List<R> containing the transformed elements
     */
    public static <R, E> List<R> map(E[] array, Delegate<R, E> delegate) {
        return map(new ArrayIterator<E>(array), delegate);
    }

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
}
