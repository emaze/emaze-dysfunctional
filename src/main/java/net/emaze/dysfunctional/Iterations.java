package net.emaze.dysfunctional;

import java.util.Iterator;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.iterations.*;
import net.emaze.dysfunctional.options.Maybe;

/**
 * Iterators and Iterables shortcuts.
 *
 * @author rferranti
 */
public abstract class Iterations {

    /**
     * Creates an iterable usable only ONE TIME from an iterator.
     *
     * @param <T> the iterator element type parameter
     * @param iterator the iterator to be yielded by the iterable
     * @return an iterable consumable only once
     */
    public static <T> Iterable<T> oneTime(Iterator<T> iterator) {
        return new OneTimeIterable<T>(iterator);
    }

    /**
     * Creates an empty iterator
     *
     * @param <T> the element parameter type
     * @return an empty iterator.
     */
    public static <T> Iterator<T> iterator() {
        return new EmptyIterator<T>();
    }

    /**
     * Creates an iterator from the passed value.
     *
     * @param <T> the element parameter type
     * @param value the value to be yielded by the iterator
     * @return an iterator.
     */
    public static <T> Iterator<T> iterator(T value) {
        return new SingletonIterator<T>(value);
    }

    /**
     * Creates an iterator from the passed values.
     *
     * @param <T> the elements parameter type
     * @param first the first element
     * @param second the second element
     * @return an iterator.
     */
    public static <T> Iterator<T> iterator(T first, T second) {
        return ArrayIterator.of(first, second);
    }

    /**
     * Creates an iterator from the passed values.
     *
     * @param <T> the elements parameter type
     * @param first the first element
     * @param second the second element
     * @param third the third element
     * @return an iterator.
     */
    public static <T> Iterator<T> iterator(T first, T second, T third) {
        return ArrayIterator.of(first, second, third);
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
     * Adapts a provider of {@code Maybe<T>} to a iterator of {@code T}.
     * The iterator ends when the provider returns nothing.
     *
     * @param <T> the provider element parameter type
     * @param provider the provider of values to be yielded by the iterator
     * @return an iterator
     */
    public static <T> Iterator<T> iterator(Provider<Maybe<T>> provider) {
        return new ProviderToIterator<T>(provider);
    }

    /**
     * Creates an empty iterable.
     *
     * @param <T> the element parameter type
     * @return an iterable always yielding an empty iterator.
     */
    public static <T> Iterable<T> iterable() {
        return new EmptyIterable<T>();
    }

    /**
     * Creates an iterable from the passed value.
     *
     * @param <T> the element parameter type
     * @param value the value to be yielded by the iterator
     * @return an iterable.
     */
    public static <T> Iterable<T> iterable(T value) {
        return new SingletonIterable<T>(value);
    }

    /**
     * Creates an iterable from the passed values.
     *
     * @param <T> the elements parameter type
     * @param first the first element
     * @param second the second element
     * @return an iterable.
     */
    public static <T> Iterable<T> iterable(T first, T second) {
        return ArrayIterable.of(first, second);
    }

    /**
     * Creates an iterable from the passed values.
     *
     * @param <T> the elements parameter type
     * @param first the first element
     * @param second the second element
     * @param third the third element
     * @return an iterable.
     */
    public static <T> Iterable<T> iterable(T first, T second, T third) {
        return ArrayIterable.of(first, second, third);
    }

    /**
     * Creates an iterable from the passed array.
     *
     * @param <T> the array element parameter type
     * @param values the values to be yielded by the iterator
     * @return an iterable.
     */
    public static <T> Iterable<T> iterable(T... values) {
        return new ArrayIterable<T>(values);
    }
}
