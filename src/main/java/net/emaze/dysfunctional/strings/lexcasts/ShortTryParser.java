package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import net.emaze.dysfunctional.options.Maybe;

/**
 *
 * @author rferranti
 */
public class ShortTryParser implements Function<String, Maybe<Short>> {

    private final int radix;

    public ShortTryParser(int radix) {
        dbc.precondition(radix >= Character.MIN_RADIX, "radix should be in range [MIN_RADIX, MAX_RADIX]");
        dbc.precondition(radix <= Character.MAX_RADIX, "radix should be in range [MIN_RADIX, MAX_RADIX]");        
        this.radix = radix;
    }

    @Override
    public Maybe<Short> apply(String parsee) {
        try {
            return Maybe.just(Short.parseShort(parsee, radix));
        } catch (NumberFormatException ex) {
            return Maybe.nothing();
        }
    }
}
