package net.emaze.disfunctional.iterations;

import net.emaze.disfunctional.delegates.Predicate;

/**
 *
 * @author rferranti
 */
public class Iterations {

    public static <E> boolean any(Iterable<E> iterable, Predicate<E> predicate) {
        for (E element : iterable) {
            if (predicate.test(element)) {
                return true;
            }
        }
        return false;
    }

    public static <E> boolean every(Iterable<E> iterable, Predicate<E> predicate) {
        for (E element : iterable) {
            if (!predicate.test(element)) {
                return false;
            }
        }
        return true;
    }
}
