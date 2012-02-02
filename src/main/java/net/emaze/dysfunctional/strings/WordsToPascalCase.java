package net.emaze.dysfunctional.strings;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.iterations.TransformingIterator;

/**
 * ["one","two","three"] => "OneTwoThree"
 * @author rferranti
 */
public class WordsToPascalCase implements Delegate<String, String[]> {

    private final ToTitleCase toTitle = new ToTitleCase();

    @Override
    public String perform(String[] words) {
        dbc.precondition(words != null, "cannot pascalCase a null array of words");
        final Iterator<String> values = new TransformingIterator<String, String>(new ArrayIterator<String>(words), toTitle);
        return new JoinStrings<String>().perform(values);
    }
}
