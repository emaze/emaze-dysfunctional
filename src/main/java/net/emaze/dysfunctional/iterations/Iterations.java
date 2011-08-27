package net.emaze.dysfunctional.iterations;

import net.emaze.dysfunctional.dispatching.Tapper;
import java.util.Iterator;
import net.emaze.dysfunctional.adapting.ArrayIterable;
import net.emaze.dysfunctional.adapting.ArrayIterator;
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
            if (predicate.accept(element)) {
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
            if (!predicate.accept(element)) {
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

    /**
     * Creates an iterable usable only ONE TIME from an iterator.
     * @param <T> the iterator element type parameter
     * @param iterator the iterator to be yielded by the iterable
     * @return an iterable consumable only once
     */
    public static <T> Iterable<T> oneTime(Iterator<T> iterator) {
        return new OneTimeIterable<T>(iterator);
    }

    private static <T> T[] array(T... array) {
        return array;
    }

    /**
     * Creates an iterator from the passed value.
     * @param <T> the element parameter type
     * @param value the value to be yielded by the iterator
     * @return an iterator.
     */
    public static <T> Iterator<T> iterator(T value) {
        return new ArrayIterator<T>(array(value));
    }

    /**
     * Creates an iterator from the passed values.
     * @param <T> the elements parameter type
     * @param first the first element
     * @param second the second element
     * @return an iterator.
     */
    public static <T> Iterator<T> iterator(T first, T second) {
        return new ArrayIterator<T>(array(first, second));
    }

    /**
     * Creates an iterator from the passed values.
     * @param <T> the elements parameter type
     * @param first the first element
     * @param second the second element
     * @param third the third element
     * @return an iterator.
     */
    public static <T> Iterator<T> iterator(T first, T second, T third) {
        return new ArrayIterator<T>(array(first, second, third));
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
    /**
     * Creates an iterable from the passed value.
     * @param <T> the element parameter type
     * @param value the value to be yielded by the iterator
     * @return an iterable.
     */
    public static <T> Iterable<T> iterable(T value) {
        return new ArrayIterable<T>(array(value));
    }

    /**
     * Creates an iterable from the passed values.
     * @param <T> the elements parameter type
     * @param first the first element
     * @param second the second element
     * @return an iterable.
     */
    public static <T> Iterable<T> iterable(T first, T second) {
        return new ArrayIterable<T>(array(first, second));
    }

    /**
     * Creates an iterable from the passed values.
     * @param <T> the elements parameter type
     * @param first the first element
     * @param second the second element
     * @param third the third element
     * @return an iterable.
     */
    public static <T> Iterable<T> iterable(T first, T second, T third) {
        return new ArrayIterable<T>(array(first, second, third));
    }

    /**
     * Creates an iterable from the passed array.
     * @param <T> the array element parameter type
     * @param values the values to be yielded by the iterator
     * @return an iterable.
     */
    public static <T> Iterable<T> iterable(T... values) {
        return new ArrayIterable<T>(values);
    }
    
}
