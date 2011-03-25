package net.emaze.dysfunctional.iterations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.adapting.ArrayIterator;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.consumers.EagerConsumer;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 * iterating shortcuts (usage shouldn't be abused)
 * @author rferranti
 */
public abstract class Iterations {

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
            if (predicate.test(element)) {
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
            if (!predicate.test(element)) {
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
     * Applies a side effect on each elements of the passed iterable.
     * @param <E> the iterable element type parameter
     * @param iterable the iterable where elements are fetched from
     * @param action the action applied to every element fetched from the iterable
     */
    public static <E> void each(Iterable<E> iterable, Action<E> action) {
        dbc.precondition(iterable != null, "cannot call each with a null iterable");
        dbc.precondition(action != null, "cannot call each with a null action");

        for (E element : iterable) {
            action.perform(element);
        }
    }

    /**
     * Applies a side effect on each elements of the passed iterator.
     * @param <E> the iterator element type parameter
     * @param iterator the iterator where elements are fetched from
     * @param action the action applied to every element fetched from the iterator
     */
    public static <E> void each(Iterator<E> iterator, Action<E> action) {
        each(new OneTimeIterable<E>(iterator), action);
    }

    /**
     * Applies a side effect on each elements of the passed array.
     * @param <E> the array element type parameter
     * @param array the array where elements are fetched from
     * @param action the action applied to every element fetched from the array
     */
    public static <E> void each(E[] array, Action<E> action) {
        each(new ArrayIterator<E>(array), action);
    }

    /**
     * 
     * @param <R>
     * @param <E> 
     * @param iterable the iterable where elements are fetched from
     * @param delegate a delegate used to transform each element
     * @return a List<R> containing the transformed elements
     * @deprecated use Iterations.transform ° Consumers.all instead
     */
    @Deprecated
    public static <R, E> List<R> map(Iterable<E> iterable, Delegate<R, E> delegate) {
        return map(iterable.iterator(), delegate);
    }

    /**
     *
     * @param <R>
     * @param <E> 
     * @param iterator the iterator where elements are fetched from
     * @param delegate a delegate used to transform each element
     * @return a List<R> containing the transformed elements
     * @deprecated use Iterations.transform ° Consumers.all instead
     */
    @Deprecated
    public static <R, E> List<R> map(Iterator<E> iterator, Delegate<R, E> delegate) {
        return new EagerConsumer<ArrayList<R>, R>(new ArrayListFactory<R>()).consume(new TransformingIterator<R, E>(iterator, delegate));
    }

    /**
     *
     * @param <R>
     * @param <E> 
     * @param array the array where elements are fetched from
     * @param delegate a delegate used to transform each element
     * @return a List<R> containing the transformed elements
     * @deprecated use Iterations.transform ° Consumers.all instead
     */
    @Deprecated
    public static <R, E> List<R> map(E[] array, Delegate<R, E> delegate) {
        return map(new ArrayIterator<E>(array), delegate);
    }

    /**
     * Applies a (lazy) transformation on every element of the iterable.
     * @param <R> the result iterator element type parameter
     * @param <E> the input iterable element type parameter
     * @param iterable the iterable where elements are fetched from
     * @param delegate a delegate used to transform each element
     * @return the transformed iterator
     */
    public static <R, E> Iterator<R> transform(Iterable<E> iterable, Delegate<R, E> delegate) {
        dbc.precondition(iterable != null, "cannot call transform with a null iterable");
        return transform(iterable.iterator(), delegate);
    }

    /**
     * Applies a (lazy) transformation on every element of the iterator.
     * @param <R> the result iterator element type parameter
     * @param <E> the input iterator element type parameter
     * @param iterator the iterator where elements are fetched from
     * @param delegate a delegate used to transform each element
     * @return the transformed iterator
     */
    public static <R, E> Iterator<R> transform(Iterator<E> iterator, Delegate<R, E> delegate) {
        return new TransformingIterator<R, E>(iterator, delegate);
    }

    /**
     * Applies a (lazy) transformation on every element of the array.
     * @param <R> the result iterator element type parameter
     * @param <E> the input array element type parameter
     * @param array the array where elements are fetched from
     * @param delegate a delegate used to transform each element
     * @return the transformed iterator
     */
    public static <R, E> Iterator<R> transform(E[] array, Delegate<R, E> delegate) {
        return transform(new ArrayIterator<E>(array), delegate);
    }

    /**
     * Creates an iterable usable only ONE TIME from an iterator.
     * @param <T> the iterator element type parameter
     * @param iterator the iterator to be yielded by the iterator
     * @return an iterable consumable only once
     */
    public static <T> Iterable<T> oneTime(Iterator<T> iterator) {
        return new OneTimeIterable<T>(iterator);
    }

    /**
     * Creates an iterator from the passed array.
     * @param <T> the array element parameter type
     * @param values the values to be yielded by the iterator
     * @return an iterator.
     */
    public static <T> Iterator<T> iterator(T... values) {
        return new ArrayIterator<T>(values);
    }
}
