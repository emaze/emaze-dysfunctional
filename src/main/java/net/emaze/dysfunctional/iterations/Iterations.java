package net.emaze.dysfunctional.iterations;

import java.util.Iterator;

/**
 * iterating shortcuts (usage shouldn't be abused)
 * @author rferranti
 */
public abstract class Iterations {

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
     * Creates an empty iterator
     * @param <T> the element parameter type
     * @return an empty iterator.
     */
    public static <T> Iterator<T> iterator() {
        return new EmptyIterator<T>();
    }

    /**
     * Creates an iterator from the passed value.
     * @param <T> the element parameter type
     * @param value the value to be yielded by the iterator
     * @return an iterator.
     */
    public static <T> Iterator<T> iterator(T value) {
        return new SingletonIterator<T>(value);
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
     * Creates an empty iterable.
     * @param <T> the element parameter type
     * @return an iterable always yielding an empty iterator.
     */
    public static <T> Iterable<T> iterable() {
        return new EmptyIterable<T>();
    }

    /**
     * Creates an iterable from the passed value.
     * @param <T> the element parameter type
     * @param value the value to be yielded by the iterator
     * @return an iterable.
     */
    public static <T> Iterable<T> iterable(T value) {
        return new SingletonIterable<T>(value);
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
