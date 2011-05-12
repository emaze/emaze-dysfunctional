package net.emaze.dysfunctional.strings;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 *
 * @author rferranti
 */
public class ToTitleCase implements Delegate<String, String> {

    @Override
    public String perform(String word) {
        dbc.precondition(word != null, "cannot titleCase a null string");
        dbc.precondition(!word.isEmpty(), "cannot titleCase an empty string");
        return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
    }
}
