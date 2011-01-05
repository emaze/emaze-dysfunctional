package net.emaze.dysfunctional.reductions;

import java.util.Iterator;
import net.emaze.dysfunctional.adapting.ArrayIterator;

/**
 *
 * @author rferranti
 */
public abstract class Reductions {
    /**
     *
     * @param <E>
     * @param iterator
     * @return
     */
    public static <E> long count(Iterator<E> iterator) {
        return new CountingReductor<E>().consume(iterator);
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
     * @param outputIterator
     * @return
     */
    public static <E> long count(E[] array) {
        return count(new ArrayIterator<E>(array));
    }
}
