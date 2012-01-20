package net.emaze.dysfunctional.strings;

import net.emaze.dysfunctional.Strings;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.Transforming;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

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
        return Strings.interpose(Transforming.transform(words, toLower), snakeGlyph);
    }
}
