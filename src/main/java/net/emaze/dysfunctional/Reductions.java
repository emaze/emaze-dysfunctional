package net.emaze.dysfunctional;

import java.util.Comparator;
import java.util.Iterator;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.iterations.OneTimeIterable;
import net.emaze.dysfunctional.order.ComparableComparator;
import net.emaze.dysfunctional.order.Max;
import net.emaze.dysfunctional.order.Min;
import net.emaze.dysfunctional.reductions.Count;
import net.emaze.dysfunctional.reductions.Reductor;

/**
 *
 * @author rferranti
 */
public abstract class Reductions {

    /**
     * Reduces an iterator of elements using the passed delegate.
     * @param <R> the result type parameter
     * @param <E> the element type parameter
     * @param iterator the iterator to be consumed
     * @param delegate the reduction delegate
     * @param init the initial value for reductions
     * @return the reduced value
     */
    public static <R, E> R reduce(Iterator<E> iterator, BinaryDelegate<R, R, E> delegate, R init) {
        return new Reductor<R, E>(delegate, init).perform(iterator);
    }

    /**
     * Reduces an iterator of elements using the passed delegate.
     * @param <R> the result type parameter
     * @param <E> the element type parameter
     * @param iterable the iterable to be consumed
     * @param delegate the reduction delegate
     * @param init the initial value for reductions
     * @return the reduced value
     */
    public static <R, E> R reduce(Iterable<E> iterable, BinaryDelegate<R, R, E> delegate, R init) {
        dbc.precondition(iterable != null, "cannot call reduce with a null iterable");
        return reduce(iterable.iterator(), delegate, init);
    }

    /**
     * Reduces an array of elements using the passed delegate.
     * @param <R> the result type parameter
     * @param <E> the element type parameter
     * @param array the array to be consumed
     * @param delegate the reduction delegate
     * @param init the initial value for reductions
     * @return the reduced value
     */
    public static <R, E> R reduce(E[] array, BinaryDelegate<R, R, E> delegate, R init) {
        return reduce(new ArrayIterator<E>(array), delegate, init);
    }

    /**
     * Yields true if ANY predicate application on the given iterable yields
     * true (giving up on the first positive match).
     * @param <E> the iterable element type parameter
     * @param iterable the iterable where elements are fetched from
     * @param predicate the predicate applied to every element until a match is found
     * @return true if ANY predicate application yields true (gives up on the first positive match)
     */
    public static <E> boolean any(Iterable<E> iterable, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot call any with a null iterable");
        dbc.precondition(predicate != null, "cannot call any with a null predicate");

        for (E element : iterable) {
            if (predicate.accept(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Yields true if ANY predicate application on the given iterator yields
     * true (giving up on the first positive match).
     * @param <E> the iterator element type parameter
     * @param iterator the iterator where elements are fetched from
     * @param predicate the predicate applied to every element until a match is found
     * @return true if ANY predicate application yields true (gives up on the first positive match)
     */
    public static <E> boolean any(Iterator<E> iterator, Predicate<E> predicate) {
        return any(new OneTimeIterable<E>(iterator), predicate);
    }

    /**
     * Yields true if ANY predicate application on the given array yields true 
     * (giving up on the first positive match).
     * @param <E> the array element type parameter
     * @param array the array where elements are fetched from
     * @param predicate the predicate applied to every element until a match is found
     * @return true if ANY predicate application yields true (gives up on the first positive match)
     */
    public static <E> boolean any(E[] array, Predicate<E> predicate) {
        return any(new ArrayIterator<E>(array), predicate);
    }

    /**
     * Yields true if EVERY predicate application on the given iterable yields
     * true.
     * @param <E> the iterable element type parameter
     * @param iterable the iterable where elements are fetched from
     * @param predicate the predicate applied to every element fetched from the iterable
     * @return true if EVERY predicate application yields true
     */
    public static <E> boolean every(Iterable<E> iterable, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot call every with a null iterable");
        dbc.precondition(predicate != null, "cannot call every with a null predicate");

        for (E element : iterable) {
            if (!predicate.accept(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Yields true if EVERY predicate application on the given iterator yields
     * true.
     * @param <E> the iterator element type parameter
     * @param iterator the iterator where elements are fetched from
     * @param predicate the predicate applied to every element fetched from the iterator
     * @return true if EVERY predicate application yields true
     */
    public static <E> boolean every(Iterator<E> iterator, Predicate<E> predicate) {
        return every(new OneTimeIterable<E>(iterator), predicate);
    }

    /**
     * Yields true if EVERY predicate application on the given array yields
     * true.
     * @param <E> the array element type parameter
     * @param array the array where elements are fetched from
     * @param predicate the predicate applied to every element fetched from the array
     * @return true if EVERY predicate application yields true
     */
    public static <E> boolean every(E[] array, Predicate<E> predicate) {
        return every(new ArrayIterator<E>(array), predicate);
    }

    /**
     * Counts elements contained in the iterator.
     * @param <E> the iterator element type parameter
     * @param iterator the iterator to be consumed
     * @return the size of the iterator
     */
    public static <E> long count(Iterator<E> iterator) {
        return reduce(iterator, new Count<E>(), 0l);
    }

    /**
     * Counts elements contained in the iterable.
     * @param <E> the iterable element type parameter
     * @param iterable the iterable to be consumed
     * @return the size of the iterable
     */
    public static <E> long count(Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call count with a null iterable");
        return count(iterable.iterator());
    }

    /**
     * Counts elements contained in the iterator.
     * @param <E> the iterator element type parameter
     * @param iterator the iterator to be consumed
     * @return the size of the iterator
     */
    public static <E> int counti(Iterator<E> iterator) {
        final long value = reduce(iterator, new Count<E>(), 0l);
        dbc.stateprecondition(value <= Integer.MAX_VALUE, "iterator size overflows an integer");
        return (int) value;
    }

    /**
     * Counts elements contained in the iterable.
     * @param <E> the iterable element type parameter
     * @param iterable the iterable to be consumed
     * @return the size of the iterator
     */
    public static <E> int counti(Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call counti with a null iterable");
        return counti(iterable.iterator());
    }

    /**
     * Returns the max element contained in the iterator 
     * @param <E> the iterator element type parameter
     * @param <C> the comparator type parameter
     * @param iterator the iterator to be consumed
     * @param comparator the comparator to be used to evaluate the max element
     * @param init the initial value to be used 
     * @return the max element contained in the iterator
     */
    public static <E, C extends Comparator<E>> E maximum(Iterator<E> iterator, C comparator, E init) {
        return Reductions.reduce(iterator, new Max<E>(comparator), init);
    }

    /**
     * Returns the max element contained in the iterator
     * @param <E> the iterator element type parameter
     * @param iterator the iterator to be consumed
     * @param init the initial value to be used
     * @return the max element contained in the iterator
     */
    public static <E extends Comparable<E>> E maximum(Iterator<E> iterator, E init) {
        return Reductions.reduce(iterator, new Max<E>(new ComparableComparator<E>()), init);
    }

    /**
     * Returns the min element contained in the iterator
     * @param <E> the iterator element type parameter
     * @param <C> the comparator type parameter
     * @param iterator the iterator to be consumed
     * @param comparator the comparator to be used to evaluate the min element
     * @param init the initial value to be used
     * @return the min element contained in the iterator
     */
    public static <E, C extends Comparator<E>> E minimum(Iterator<E> iterator, C comparator, E init) {
        return Reductions.reduce(iterator, new Min<E>(comparator), init);
    }

    /**
     * Returns the min element contained in the iterator
     * @param <E> the iterator element type parameter
     * @param iterator the iterator to be consumed
     * @param init the initial value to be used
     * @return the min element contained in the iterator
     */
    public static <E extends Comparable<E>> E minimum(Iterator<E> iterator, E init) {
        return Reductions.reduce(iterator, new Min<E>(new ComparableComparator<E>()), init);
    }
}
