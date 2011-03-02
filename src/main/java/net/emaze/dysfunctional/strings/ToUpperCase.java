package net.emaze.dysfunctional.strings;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;

/**
 *
 * @author rferranti
 */
public class ToUpperCase implements Delegate<String, String>{

    @Override
    public String perform(String str) {
        dbc.precondition(str != null, "cannot uppercase a null string");
        return str.toUpperCase();
    }

}
