package net.emaze.dysfunctional.strings;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.iterations.Iterations;

/**
 * ["oNe","tWo","threE"], "-" => "one-two-three"
 * @author rferranti
 */
public class WordsToLowerSnakeCase implements Delegate<String, String[]> {

    private final ToLowerCase toLower = new ToLowerCase();
    private final String snakeGlyph;

    public WordsToLowerSnakeCase(String snakeGlyph) {
        dbc.precondition(snakeGlyph != null, "cannot create WordsToLowerSnakeCase with a null snakeGlyph");
        this.snakeGlyph = snakeGlyph;
    }

    @Override
    public String perform(String[] words) {
        dbc.precondition(words != null, "cannot lowerSnakeCase a null array of words");
        return Strings.interpose(Iterations.transform(words, toLower), snakeGlyph);
    }
}
