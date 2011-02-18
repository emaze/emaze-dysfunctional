package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;

/**
 *
 * @author rferranti
 */
public class IntegerParser implements Delegate<Integer, String> {

    @Override
    public Integer perform(String parsee) {
        dbc.precondition(parsee != null, "cannot parse a null string");
        return Integer.parseInt(parsee);
    }
}
