package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.options.Maybe;

/**
 *
 * @author rferranti
 */
public class ByteTryParser implements Delegate<Maybe<Byte>, String> {

    private final int radix;

    public ByteTryParser(int radix) {
        dbc.precondition(radix >= Character.MIN_RADIX, "radix should be in range [MIN_RADIX, MAX_RADIX]");
        dbc.precondition(radix <= Character.MAX_RADIX, "radix should be in range [MIN_RADIX, MAX_RADIX]");        
        this.radix = radix;
    }

    @Override
    public Maybe<Byte> perform(String parsee) {
        try {
            return Maybe.just(Byte.parseByte(parsee, radix));
        } catch (NumberFormatException ex) {
            return Maybe.nothing();
        }
    }
}
