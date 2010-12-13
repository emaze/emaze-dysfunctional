package net.emaze.dysfunctional.iterations;

import net.emaze.dysfunctional.adapting.ArrayIterator;
import java.util.Iterator;
import net.emaze.dysfunctional.consumers.EagerConsumer;
import java.util.List;
import net.emaze.dysfunctional.delegates.Action;
import net.emaze.dysfunctional.delegates.Delegate;
import net.emaze.dysfunctional.delegates.Predicate;

/**
 * iterating shortcuts (usage shouldn't be abused)
 * @author rferranti
 */
public abstract class Iterations {

    /**
     * @param <E> 
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
     * @param <E> 
     * @param iterator the iterator where elements are fetched from
     * @param predicate the predicate applied to every element until a match is found
     * @return true if ANY predicate application yields true (gives up on the first positive match)
     */
    public static <E> boolean any(Iterator<E> iterator, Predicate<E> predicate) {
        return any(new OneTimeIterable<E>(iterator), predicate);
    }

    /**
     * @param <E> 
     * @param array the array where elements are fetched from
     * @param predicate the predicate applied to every element until a match is found
     * @return true if ANY predicate application yields true (gives up on the first positive match)
     */
    public static <E> boolean any(E[] array, Predicate<E> predicate) {
        return any(new ArrayIterator<E>(array), predicate);
    }

    /**
     * @param <E> 
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
     * @param <E> 
     * @param iterator the iterator where elements are fetched from
     * @param predicate the predicate applied to every element fetched from the iterator
     * @return true if EVERY predicate application yields true
     */
    public static <E> boolean every(Iterator<E> iterator, Predicate<E> predicate) {
        return every(new OneTimeIterable<E>(iterator), predicate);
    }

    /**
     * @param <E> 
     * @param array the array where elements are fetched from
     * @param predicate the predicate applied to every element fetched from the array
     * @return true if EVERY predicate application yields true
     */
    public static <E> boolean every(E[] array, Predicate<E> predicate) {
        return every(new ArrayIterator<E>(array), predicate);
    }

    /**
     * @param <E> 
     * @param iterable the iterable where elements are fetched from
     * @param action the action applied to every element fetched from the iterable
     */
    public static <E> void each(Iterable<E> iterable, Action<E> action) {
        for (E element : iterable) {
            action.perform(element);
        }
    }

    /**
     * @param <E> 
     * @param iterator the iterator where elements are fetched from
     * @param action the action applied to every element fetched from the iterator
     */
    public static <E> void each(Iterator<E> iterator, Action<E> action) {
        each(new OneTimeIterable<E>(iterator), action);
    }

    /**
     * @param <E> 
     * @param array the array where elements are fetched from
     * @param action the action applied to every element fetched from the array
     */
    public static <E> void each(E[] array, Action<E> action) {
        each(new ArrayIterator<E>(array), action);
    }

    /**
     * 
     * @param <R>
     * @param <E> 
     * @param iterable the iterable where elements are fetched from
     * @param delegate a delegate used to transform each element
     * @return a List<R> containing the transformed elements
     */
    public static <R, E> List<R> map(Iterable<E> iterable, Delegate<R, E> delegate) {
        return map(iterable.iterator(), delegate);
    }

    /**
     *
     * @param <R>
     * @param <E> 
     * @param iterator the iterator where elements are fetched from
     * @param delegate a delegate used to transform each element
     * @return a List<R> containing the transformed elements
     */
    public static <R, E> List<R> map(Iterator<E> iterator, Delegate<R, E> delegate) {
        return new EagerConsumer<R>().consume(new TransformingIterator<R, E>(iterator, delegate));
    }

    /**
     *
     * @param <R>
     * @param <E> 
     * @param array the array where elements are fetched from
     * @param delegate a delegate used to transform each element
     * @return a List<R> containing the transformed elements
     */
    public static <R, E> List<R> map(E[] array, Delegate<R, E> delegate) {
        return map(new ArrayIterator<E>(array), delegate);
    }


    public static <T> Iterable<T> oneTime(Iterator<T> iterator){
        return new OneTimeIterable<T>(iterator);
    }
}
