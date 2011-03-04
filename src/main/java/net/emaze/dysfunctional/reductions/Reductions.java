package net.emaze.dysfunctional.reductions;

import java.util.Comparator;
import java.util.Iterator;
import net.emaze.dysfunctional.adapting.ArrayIterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.BinaryDelegate;
import net.emaze.dysfunctional.order.ComparableComparator;
import net.emaze.dysfunctional.order.Max;
import net.emaze.dysfunctional.order.Min;

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
        return new Reductor<R, E>(delegate, init).consume(iterator);
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
    public static <E, C extends Comparator<E>> E max(Iterator<E> iterator, C comparator, E init) {
        return Reductions.reduce(iterator, new Max<E>(comparator), init);
    }

    /**
     * Returns the max element contained in the iterator
     * @param <E> the iterator element type parameter
     * @param iterator the iterator to be consumed
     * @param init the initial value to be used
     * @return the max element contained in the iterator
     */
    public static <E extends Comparable<E>> E max(Iterator<E> iterator, E init) {
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
    public static <E, C extends Comparator<E>> E min(Iterator<E> iterator, C comparator, E init) {
        return Reductions.reduce(iterator, new Min<E>(comparator), init);
    }

    /**
     * Returns the min element contained in the iterator
     * @param <E> the iterator element type parameter
     * @param iterator the iterator to be consumed
     * @param init the initial value to be used
     * @return the min element contained in the iterator
     */
    public static <E extends Comparable<E>> E min(Iterator<E> iterator, E init) {
        return Reductions.reduce(iterator, new Min<E>(new ComparableComparator<E>()), init);
    }
}
