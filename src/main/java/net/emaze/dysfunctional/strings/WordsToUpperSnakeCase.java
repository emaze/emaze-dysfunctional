package net.emaze.dysfunctional.strings;

import net.emaze.dysfunctional.Strings;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.Transforming;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * ["oNe","tWo","threE"], "-" => "ONE-TWO-THREE"
 * @author rferranti
 */
public class WordsToUpperSnakeCase implements Delegate<String, String[]> {

    private final ToUpperCase toUpper = new ToUpperCase();
    private final String snakeGlyph;

    public WordsToUpperSnakeCase(String snakeGlyph) {
        dbc.precondition(snakeGlyph != null, "cannot create WordsToUpperSnakeCase with a null snakeGlyph");
        this.snakeGlyph = snakeGlyph;
    }

    @Override
    public String perform(String[] words) {
        dbc.precondition(words != null, "cannot upperSnakeCase a null array of words");
        return Strings.interpose(Transforming.transform(words, toUpper), snakeGlyph);
    }
}
