package net.emaze.dysfunctional.dispatching;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.iterations.TransformingIterator;

/**
 *
 * @author rferranti
 */
public class Transforming {

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
     * Lazily applies an action to every element of the iterator. 
     * @param <E> the element type parameter
     * @param iterator the iterator 
     * @param action the action to be applied
     * @return an iterator that when consumed applies a side effect to every element
     */
    public static <E> Iterator<E> tap(Iterator<E> iterator, Action<E> action) {
        return transform(iterator, new Tapper<E>(action));
    }

    /**
     * Lazily applies an action to every element of the iterator. 
     * @param <E> the element type parameter
     * @param iterable the iterable
     * @param action the action to be applied
     * @return an iterator that when consumed applies a side effect to every element
     */
    public static <E> Iterator<E> tap(Iterable<E> iterable, Action<E> action) {
        return transform(iterable, new Tapper<E>(action));
    }

    /**
     * Lazily applies an action to every element of the iterator. 
     * @param <E> the element type parameter
     * @param array the array
     * @param action the action to be applied
     * @return an iterator that when consumed applies a side effect to every element
     */
    public static <E> Iterator<E> tap(E[] array, Action<E> action) {
        return transform(array, new Tapper<E>(action));
    }
}
