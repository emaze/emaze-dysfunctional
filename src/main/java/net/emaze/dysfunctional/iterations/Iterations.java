package net.emaze.dysfunctional.iterations;

import net.emaze.dysfunctional.consumers.EagerConsumer;
import java.util.List;
import net.emaze.dysfunctional.consumers.GiveUpConsumer;
import net.emaze.dysfunctional.delegates.Action;
import net.emaze.dysfunctional.delegates.Delegate;
import net.emaze.dysfunctional.delegates.Predicate;

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
        return new EagerConsumer().consume(new TransformingIterator(iterable.iterator(), delegate));
    }
    
    public static <E> List<E> some(Iterable<E> iterable, Predicate<E> predicate){
        return new EagerConsumer().consume(new FilteringIterator(iterable.iterator(), predicate));
    }

    public static <E> E first(Iterable<E> iterable, Predicate<E> predicate){
        return new GiveUpConsumer<E>().consume(new FilteringIterator(iterable.iterator(), predicate));
    }
}
