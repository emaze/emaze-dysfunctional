package net.emaze.dysfunctional.strings;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.iterations.SingletonIterator;
import net.emaze.dysfunctional.iterations.TransformingIterator;
import net.emaze.dysfunctional.multiplexing.ChainIterator;

/**
 * ["one","two","three"] => "oneTwoThree"
 *
 * @author rferranti
 */
public class WordsToCamelCase implements Function<String[], String> {

    private final ToLowerCase toLower = new ToLowerCase();
    private final ToTitleCase toTitle = new ToTitleCase();

    @Override
    public String apply(String[] words) {
        dbc.precondition(words != null, "cannot camelcase a null array of words");
        if (words.length == 0) {
            return "";
        }
        final Iterator<String> iter = new ArrayIterator<String>(words);
        final Iterator<String> head = new SingletonIterator<String>(toLower.apply(iter.next()));
        final Iterator<String> tail = new TransformingIterator<String, String>(iter, toTitle);
        final ArrayIterator<Iterator<String>> ht = ArrayIterator.of(head, tail);
        final Iterator<String> chained = new ChainIterator<String>(ht);
        return new JoinStrings<String>().apply(chained);
    }
}
