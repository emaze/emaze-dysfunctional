package net.emaze.dysfunctional.strings;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.iterations.SingletonIterator;
import net.emaze.dysfunctional.iterations.TransformingIterator;

/**
 * ["oNe","tWo","threE"], "-" => "ONE-TWO-THREE"
 *
 * @author rferranti
 */
public class WordsToUpperSnakeCase implements Function<String[], String> {

    private final ToUpperCase toUpper = new ToUpperCase();
    private final String snakeGlyph;

    public WordsToUpperSnakeCase(String snakeGlyph) {
        dbc.precondition(snakeGlyph != null, "cannot create WordsToUpperSnakeCase with a null snakeGlyph");
        this.snakeGlyph = snakeGlyph;
    }

    @Override
    public String apply(String[] words) {
        dbc.precondition(words != null, "cannot upperSnakeCase a null array of words");
        final Iterator<String> values = new TransformingIterator<String, String>(new ArrayIterator<String>(words), toUpper);
        return new InterposeStrings<String, String>().apply(values, new SingletonIterator<String>(snakeGlyph));
    }
}
