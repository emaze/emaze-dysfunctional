package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;

/**
 *
 * @author rferranti
 */
public class ShortParser implements Delegate<Short, String> {

    @Override
    public Short perform(String parsee) {
        dbc.precondition(parsee != null, "cannot parse a null string");
        return Short.parseShort(parsee);
    }
}
