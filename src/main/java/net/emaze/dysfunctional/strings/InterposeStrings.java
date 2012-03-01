package net.emaze.dysfunctional.strings;

import java.util.Iterator;
import net.emaze.dysfunctional.consumers.ConsumeIntoOutputIterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.iterations.TransformingIterator;
import net.emaze.dysfunctional.multiplexing.InterposingIterator;
import net.emaze.dysfunctional.output.StringOutputIterator;

/**
 *
 * @param <T> 
 * @param <V> 
 * @author rferranti
 */
public class InterposeStrings<T, V> implements BinaryDelegate<String, Iterator<T>, Iterator<V>> {

    @Override
    public String perform(Iterator<T> values, Iterator<V> separators) {
        dbc.precondition(values != null, "calling interpose with a null values");
        dbc.precondition(separators != null, "calling interpose with a null separators");
        final Iterator<String> input = new InterposingIterator<String>(
                new TransformingIterator<String, T>(values, new ToStringTransformer<T>()),
                new TransformingIterator<String, V>(separators, new ToStringTransformer<V>()));
        final StringOutputIterator output = new StringOutputIterator();
        final ConsumeIntoOutputIterator<String> pipe = new ConsumeIntoOutputIterator<String>(output);
        return pipe.perform(input).toString();
    }
}
