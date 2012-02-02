package net.emaze.dysfunctional.strings;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.iterations.SingletonIterator;
import net.emaze.dysfunctional.iterations.TransformingIterator;

/**
 * ["oNe","tWo","threE"], "-" => "one-two-three"
 *
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
        final Iterator<String> values = new TransformingIterator<String, String>(new ArrayIterator<String>(words), toLower);
        return new InterposeStrings<String, String>().perform(values, new SingletonIterator<String>(snakeGlyph));
    }
}
