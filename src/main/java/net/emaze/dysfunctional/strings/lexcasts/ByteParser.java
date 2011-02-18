package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;

/**
 *
 * @author rferranti
 */
public class ByteParser implements Delegate<Byte, String> {

    @Override
    public Byte perform(String parsee) {
        dbc.precondition(parsee != null, "cannot parse a null string");
        return Byte.parseByte(parsee);
    }
}
