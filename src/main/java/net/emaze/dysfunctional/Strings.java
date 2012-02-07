package net.emaze.dysfunctional;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.iterations.ConstantIterator;
import net.emaze.dysfunctional.strings.InterposeStrings;
import net.emaze.dysfunctional.strings.JoinStrings;

/**
 *
 * @author rferranti
 */
public abstract class Strings {

    /**
     * Joins an array of T into a String. E.g:
     * <code>[1,2,3] -> "123"</code>
     *
     * @param <T> the array element type
     * @param array the array to be joined
     * @return the resulting string
     */
    public static <T> String join(T[] array) {
        return new JoinStrings<T>().perform(new ArrayIterator<T>(array));
    }

    /**
     * Joins an iterable of T into a String. E.g:
     * <code>[1,2,3] -> "123"</code>
     *
     * @param <T> the iterable element type
     * @param iterable the iterable to be joined
     * @return the resulting string
     */
    public static <T> String join(Iterable<T> iterable) {
        dbc.precondition(iterable != null, "cannot join a null iterable");
        return new JoinStrings<T>().perform(iterable.iterator());
    }

    /**
     * Joins an iterator of T into a String. E.g:
     * <code>[1,2,3] -> "123"</code>
     *
     * @param <T> the iterator element type
     * @param iterator the iterator to be joined
     * @return the resulting string
     */
    public static <T> String join(Iterator<T> iterator) {
        return new JoinStrings<T>().perform(iterator);
    }

    /**
     * Creates a string by interposing stringified values from the source array
     * with values from the separators. E.g:
     * <code>interpose([1,2,3,4,5], ["-", ","]) -> "1-2,3-4,5"</code>
     *
     * @param <T> the array element type
     * @param <V> the separator type
     * @param values the source array
     * @param separators the separators
     * @return the resulting string
     */
    public static <T, V> String interpose(T[] values, Iterator<V> separators) {
        return new InterposeStrings<T, V>().perform(new ArrayIterator<T>(values), separators);
    }

    /**
     * Creates a string by interposing stringified values from the source
     * iterable with values from the separators. E.g:
     * <code>interpose([1,2,3,4,5], ["-", ","]) -> "1-2,3-4,5"</code>
     *
     * @param <T> the iterable element type
     * @param <V> the separator type
     * @param values the source iterable
     * @param separators the separators
     * @return the resulting string
     */
    public static <T, V> String interpose(Iterable<T> values, Iterator<V> separators) {
        dbc.precondition(values != null, "calling interpose with a null iterable");
        return new InterposeStrings<T, V>().perform(values.iterator(), separators);
    }

    /**
     * Creates a string by interposing stringified values from the source
     * iterator with values from the separators. E.g:
     * <code>interpose([1,2,3,4,5], ["-", ","]) -> "1-2,3-4,5"</code>
     *
     * @param <T> the a element type
     * @param <V> the separator type
     * @param values the source iterator
     * @param separators the separators
     * @return the resulting string
     */
    public static <T, V> String interpose(Iterator<T> values, Iterator<V> separators) {
        return new InterposeStrings<T, V>().perform(values, separators);
    }

    /**
     * Creates a string by interposing stringified values from the source array
     * with the separator. E.g:
     * <code>interpose([1,2,3], "-") -> "1-2-3"</code>
     *
     * @param <T> the iterable element type
     * @param <V> the separator type
     * @param values the source array
     * @param separator the separator
     * @return the resulting string
     */
    public static <T, V> String interpose(T[] values, V separator) {
        return new InterposeStrings<T, V>().perform(new ArrayIterator<T>(values), new ConstantIterator<V>(separator));
    }

    /**
     * Creates a string by interposing stringified values from the source
     * iterable with the separator. E.g:
     * <code>interpose([1,2,3], "-") -> "1-2-3"</code>
     *
     * @param <T> the iterable element type
     * @param <V> the separator type
     * @param values the source iterable
     * @param separator the separator
     * @return the resulting string
     */
    public static <T, V> String interpose(Iterable<T> values, V separator) {
        dbc.precondition(values != null, "cannot interpose from a null iterable");
        return new InterposeStrings<T, V>().perform(values.iterator(), new ConstantIterator<V>(separator));
    }

    /**
     * Creates a string by interposing stringified values from the source
     * iterator with the separator. E.g:
     * <code>interpose([1,2,3], "-") -> "1-2-3"</code>
     *
     * @param <T> the iterable element type
     * @param <V> the separator type
     * @param values the source iterator
     * @param separator the separator
     * @return the resulting string
     */
    public static <T, V> String interpose(Iterator<T> values, V separator) {
        return new InterposeStrings<T, V>().perform(values, new ConstantIterator<V>(separator));
    }
}
