package net.emaze.dysfunctional;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.order.ComparableComparator;
import net.emaze.dysfunctional.order.Max;
import net.emaze.dysfunctional.order.Min;
import net.emaze.dysfunctional.reductions.Any;
import net.emaze.dysfunctional.reductions.Count;
import net.emaze.dysfunctional.reductions.Every;
import net.emaze.dysfunctional.reductions.Reductor;

/**
 * reduce, any, every, count, maximum, minimum.
 *
 * @author rferranti
 */
public abstract class Reductions {

    /**
     * Reduces an iterator of elements using the passed delegate.
     *
     * @param <E> the element type parameter
     * @param <R> the result type parameter
     * @param iterator the iterator to be consumed
     * @param delegate the reduction delegate
     * @param init the initial value for reductions
     * @return the reduced value
     */
    public static <E, R> R reduce(Iterator<E> iterator, BiFunction<R, E, R> delegate, R init) {
        return new Reductor<>(delegate, init).apply(iterator);
    }

    /**
     * Reduces an iterator of elements using the passed delegate.
     *
     * @param <E> the element type parameter
     * @param <R> the result type parameter
     * @param iterable the iterable to be consumed
     * @param delegate the reduction delegate
     * @param init the initial value for reductions
     * @return the reduced value
     */
    public static <E, R> R reduce(Iterable<E> iterable, BiFunction<R, E, R> delegate, R init) {
        dbc.precondition(iterable != null, "cannot call reduce with a null iterable");
        return new Reductor<>(delegate, init).apply(iterable.iterator());
    }

    /**
     * Reduces an array of elements using the passed delegate.
     *
     * @param <E> the element type parameter
     * @param <R> the result type parameter
     * @param array the array to be consumed
     * @param delegate the reduction delegate
     * @param init the initial value for reductions
     * @return the reduced value
     */
    public static <E, R> R reduce(E[] array, BiFunction<R, E, R> delegate, R init) {
        return new Reductor<>(delegate, init).apply(new ArrayIterator<E>(array));
    }

    /**
     * Yields true if ANY predicate application on the given iterable yields
     * true (giving up on the first positive match).
     *
     * @param <E> the iterable element type parameter
     * @param iterable the iterable where elements are fetched from
     * @param predicate the predicate applied to every element until a match is
     * found
     * @return true if ANY predicate application yields true (gives up on the
     * first positive match)
     */
    public static <E> boolean any(Iterable<E> iterable, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot call any with a null iterable");
        return new Any<E>(predicate).test(iterable.iterator());
    }

    /**
     * Yields true if ANY predicate application on the given iterator yields
     * true (giving up on the first positive match).
     *
     * @param <E> the iterator element type parameter
     * @param iterator the iterator where elements are fetched from
     * @param predicate the predicate applied to every element until a match is
     * found
     * @return true if ANY predicate application yields true (gives up on the
     * first positive match)
     */
    public static <E> boolean any(Iterator<E> iterator, Predicate<E> predicate) {
        return new Any<E>(predicate).test(iterator);
    }

    /**
     * Yields true if ANY predicate application on the given array yields true
     * (giving up on the first positive match).
     *
     * @param <E> the array element type parameter
     * @param array the array where elements are fetched from
     * @param predicate the predicate applied to every element until a match is
     * found
     * @return true if ANY predicate application yields true (gives up on the
     * first positive match)
     */
    public static <E> boolean any(E[] array, Predicate<E> predicate) {
        return new Any<E>(predicate).test(new ArrayIterator<E>(array));
    }

    /**
     * Yields true if EVERY predicate application on the given iterable yields
     * true.
     *
     * @param <E> the iterable element type parameter
     * @param iterable the iterable where elements are fetched from
     * @param predicate the predicate applied to every element fetched from the
     * iterable
     * @return true if EVERY predicate application yields true
     */
    public static <E> boolean every(Iterable<E> iterable, Predicate<E> predicate) {
        dbc.precondition(iterable != null, "cannot call every with a null iterable");
        return new Every<E>(predicate).test(iterable.iterator());
    }

    /**
     * Yields true if EVERY predicate application on the given iterator yields
     * true.
     *
     * @param <E> the iterator element type parameter
     * @param iterator the iterator where elements are fetched from
     * @param predicate the predicate applied to every element fetched from the
     * iterator
     * @return true if EVERY predicate application yields true
     */
    public static <E> boolean every(Iterator<E> iterator, Predicate<E> predicate) {
        return new Every<E>(predicate).test(iterator);
    }

    /**
     * Yields true if EVERY predicate application on the given array yields
     * true.
     *
     * @param <E> the array element type parameter
     * @param array the array where elements are fetched from
     * @param predicate the predicate applied to every element fetched from the
     * array
     * @return true if EVERY predicate application yields true
     */
    public static <E> boolean every(E[] array, Predicate<E> predicate) {
        return new Every<E>(predicate).test(new ArrayIterator<E>(array));
    }

    /**
     * Counts elements contained in the iterator.
     *
     * @param <E> the iterator element type parameter
     * @param iterator the iterator to be consumed
     * @return the size of the iterator
     */
    public static <E> long count(Iterator<E> iterator) {
        return reduce(iterator, new Count<E>(), 0l);
    }

    /**
     * Counts elements contained in the iterable.
     *
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
     *
     * @param <E> the iterator element type parameter
     * @param iterator the iterator to be consumed
     * @return the size of the iterator
     */
    public static <E> int counti(Iterator<E> iterator) {
        final long value = reduce(iterator, new Count<E>(), 0l);
        dbc.state(value <= Integer.MAX_VALUE, "iterator size overflows an integer");
        return (int) value;
    }

    /**
     * Counts elements contained in the iterable.
     *
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
     *
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
     *
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
     *
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
     *
     * @param <E> the iterator element type parameter
     * @param iterator the iterator to be consumed
     * @param init the initial value to be used
     * @return the min element contained in the iterator
     */
    public static <E extends Comparable<E>> E minimum(Iterator<E> iterator, E init) {
        return Reductions.reduce(iterator, new Min<E>(new ComparableComparator<E>()), init);
    }
}
