package net.emaze.dysfunctional.eagers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.consumers.EagerConsumer;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.iterations.OneTimeIterable;
import net.emaze.dysfunctional.iterations.TransformingIterator;

public class Eagerly {

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
     */
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
     */
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
     */
    public static <R, E> List<R> map(E[] array, Delegate<R, E> delegate) {
        return map(new ArrayIterator<E>(array), delegate);
    }
}
