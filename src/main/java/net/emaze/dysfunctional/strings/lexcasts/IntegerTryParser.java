package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;
import net.emaze.dysfunctional.options.Maybe;

/**
 *
 * @author rferranti
 */
public class IntegerTryParser implements Delegate<Maybe<Integer>, String> {

    private final int radix;

    public IntegerTryParser(int radix) {
        dbc.precondition(radix >= Character.MIN_RADIX, "radix should be in range [MIN_RADIX, MAX_RADIX]");
        dbc.precondition(radix <= Character.MAX_RADIX, "radix should be in range [MIN_RADIX, MAX_RADIX]");        
        this.radix = radix;
    }

    @Override
    public Maybe<Integer> perform(String parsee) {
        try {
            return Maybe.just(Integer.parseInt(parsee, radix));
        } catch (NumberFormatException ex) {
            return Maybe.nothing();
        }
    }
}
