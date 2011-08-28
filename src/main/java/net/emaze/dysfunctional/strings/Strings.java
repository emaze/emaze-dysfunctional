package net.emaze.dysfunctional.strings;

import java.util.Iterator;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.consumers.PipingConsumer;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.iterations.ConstantIterator;
import net.emaze.dysfunctional.iterations.Iterations;
import net.emaze.dysfunctional.consumers.StringOutputIterator;
import net.emaze.dysfunctional.iterations.TransformingIterator;
import net.emaze.dysfunctional.multiplexing.InterposingIterator;

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
        dbc.precondition(iterator != null, "cannot join a null iterator");
        final StringOutputIterator output = new StringOutputIterator();
        final PipingConsumer<String> pipe = new PipingConsumer<String>(output);
        final Iterator<String> elements = Iterations.transform(iterator, new ToStringTransformer<T>());
        return pipe.consume(elements).toString();
    }

    public static <T, V> String interpose(Iterator<T> values, Iterator<V> separators) {
        dbc.precondition(values != null, "calling interpose with a null values");
        dbc.precondition(separators != null, "calling interpose with a null separators");
        final Iterator<String> input = new InterposingIterator<String>(
                new TransformingIterator<String, T>(values, new ToStringTransformer<T>()),
                new TransformingIterator<String, V>(separators, new ToStringTransformer<V>()));
        final StringOutputIterator output = new StringOutputIterator();
        final PipingConsumer<String> pipe = new PipingConsumer<String>(output);
        return pipe.consume(input).toString();
    }

    public static <T, V> String interpose(Iterator<T> values, V separator) {
        return interpose(values, new ConstantIterator<V>(separator));
    }

    public static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
