package net.emaze.dysfunctional.strings;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.ConsumeIntoOutputIterator;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.iterations.TransformingIterator;
import net.emaze.dysfunctional.output.StringOutputIterator;

/**
 *
 * @author rferranti
 */
public class JoinStrings<T> implements Delegate<String, Iterator<T>> {

    @Override
    public String perform(Iterator<T> iterator) {
        dbc.precondition(iterator != null, "cannot join a null iterator");
        final StringOutputIterator output = new StringOutputIterator();
        final ConsumeIntoOutputIterator<String> pipe = new ConsumeIntoOutputIterator<String>(output);
        final Iterator<String> elements = new TransformingIterator<String, T>(iterator, new ToStringTransformer<T>());
        return pipe.perform(elements).toString();
    }
}
