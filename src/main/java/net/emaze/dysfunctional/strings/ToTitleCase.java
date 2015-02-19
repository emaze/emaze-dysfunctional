package net.emaze.dysfunctional.strings;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 *
 * @author rferranti
 */
public class ToTitleCase implements Function<String, String> {

    @Override
    public String apply(String word) {
        dbc.precondition(word != null, "cannot titleCase a null string");
        dbc.precondition(!word.isEmpty(), "cannot titleCase an empty string");
        return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
    }
}
