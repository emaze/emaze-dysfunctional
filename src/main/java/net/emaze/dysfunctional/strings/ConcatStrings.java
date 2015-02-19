package net.emaze.dysfunctional.strings;

import java.util.Iterator;
import net.emaze.dysfunctional.consumers.ConsumeIntoOutputIterator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import net.emaze.dysfunctional.output.StringOutputIterator;

/**
 * Concatenate an iterator of String into a single String.
 */
public class ConcatStrings implements Function<Iterator<String>, String> {

    @Override
    public String apply(Iterator<String> iterator) {
        dbc.precondition(iterator != null, "cannot concat a null iterator");
        final StringOutputIterator output = new StringOutputIterator();
        final ConsumeIntoOutputIterator<String> pipe = new ConsumeIntoOutputIterator<String>(output);
        return pipe.apply(iterator).toString();
    }
}
