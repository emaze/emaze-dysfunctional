package net.emaze.dysfunctional.strings;

import java.util.Iterator;
import net.emaze.dysfunctional.consumers.ConsumeIntoOutputIterator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import net.emaze.dysfunctional.iterations.TransformingIterator;
import net.emaze.dysfunctional.output.StringOutputIterator;

/**
 *
 * @param <T> 
 * @author rferranti
 */
public class JoinStrings<T> implements Function<Iterator<T>, String> {

    @Override
    public String apply(Iterator<T> iterator) {
        dbc.precondition(iterator != null, "cannot join a null iterator");
        final StringOutputIterator output = new StringOutputIterator();
        final ConsumeIntoOutputIterator<String> pipe = new ConsumeIntoOutputIterator<String>(output);
        final Iterator<String> elements = new TransformingIterator<>(iterator, new ToStringTransformer<T>());
        return pipe.apply(elements).toString();
    }
}
