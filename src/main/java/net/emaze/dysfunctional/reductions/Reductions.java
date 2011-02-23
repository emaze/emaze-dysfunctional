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
     *
     * @param <R>
     * @param <E>
     * @param iterator
     * @param delegate
     * @param init
     * @return
     */
    public static <R, E> R reduce(Iterator<E> iterator, BinaryDelegate<R, R, E> delegate, R init) {
        return new Reductor<R, E>(delegate, init).consume(iterator);
    }

    /**
     *
     * @param <R>
     * @param <E>
     * @param iterable
     * @param delegate
     * @param init
     * @return
     */
    public static <R, E> R reduce(Iterable<E> iterable, BinaryDelegate<R, R, E> delegate, R init) {
        dbc.precondition(iterable != null, "cannot call reduce with a null iterable");
        return reduce(iterable.iterator(), delegate, init);
    }

    /**
     *
     * @param <R>
     * @param <E>
     * @param array
     * @param delegate
     * @param init
     * @return
     */
    public static <R, E> R reduce(E[] array, BinaryDelegate<R, R, E> delegate, R init) {
        return reduce(new ArrayIterator<E>(array), delegate, init);
    }

    /**
     *
     * @param <E>
     * @param iterator
     * @return
     */
    public static <E> long count(Iterator<E> iterator) {
        return reduce(iterator, new Count<E>(), 0l);
    }

    /**
     *
     * @param <E>
     * @param iterable
     * @return
     */
    public static <E> long count(Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call count with a null iterable");
        return count(iterable.iterator());
    }

    /**
     *
     * @param <E>
     * @param array
     * @return
     */
    public static <E> long count(E[] array) {
        return count(new ArrayIterator<E>(array));
    }

    /**
     *
     * @param <E>
     * @param iterator
     * @return
     */
    public static <E> int counti(Iterator<E> iterator) {
        final long value = reduce(iterator, new Count<E>(), 0l);
        dbc.stateprecondition(value <= Integer.MAX_VALUE, "iterator size overflows an integer");
        return (int) value;
    }

    /**
     *
     * @param <E>
     * @param iterable
     * @return
     */
    public static <E> int counti(Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call counti with a null iterable");
        return counti(iterable.iterator());
    }

    /**
     *
     * @param <E>
     * @param array
     * @return
     */
    public static <E> int counti(E[] array) {
        return counti(new ArrayIterator<E>(array));
    }

    public static <E, C extends Comparator<E>> E max(Iterator<E> iterator, C comparator, E init) {
        return Reductions.reduce(iterator, new Max<E>(comparator), init);
    }

    public static <E extends Comparable<E>> E max(Iterator<E> iterator, E init) {
        return Reductions.reduce(iterator, new Max<E>(new ComparableComparator<E>()), init);
    }

    public static <E, C extends Comparator<E>> E min(Iterator<E> iterator, C comparator, E init) {
        return Reductions.reduce(iterator, new Min<E>(comparator), init);
    }

    public static <E extends Comparable<E>> E min(Iterator<E> iterator, E init) {
        return Reductions.reduce(iterator, new Min<E>(new ComparableComparator<E>()), init);
    }
}
