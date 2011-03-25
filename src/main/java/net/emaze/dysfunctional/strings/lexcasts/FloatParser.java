package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 *
 * @author rferranti
 */
public class FloatParser implements Delegate<Float, String> {

    @Override
    public Float perform(String parsee) {
        dbc.precondition(parsee != null, "cannot parse a null string");
        return Float.parseFloat(parsee);
    }
}
