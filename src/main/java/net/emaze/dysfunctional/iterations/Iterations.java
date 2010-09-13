package net.emaze.dysfunctional.iterations;

import net.emaze.dysfunctional.consumers.EagerConsumer;
import java.util.List;
import net.emaze.dysfunctional.consumers.GiveUpConsumer;
import net.emaze.dysfunctional.consumers.StubbornConsumer;
import net.emaze.dysfunctional.delegates.Action;
import net.emaze.dysfunctional.delegates.Delegate;
import net.emaze.dysfunctional.delegates.Predicate;

/**
 * iterating shortcuts (usage shouldn't be abused)
 * @author rferranti
 */
public class Iterations {

    /**
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
     * @param iterable the iterable where elements are fetched from
     * @param action the action applied to every element fetched from the iterable
     */
    public static <E> void each(Iterable<E> iterable, Action<E> action){
        for(E element: iterable){
            action.perform(element);
        }
    }

    /**
     * 
     * @param iterable the iterable where elements are fetched from
     * @param delegate a delegate used to transform each element
     * @return a List<R> containing the transformed elements
     */
    public static <R, E> List<R> map(Iterable<E> iterable, Delegate<R,E> delegate){
        return new EagerConsumer().consume(new TransformingIterator(iterable.iterator(), delegate));
    }
    
    /**
     * filters an iterable returning matching elements
     * @param iterable the iterable where elements are fetched from
     * @param predicate the predicate applied to each element
     * @return a List<R> containing the elements for which the predicate evaluates to true
     */
    public static <E> List<E> some(Iterable<E> iterable, Predicate<E> predicate){
        return new EagerConsumer().consume(new FilteringIterator(iterable.iterator(), predicate));
    }

    /**
     * fetches the first matching element from an iterable, throwing an IllegalArgumentException
     * if no element matches
     * @param iterable
     * @param predicate
     * @return first matching element from the iterable (throws IllegalArgumentException if not found)
     */
    public static <E> E first(Iterable<E> iterable, Predicate<E> predicate){
        return new GiveUpConsumer<E>().consume(new FilteringIterator(iterable.iterator(), predicate));
    }
    
    /**
     * fetches the first element from an iterable, throwing an IllegalArgumentException if no element
     * can be fetched
     * @param iterable
     * @param predicate
     * @return first element from the iterable (throws IllegalArgumentException if the iterable is empty)
     */
    public static <E> E first(Iterable<E> iterable){
        return new GiveUpConsumer<E>().consume(iterable.iterator());
    }
    
    /**
     * fetches the last matching element from an iterable, throwing an IllegalArgumentException
     * if no element matches
     * @param iterable
     * @param predicate
     * @return last matching element from the iterable (throws IllegalArgumentException if not found)
     */
    public static <E> E last(Iterable<E> iterable, Predicate<E> predicate){
        return new StubbornConsumer<E>().consume(new FilteringIterator(iterable.iterator(), predicate));
    }
    
    /**
     * fetches the last element from an iterable, throwing an IllegalArgumentException if no element
     * can be fetched
     * @param iterable
     * @param predicate
     * @return last element of the iterable (throws IllegalArgumentException if the iterable is empty)
     */
    public static <E> E last(Iterable<E> iterable){
        return new StubbornConsumer<E>().consume(iterable.iterator());
    }
}
