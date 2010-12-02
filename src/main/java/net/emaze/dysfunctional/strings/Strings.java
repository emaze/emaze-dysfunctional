package net.emaze.dysfunctional.strings;

import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.consumers.PipingConsumer;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.ToStringTransformer;
import net.emaze.dysfunctional.iterations.ConstantIterator;
import net.emaze.dysfunctional.iterations.Iterations;
import net.emaze.dysfunctional.iterations.StringOutputIterator;
import net.emaze.dysfunctional.multiplexing.InterposingIterator;

/**
 *
 * @author rferranti
 */
public abstract class Strings {

    public static <T> String join(Iterator<T> iterator) {
        final StringOutputIterator output = new StringOutputIterator();
        final PipingConsumer<String> pipe = new PipingConsumer<String>(output);
        final List<String> elements = Iterations.map(iterator, new ToStringTransformer<T>());
        return pipe.consume(elements.iterator()).toString();
    }

    public static <T, V> String interpose(Iterator<T> values, Iterator<V> separators) {
        dbc.precondition(values != null, "calling interpose with a null values");
        dbc.precondition(separators != null, "calling interpose with a null separators");

        final StringOutputIterator output = new StringOutputIterator();
        final PipingConsumer<String> pipe = new PipingConsumer<String>(output);
        final Iterator<String> input = new InterposingIterator<String>(
                Iterations.map(values, new ToStringTransformer<T>()).iterator(),
                //BUG: these map should be lazy (try using a constantIterator)
                Iterations.map(separators, new ToStringTransformer<V>()).iterator());
        return pipe.consume(input).toString();
    }

    public static <T, V> String interpose(Iterator<T> values, V separator) {
        dbc.precondition(values != null, "calling interpose with a null values");
        dbc.precondition(separator != null, "calling interpose with a null separator");

        final StringOutputIterator output = new StringOutputIterator();
        final PipingConsumer<String> pipe = new PipingConsumer<String>(output);
        final String separatorAsString = new ToStringTransformer<V>().perform(separator);
        final Iterator<String> input = new InterposingIterator<String>(
                Iterations.map(values, new ToStringTransformer<T>()).iterator(),
                new ConstantIterator<String>(separatorAsString));
        return pipe.consume(input).toString();
    }
}
