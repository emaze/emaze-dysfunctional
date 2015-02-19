package net.emaze.dysfunctional.strings;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.iterations.TransformingIterator;

/**
 * ["one","two","three"] => "OneTwoThree"
 * @author rferranti
 */
public class WordsToPascalCase implements Function<String[], String> {

    private final ToTitleCase toTitle = new ToTitleCase();

    @Override
    public String apply(String[] words) {
        dbc.precondition(words != null, "cannot pascalCase a null array of words");
        final Iterator<String> values = new TransformingIterator<String, String>(new ArrayIterator<String>(words), toTitle);
        return new JoinStrings<String>().apply(values);
    }
}
