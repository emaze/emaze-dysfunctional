package net.emaze.dysfunctional.strings;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 *
 * @author rferranti
 */
public class ToLowerCase implements Delegate<String, String>{

    @Override
    public String perform(String str) {
        dbc.precondition(str != null, "cannot lowercase a null string");
        return str.toLowerCase();
    }

}
