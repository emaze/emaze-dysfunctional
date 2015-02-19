package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import net.emaze.dysfunctional.options.Maybe;

/**
 *
 * @author rferranti
 */
public class LongTryParser implements Function<String, Maybe<Long>> {

    private final int radix;

    public LongTryParser(int radix) {
        dbc.precondition(radix >= Character.MIN_RADIX, "radix should be in range [MIN_RADIX, MAX_RADIX]");
        dbc.precondition(radix <= Character.MAX_RADIX, "radix should be in range [MIN_RADIX, MAX_RADIX]");        
        this.radix = radix;
    }

    @Override
    public Maybe<Long> apply(String parsee) {
        try {
            return Maybe.just(Long.parseLong(parsee, radix));
        } catch (NumberFormatException ex) {
            return Maybe.nothing();
        }
    }
}
