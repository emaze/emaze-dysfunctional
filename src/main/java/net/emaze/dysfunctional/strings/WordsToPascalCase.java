package net.emaze.dysfunctional.strings;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.iterations.Iterations;

/**
 * ["one","two","three"] => "OneTwoThree"
 * @author rferranti
 */
public class WordsToPascalCase implements Delegate<String, String[]> {

    private final ToTitleCase toTitle = new ToTitleCase();

    @Override
    public String perform(String[] words) {
        dbc.precondition(words != null, "cannot pascalCase a null array of words");
        return Strings.join(Iterations.transform(words, toTitle));
    }
}
