package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;

/**
 *
 * @author rferranti
 */
public class LongParser implements Delegate<Long, String> {

    private final int radix;

    public LongParser(int radix) {
        this.radix = radix;
    }

    @Override
    public Long perform(String parsee) {
        dbc.precondition(parsee != null, "cannot parse a null string");
        return Long.parseLong(parsee, radix);
    }
}
