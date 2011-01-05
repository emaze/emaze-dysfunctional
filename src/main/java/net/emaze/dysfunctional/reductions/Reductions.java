package net.emaze.dysfunctional.reductions;

import java.util.Iterator;
import net.emaze.dysfunctional.adapting.ArrayIterator;
import net.emaze.dysfunctional.delegates.BinaryDelegate;

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
}
