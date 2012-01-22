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

    public static <T> String join(T[] array) {
        return join(new ArrayIterator<T>(array));
    }

    public static <T> String join(Iterable<T> iterable) {
        dbc.precondition(iterable != null, "cannot join a null iterable");
        return join(iterable.iterator());
    }

    public static <T> String join(Iterator<T> iterator) {
        return new JoinStrings<T>().perform(iterator);
    }

    public static <T, V> String interpose(T[] values, Iterator<V> separators) {
        return new InterposeStrings<T, V>().perform(new ArrayIterator<T>(values), separators);
    }

    public static <T, V> String interpose(Iterable<T> values, Iterator<V> separators) {
        dbc.precondition(values != null, "calling interpose with a null iterable");
        return new InterposeStrings<T, V>().perform(values.iterator(), separators);
    }

    public static <T, V> String interpose(Iterator<T> values, Iterator<V> separators) {
        return new InterposeStrings<T, V>().perform(values, separators);
    }

    public static <T, V> String interpose(T[] values, V separator) {
        return new InterposeStrings<T, V>().perform(new ArrayIterator<T>(values), new ConstantIterator<V>(separator));
    }

    public static <T, V> String interpose(Iterable<T> values, V separator) {
        dbc.precondition(values != null, "cannot interpose from a null iterable");
        return new InterposeStrings<T, V>().perform(values.iterator(), new ConstantIterator<V>(separator));
    }

    public static <T, V> String interpose(Iterator<T> values, V separator) {
        return new InterposeStrings<T, V>().perform(values, new ConstantIterator<V>(separator));
    }
}
