package net.emaze.dysfunctional.strings;

import java.util.Iterator;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.iterations.Iterations;
import net.emaze.dysfunctional.multiplexing.Multiplexing;

/**
 * ["one","two","three"] => "oneTwoThree"
 * @author rferranti
 */
public class WordsToCamelCase implements Delegate<String, String[]> {
    private final ToLowerCase toLower = new ToLowerCase();
    private final ToTitleCase toTitle = new ToTitleCase();

    @Override
    public String perform(String[] words) {
        dbc.precondition(words != null, "cannot camelcase a null array of words");
        if(words.length == 0){
            return "";
        }
        final Iterator<String> iter = new ArrayIterator<String>(words);
        final String head = toLower.perform(iter.next());
        final Iterator<String> tail = Iterations.transform(iter, toTitle);
        return Strings.join(Multiplexing.chain(Iterations.iterator(head), tail));
    }
}
