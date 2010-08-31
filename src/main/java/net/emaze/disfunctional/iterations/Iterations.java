package net.emaze.disfunctional.iterations;

import java.util.ArrayList;
import java.util.List;
import net.emaze.disfunctional.delegates.Action;
import net.emaze.disfunctional.delegates.Delegate;
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

    public static <E> void each(Iterable<E> iterable, Action<E> action){
        for(E element: iterable){
            action.perform(element);
        }
    }

    public static <R, E> List<R> map(Iterable<E> iterable, Delegate<R,E> delegate){
        final List<R> out = new ArrayList<R>();
        for(E element: iterable){
            out.add(delegate.perform(element));
        }
        return out;
    }

}
