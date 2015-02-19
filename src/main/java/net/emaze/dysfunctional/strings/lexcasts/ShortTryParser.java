package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import java.util.Optional;

/**
 *
 * @author rferranti
 */
public class ShortTryParser implements Function<String, Optional<Short>> {

    private final int radix;

    public ShortTryParser(int radix) {
        dbc.precondition(radix >= Character.MIN_RADIX, "radix should be in range [MIN_RADIX, MAX_RADIX]");
        dbc.precondition(radix <= Character.MAX_RADIX, "radix should be in range [MIN_RADIX, MAX_RADIX]");        
        this.radix = radix;
    }

    @Override
    public Optional<Short> apply(String parsee) {
        try {
            return Optional.of(Short.parseShort(parsee, radix));
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
    }
}
