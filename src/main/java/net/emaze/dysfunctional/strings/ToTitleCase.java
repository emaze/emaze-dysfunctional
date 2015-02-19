package net.emaze.dysfunctional.strings;

import java.util.function.UnaryOperator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 *
 * @author rferranti
 */
public class ToTitleCase implements UnaryOperator<String> {

    @Override
    public String apply(String word) {
        dbc.precondition(word != null, "cannot titleCase a null string");
        dbc.precondition(!word.isEmpty(), "cannot titleCase an empty string");
        return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
    }
}
