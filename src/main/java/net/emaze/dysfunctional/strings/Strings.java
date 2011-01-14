package net.emaze.dysfunctional.strings;

import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.consumers.PipingConsumer;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.ToStringTransformer;
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

    public static <T> String join(Iterator<T> iterator) {
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
}
