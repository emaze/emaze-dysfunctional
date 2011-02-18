package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;

/**
 * No, it doesn't parse two times.
 * @author rferranti
 */
public class DoubleParser implements Delegate<Double, String> {

    @Override
    public Double perform(String parsee) {
        dbc.precondition(parsee != null, "cannot parse a null string");
        return Double.parseDouble(parsee);
    }
}
